# Модуль 104. Spring Test: тестирование веб-слоя (@WebMvcTest, MockMvc)

Поднимать всё приложение ради проверки одного контроллера — дорого. Spring даёт **срезовый тест** `@WebMvcTest`: он загружает только web-слой (контроллеры, фильтры, конвертеры JSON, `MockMvc`) и **мокает** сервисы. Это быстрый способ проверить маршрутизацию, статусы, сериализацию и валидацию HTTP-слоя без БД и реального сервера. В этом модуле — `@WebMvcTest`, `MockMvc`, новый текучий `MockMvcTester`, `@MockBean`, `jsonPath` и `@JsonTest`.

> Практика — задачи в `practice/`. **Тест-классы (без `main`)**, запуск в IDE (▶) или `./gradlew test`. Зависимости: `spring-boot-starter-web`, `spring-boot-starter-test`. bare-javac не верифицируется (норма). Сквозной проект — **Task Tracker API**.

---

## `@WebMvcTest`: что внутри среза

```
   @WebMvcTest(TaskController.class)
   ┌─────────────────────────────────────────┐
   │ ЗАГРУЖАЕТСЯ:  контроллеры, @ControllerAdvice, фильтры, MockMvc,
   │              Jackson (JSON), валидация, конвертеры
   │ НЕ загружается: @Service, @Repository, БД  → их МОКАЮТ (@MockBean)
   └─────────────────────────────────────────┘
```

```java
@WebMvcTest(TaskController.class)              // только этот контроллер
class TaskControllerTest {

    @Autowired MockMvc mockMvc;                // клиент для «запросов» без сети
    @MockBean TaskService service;             // зависимость контроллера — мок в контексте

    @Test
    void getReturnsTask() throws Exception {
        when(service.find(1L)).thenReturn(new TaskDto(1L, "Кофе", "NEW"));

        mockMvc.perform(get("/api/tasks/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.title").value("Кофе"));
    }
}
```

> `@MockBean` — это Mockito-мок, помещённый **в Spring-контекст** (в отличие от `@Mock` из модуля 102, который живёт вне контекста). Контроллер получит именно его.

---

## `MockMvc`: запрос → ожидания

```java
mockMvc.perform(get("/api/tasks/1")                  // построить запрос
                .accept(MediaType.APPLICATION_JSON))
       .andExpect(status().isOk())                   // проверки ответа
       .andExpect(content().contentType(MediaType.APPLICATION_JSON))
       .andExpect(jsonPath("$.title").value("Кофе"))
       .andDo(print());                              // распечатать запрос/ответ (отладка)
```

| Билдер запроса | |
|----------------|--|
| `get/post/put/patch/delete("/uri")` | метод и путь |
| `.contentType(...)` / `.accept(...)` | заголовки Content-Type / Accept |
| `.content("{...}")` | тело запроса |
| `.param("page", "0")` | query-параметр |
| `.header("X", "y")` | произвольный заголовок |

| Матчер ответа | |
|---------------|--|
| `status().isOk()/.isCreated()/.isNotFound()/.isBadRequest()` | HTTP-статус |
| `jsonPath("$.field").value(...)` | значение по JSON-пути |
| `content().json("{...}")` | сравнение всего JSON |
| `header().string("Location", ...)` | заголовок ответа |

---

## POST с телом и проверка `Location`

```java
@Test
void createsTask() throws Exception {
    when(service.create(any())).thenReturn(new TaskDto(10L, "Кофе", "NEW"));

    mockMvc.perform(post("/api/tasks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                             {"title":"Кофе"}
                             """))
           .andExpect(status().isCreated())
           .andExpect(header().string("Location", "/api/tasks/10"))
           .andExpect(jsonPath("$.id").value(10));
}
```

Тело удобно собирать `ObjectMapper`’ом: `objectMapper.writeValueAsString(new CreateTaskRequest("Кофе"))`.

---

## `jsonPath`: проверки структуры ответа

```java
.andExpect(jsonPath("$.title").value("Кофе"))            // поле
.andExpect(jsonPath("$.status").value("NEW"))
.andExpect(jsonPath("$").isArray())                      // корень — массив
.andExpect(jsonPath("$.length()").value(2))              // размер массива
.andExpect(jsonPath("$[0].title").value("Кофе"))         // элемент массива
.andExpect(jsonPath("$.assignee").doesNotExist())        // поля нет
.andExpect(jsonPath("$.tags", hasSize(3)))               // Hamcrest-матчер (модуль 101)
```

---

## `MockMvcTester` — текучий API (Spring 6.2 / Boot 3.4+)

Современная альтернатива `mockMvc.perform(...).andExpect(...)` — на AssertJ:

```java
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(TaskController.class)
class TaskControllerTesterTest {
    @Autowired MockMvcTester mvc;          // автоконфигурируется
    @MockBean TaskService service;

    @Test
    void getReturnsTask() {
        when(service.find(1L)).thenReturn(new TaskDto(1L, "Кофе", "NEW"));

        assertThat(mvc.get().uri("/api/tasks/1"))      // нет checked-исключений!
                .hasStatusOk()
                .bodyJson().extractingPath("$.title").isEqualTo("Кофе");
    }
}
```

| `MockMvc` (классика) | `MockMvcTester` (новый) |
|----------------------|-------------------------|
| `perform(...).andExpect(...)` | `assertThat(mvc.get()...)` |
| бросает `throws Exception` | без checked-исключений |
| матчеры Hamcrest/jsonPath | стиль AssertJ |

Оба валидны; в курсе показываем классику (распространена) и `MockMvcTester` (актуальный).

---

## `@JsonTest` + `JacksonTester`: только сериализация

Когда нужно проверить **маппинг DTO ↔ JSON** в изоляции (без контроллера):

```java
@JsonTest
class TaskDtoJsonTest {
    @Autowired JacksonTester<TaskDto> json;

    @Test
    void serializes() throws Exception {
        assertThat(json.write(new TaskDto(1L, "Кофе", "NEW")))
                .hasJsonPathStringValue("$.title")
                .extractingJsonPathStringValue("$.title").isEqualTo("Кофе");
    }

    @Test
    void deserializes() throws Exception {
        TaskDto dto = json.parseObject("{\"id\":1,\"title\":\"Кофе\",\"status\":\"NEW\"}");
        assertThat(dto.title()).isEqualTo("Кофе");
    }
}
```

`@JsonTest` поднимает только Jackson — ещё легче, чем `@WebMvcTest`.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Тест видит «реальный» сервис → `NullPointer` | сервис не замокан | `@MockBean` на зависимости контроллера |
| Контекст не поднимается | `@WebMvcTest` тащит `@Component`, которого нет | мокать недостающие бины или сужать срез |
| 200 вместо 404 | сервис-мок не настроен бросать/возвращать пусто | задать поведение мока для этого пути |
| POST → 403 | включён CSRF (если есть Security) | `.with(csrf())` (модуль 100) |
| `jsonPath` не находит поле | неверный путь/поле сериализуется иначе | проверить имя (`@JsonProperty`), `andDo(print())` |
| `@WebMvcTest` грузит всё приложение | указали без аргумента + широкий scan | `@WebMvcTest(Controller.class)` точечно |
| Security-правила не совпадают с продом | `SecurityConfig` не импортирован | `@Import(SecurityConfig.class)` (модули 76/100) |

---

## Дополнительные источники

- [Spring Boot: Testing the Web Layer (`@WebMvcTest`)](https://docs.spring.io/spring-boot/reference/testing/spring-boot-applications.html#testing.spring-boot-applications.spring-mvc-tests).
- [`MockMvcTester` — Spring Framework docs](https://docs.spring.io/spring-framework/reference/testing/mockmvc/assertj.html).
- [`@JsonTest` + `JacksonTester`](https://docs.spring.io/spring-boot/reference/testing/spring-boot-applications.html#testing.spring-boot-applications.json-tests).
- [модуль 76](../module-76-spring-rest-testing/theory.md) — первое знакомство с `@WebMvcTest`/MockMvc.

## Что дальше

В [модуле 105](../module-105-spring-test-controller-scenarios/theory.md) — **реальные сценарии контроллера**: тест валидации (`@Valid` → 400 + ProblemDetail), обработки ошибок (`@ControllerAdvice` в срезе), пагинации/сортировки и загрузки файлов (`MockMultipartFile`). Применим инструменты этого модуля к типичным задачам REST API.
