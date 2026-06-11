# Модуль 105. Spring Test: реальные сценарии контроллера

Инструменты веб-тестирования ([модуль 104](../module-104-spring-test-webmvc/theory.md)) показаны на «счастливом пути». Реальный REST API проверяют по типовым сценариям: **валидация** (плохой ввод → 400 + понятная ошибка), **обработка ошибок** (404/конфликт → единый контракт `ProblemDetail`), **коллекции** (пагинация, сортировка, фильтрация) и **файлы** (загрузка/выгрузка). Этот модуль — тесты для каждого, по-прежнему в срезе `@WebMvcTest`. Завершает первую часть блока Spring Test.

> Практика — задачи в `practice/`. **Тест-классы (без `main`)**, запуск в IDE (▶) или `./gradlew test`. Зависимости: `spring-boot-starter-web`, `spring-boot-starter-validation`, `spring-boot-starter-test`. bare-javac не верифицируется (норма). Сквозной проект — **Task Tracker API**.

---

## Тест валидации: `@Valid` → 400

Контроллер с `@Valid` отклоняет некорректное тело статусом 400 (модули [71](../module-71-spring-rest-validation/theory.md), [72](../module-72-spring-rest-error-handling/theory.md)). Тест фиксирует и статус, и сообщение:

```java
@Test
void blank_title_is_400() throws Exception {
    mockMvc.perform(post("/api/tasks")
                    .contentType(APPLICATION_JSON)
                    .content("{\"title\":\"\"}"))             // пустой title нарушает @NotBlank
           .andExpect(status().isBadRequest())
           .andExpect(jsonPath("$.detail").exists())          // ProblemDetail
           .andExpect(jsonPath("$.errors.title").exists());   // если advice кладёт поля
}
```

> Сервис при этом **не должен вызываться** — запрос отсекается до бизнес-логики: `verify(service, never()).create(any())`.

---

## Тест обработки ошибок: единый контракт `ProblemDetail`

Срез `@WebMvcTest` сам по себе **не подхватывает** `@RestControllerAdvice`, если он в другом пакете — его импортируют:

```java
@WebMvcTest(TaskController.class)
@Import(GlobalExceptionHandler.class)        // подключить advice в срез
class TaskErrorTest {

    @Test
    void not_found_is_404_problemdetail() throws Exception {
        when(service.find(99L)).thenThrow(new TaskNotFoundException(99L));

        mockMvc.perform(get("/api/tasks/99"))
               .andExpect(status().isNotFound())
               .andExpect(content().contentType("application/problem+json"))
               .andExpect(jsonPath("$.status").value(404))
               .andExpect(jsonPath("$.detail").value(containsString("99")));
    }
}
```

| Что проверяем в ошибке | Матчер |
|------------------------|--------|
| статус | `status().isNotFound()` / `.isConflict()` |
| тип контента | `content().contentType("application/problem+json")` |
| тело ProblemDetail | `jsonPath("$.status")`, `$.detail`, `$.title` |

---

## Тест пагинации и сортировки

```java
@Test
void paginated_response() throws Exception {
    Page<TaskDto> page = new PageImpl<>(
            List.of(new TaskDto(1L, "Кофе")), PageRequest.of(0, 10), 1);
    when(service.list(any(Pageable.class))).thenReturn(page);

    mockMvc.perform(get("/api/tasks").param("page", "0").param("size", "10").param("sort", "title,asc"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.content").isArray())
           .andExpect(jsonPath("$.content.length()").value(1))
           .andExpect(jsonPath("$.totalElements").value(1))
           .andExpect(jsonPath("$.number").value(0));        // номер страницы
}
```

Передаём `page`/`size`/`sort` как query-параметры; проверяем метаданные страницы (`content`, `totalElements`, `number`, `size`). Если пагинация ручная (модуль [73](../module-73-spring-rest-collections/theory.md)) — структура своя, но логика теста та же.

---

## Тест загрузки файла: `MockMultipartFile`

```java
import org.springframework.mock.web.MockMultipartFile;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

@Test
void uploads_attachment() throws Exception {
    MockMultipartFile file = new MockMultipartFile(
            "file",                        // имя параметра (@RequestParam("file"))
            "notes.txt",                   // оригинальное имя файла
            "text/plain",                  // content-type
            "привет".getBytes(UTF_8));     // содержимое

    when(service.store(any())).thenReturn("notes.txt");

    mockMvc.perform(multipart("/api/tasks/1/attachments").file(file))
           .andExpect(status().isCreated())
           .andExpect(jsonPath("$.filename").value("notes.txt"));
}
```

## Тест выгрузки файла: байты + `Content-Disposition`

```java
@Test
void downloads_attachment() throws Exception {
    when(service.load("notes.txt")).thenReturn("привет".getBytes(UTF_8));

    mockMvc.perform(get("/api/tasks/1/attachments/notes.txt"))
           .andExpect(status().isOk())
           .andExpect(header().string("Content-Disposition", containsString("attachment")))
           .andExpect(content().bytes("привет".getBytes(UTF_8)));
}
```

| Аспект файлов | Матчер |
|---------------|--------|
| загрузка (multipart) | `multipart(uri).file(MockMultipartFile)` |
| статус создания | `status().isCreated()` |
| выгрузка — заголовок | `header().string("Content-Disposition", containsString("attachment"))` |
| выгрузка — содержимое | `content().bytes(...)` |

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Валидация даёт 200 вместо 400 | нет `@Valid` на параметре или нет starter-validation | `@Valid @RequestBody`, добавить зависимость |
| Ошибка → 500 вместо 404 | advice не подключён в срез | `@Import(GlobalExceptionHandler.class)` |
| `content().contentType("application/json")` падает на ошибке | ProblemDetail отдаёт `application/problem+json` | проверять `problem+json` |
| multipart-тест → 400 | не тот параметр/нет `multipart(...)` | имя `MockMultipartFile` = имя `@RequestParam` |
| POST/multipart → 403 | включён CSRF (Security) | `.with(csrf())` (модуль 100) |
| Сортировка по «левому» полю | нет белого списка полей | проверять отказ/санитайзинг (модуль 73) |
| Пустая коллекция = `null` в JSON | вернули `null` вместо `[]` | контроллер должен отдавать пустой массив |

---

## Дополнительные источники

- [Spring: Testing Multipart Requests (`MockMultipartFile`)](https://docs.spring.io/spring-framework/reference/testing/spring-mvc-test-framework/server-performing-requests.html).
- [Spring REST: Error Responses (`ProblemDetail`, RFC 9457)](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-ann-rest-exceptions.html).
- [модуль 72](../module-72-spring-rest-error-handling/theory.md) — `ProblemDetail` и `@RestControllerAdvice`.
- [модуль 74](../module-74-spring-rest-crud-file/theory.md) — загрузка/выгрузка файлов.

## Что дальше

Первая часть Spring Test (101–105: инструменты, unit, конфигурация, web-слой, сценарии) пройдена. В [модуле 106](../module-106-spring-test-datajpa/theory.md) начинается **тестирование слоя данных**: `@DataJpaTest`, `TestEntityManager`, `flush/clear`, проверка запросов. Поднимаемся от web-среза к data-срезу.
