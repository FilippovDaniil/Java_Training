# Модуль 76. Тестирование REST-API

API без тестов — мина замедленного действия. Изменение в одном эндпоинте незаметно ломает другой. Spring Boot даёт два уровня тестов REST: быстрый **slice** (`@WebMvcTest` + `MockMvc`) и полный **интеграционный** (`@SpringBootTest` + `TestRestTemplate`). Этот модуль закрывает блок Spring REST (67–75).

> Практика — задачи в `practice/` (это тест-классы, **без `main`**). Зависимости: `spring-boot-starter-web`, `spring-boot-starter-test`, `spring-boot-starter-validation`. Запуск в IDE (▶) или `./gradlew test`.

---

## Пирамида тестов

```
          ▲  мало, медленно
         ╱ ╲    E2E / интеграционные  (@SpringBootTest)
        ╱───╲
       ╱     ╲   слайс-тесты          (@WebMvcTest)
      ╱───────╲
     ╱         ╲  unit-тесты          (обычный JUnit + Mockito)
    ╱───────────╲ много, быстро
```

Чем ниже — тем больше тестов и быстрее они. Контроллеры удобно покрывать слайс-тестами.

---

## @WebMvcTest + MockMvc (slice)

`@WebMvcTest` поднимает **только web-слой** (контроллеры, фильтры, конвертеры) — без сервисов и БД. Зависимости контроллера подменяются моками. `MockMvc` шлёт «ненастоящие» HTTP-запросы прямо в `DispatcherServlet`, без реального сетевого порта.

```java
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired MockMvc mockMvc;
    @MockBean TaskService taskService;          // зависимость контроллера — мок

    @Test
    void getReturnsTask() throws Exception {
        when(taskService.find(1L)).thenReturn(new TaskDto(1L, "Кофе", "NEW"));

        mockMvc.perform(get("/api/tasks/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.title").value("Кофе"))
               .andExpect(jsonPath("$.status").value("NEW"));
    }
}
```

### Матчеры результата

| Матчер | Проверяет |
|--------|-----------|
| `status().isOk()` / `.isCreated()` / `.isNotFound()` | код статуса |
| `jsonPath("$.field").value(...)` | значение поля в JSON |
| `jsonPath("$", hasSize(3))` | размер массива |
| `content().string(...)` | тело как строка |
| `header().string("Location", ...)` | заголовок |
| `content().contentType(...)` | `Content-Type` |

### Построители запроса

```java
get("/api/tasks/1")
post("/api/tasks").contentType(APPLICATION_JSON).content("{\"title\":\"X\"}")
put("/api/tasks/1").contentType(APPLICATION_JSON).content(json)
patch("/api/tasks/1")...
delete("/api/tasks/1")
```

---

## Тестирование POST с JSON-телом

Тело удобно собрать `ObjectMapper`'ом:

```java
@Autowired ObjectMapper objectMapper;

@Test
void createReturns201() throws Exception {
    var dto = new CreateTaskDto("Купить кофе");
    mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
           .andExpect(status().isCreated())
           .andExpect(header().exists("Location"));
}
```

---

## Тестирование ошибок и валидации

```java
@Test
void invalidBodyReturns400() throws Exception {
    mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"\"}"))         // пустой title
           .andExpect(status().isBadRequest());
}

@Test
void missingTaskReturns404() throws Exception {
    when(taskService.find(999L)).thenThrow(new TaskNotFoundException(999L));
    mockMvc.perform(get("/api/tasks/999"))
           .andExpect(status().isNotFound());
}
```

---

## @SpringBootTest + TestRestTemplate (интеграция)

Поднимает **весь** контекст и реальный встроенный сервер на случайном порту. Тест бьёт по настоящему HTTP через `TestRestTemplate`.

```java
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class TaskApiIntegrationTest {

    @Autowired TestRestTemplate rest;

    @Test
    void getReturnsTask() {
        ResponseEntity<TaskDto> resp = rest.getForEntity("/api/tasks/1", TaskDto.class);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals("Кофе", resp.getBody().title());
    }
}
```

---

## Что выбрать

| | `@WebMvcTest` | `@SpringBootTest` |
|-|---------------|-------------------|
| Поднимает | web-слой | весь контекст + сервер |
| Скорость | быстро | медленно |
| Сеть | нет (MockMvc) | реальный порт |
| Сервис/БД | моки (`@MockBean`) | настоящие бины |
| Для чего | логика контроллера, статусы, JSON | сквозные сценарии, связки |

Покрывайте основную массу контроллеров `@WebMvcTest`, а ключевые сценарии — точечно `@SpringBootTest`.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| `@WebMvcTest` не поднимается: нет бина сервиса | сервис не в web-слое | объявить `@MockBean ServiceClass` |
| Возвращается 200 пустой ответ | замокан сам фильтр (`doFilter` final) | мокировать зависимости фильтра, не фильтр (см. проектные инструкции) |
| `jsonPath` не находит поле | неверный путь / поле переименовано Jackson | проверить фактический JSON ответа |
| Кириллица в `content()` ломает сравнение | `text/html` без UTF-8 | для JSON проблемы нет; для текста — явный charset |
| POST в тесте даёт 403 | CSRF / security включены | `@AutoConfigureMockMvc(addFilters=false)` или настроить security |
| `@SpringBootTest` медленный в каждом тесте | поднимает всё | переносить мелочи на `@WebMvcTest` |

> Тонкости тестов контроллеров под Spring Security (`@MockBean JwtAuthenticationFilter` ломает цепочку; `@Import(SecurityConfig.class)` для проверки правил) — в проектных инструкциях (`~/.claude/CLAUDE.md`, разделы про @WebMvcTest).

---

## Дополнительные источники

- [Spring Boot: Testing](https://docs.spring.io/spring-boot/reference/testing/index.html).
- [MockMvc — Spring Reference](https://docs.spring.io/spring-framework/reference/testing/mockmvc.html).
- [модуль 34](../m34_testing_junit_mockito/theory.md) — JUnit 5 и Mockito.
- [модуль 66](../m66_spring_boot_devops/theory.md) — `@WebMvcTest`/`@SpringBootTest` (введение).

## Что дальше

Блок Spring REST (67–76) завершён. В [модуле 77](../m77_spring_data_jpa_intro/theory.md) начинается **Spring Data JPA**: слой доступа к данным, сущности, репозитории — и второй сквозной проект `shop-data-jpa`.
