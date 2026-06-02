# Модуль 70. DTO, Jackson и форма JSON-ответа

Контроллер ([модуль 69](../module-69-spring-rest-controllers/theory.md)) принимает и отдаёт объекты. **Какие именно** объекты пересекают границу API — критически важно. Здесь разбираем паттерн **DTO** (Data Transfer Object), работу сериализатора **Jackson** и проектирование формы ответа.

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-web` (Jackson внутри). Продолжаем **Task Tracker API**. Базовый Jackson разбирался в [модуле 56](../module-56-json-jackson-dto/theory.md).

---

## Зачем DTO

**DTO** — объект, описывающий форму данных на границе API. Он **отделяет** внешний контракт от внутренней модели (сущностей БД).

```
   ВНУТРИ                          ГРАНИЦА API                   КЛИЕНТ
   ──────                          ───────────                   ──────
   Task (сущность БД)  ──маппинг──► TaskDto (DTO)   ──Jackson──► JSON
   id, title, status,              id, title,                    { "id":..., }
   passwordHash(!),                status
   internalNotes(!)                (только публичное)
```

| Без DTO (отдаём сущность) | С DTO |
|---------------------------|-------|
| протекают внутренние поля (хэши, заметки) | наружу только нужное |
| API жёстко связан со схемой БД | контракт независим от БД |
| ленивые связи Hibernate ломают сериализацию | DTO — простой плоский объект |
| нельзя менять БД, не сломав API | БД и API эволюционируют отдельно |

> Правило: **никогда не возвращайте `@Entity` напрямую** из контроллера.

---

## Request DTO ≠ Response DTO

У запроса и ответа разная форма — разделяйте их.

```java
// приходит от клиента (id присвоит сервер, статус по умолчанию)
public record CreateTaskRequest(String title, String assignee) {}

// уходит клиенту (есть id, статус, дата)
public record TaskResponse(Long id, String title, String assignee,
                           String status, LocalDateTime createdAt) {}
```

`record` (Java 16+) — идеальный DTO: неизменяемый, лаконичный, Jackson умеет его сериализовать и десериализовать из коробки.

---

## Jackson: основные аннотации

| Аннотация | Действие |
|-----------|----------|
| `@JsonProperty("name")` | переименовать поле в JSON |
| `@JsonIgnore` | не включать поле в JSON |
| `@JsonInclude(NON_NULL)` | пропускать `null`-поля |
| `@JsonFormat(pattern=...)` | формат даты/числа |
| `@JsonCreator` / `@JsonAlias` | управление десериализацией |
| `@JsonProperty(access = READ_ONLY)` | только в ответе (не читается из запроса) |

```java
public record TaskResponse(
        Long id,
        @JsonProperty("task_title") String title,        // → "task_title" в JSON
        @JsonIgnore String internalNote,                 // не попадёт в JSON
        @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
        LocalDateTime createdAt
) {}
```

`@JsonInclude` ставят на класс или настраивают глобально:

```properties
# не сериализовать null-поля во всём приложении
spring.jackson.default-property-inclusion=non_null
```

---

## Даты в JSON

По умолчанию Spring Boot сериализует `java.time` как **ISO-8601** (`2026-06-02T14:30:00`), что обычно и нужно. Управление:

```properties
# писать даты как строки ISO, а не как массивы чисел
spring.jackson.serialization.write-dates-as-timestamps=false
```

Локальный формат — через `@JsonFormat(pattern = "dd.MM.yyyy")`.

---

## Маппинг сущность ↔ DTO

Преобразование вручную (для курса) — статический метод или отдельный «маппер»:

```java
class TaskMapper {
    static TaskResponse toResponse(Task t) {
        return new TaskResponse(t.getId(), t.getTitle(), t.getAssignee(),
                                t.getStatus().name(), t.getCreatedAt());
    }
    static Task toEntity(CreateTaskRequest r) {
        Task t = new Task();
        t.setTitle(r.title());
        t.setAssignee(r.assignee());
        return t;
    }
}
```

> В продакшене это часто автоматизируют библиотекой **MapStruct** (генерирует маппер на этапе компиляции). Для курса достаточно ручного маппера.

---

## Форма ответа: обёртка (envelope) и generics

Иногда полезно возвращать данные в единой «обёртке» с метаинформацией:

```java
public record ApiResponse<T>(T data, String message, Instant timestamp) {
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(data, "OK", Instant.now());
    }
}
```

```java
@GetMapping("/{id}")
public ApiResponse<TaskResponse> getOne(@PathVariable Long id) {
    return ApiResponse.ok(mapper.toResponse(task));
}
// JSON: { "data": { "id":1, ... }, "message":"OK", "timestamp":"..." }
```

```
   Плоский ответ            Обёрнутый ответ (envelope)
   ───────────             ──────────────────────────
   { "id":1, ... }         { "data": { "id":1, ... },
                             "message": "OK",
                             "timestamp": "..." }
```

| | Плоский | Обёртка |
|-|---------|---------|
| Простота | проще | чуть сложнее |
| Метаданные (пагинация, сообщения) | негде | в обёртке |
| Единообразие ответов | нет | да |

Обе схемы валидны — выбирайте одну и придерживайтесь во всём API.

### Generics и стирание типов

`ApiResponse<TaskResponse>` сериализуется корректно: Jackson смотрит на **реальный** объект в поле `data` во время выполнения, поэтому стирание типов (type erasure) не мешает сериализации. Проблемы возможны при **десериализации** дженерик-обёртки — там нужен `TypeReference` (см. [модуль 56](../module-56-json-jackson-dto/theory.md)).

---

## Вложенные DTO и коллекции

```java
public record AssigneeDto(Long id, String name) {}

public record TaskResponse(
        Long id,
        String title,
        AssigneeDto assignee,            // вложенный объект → вложенный JSON
        List<String> tags                // коллекция → JSON-массив
) {}
```

```json
{ "id":1, "title":"X", "assignee":{ "id":7,"name":"Иван" }, "tags":["a","b"] }
```

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| В JSON «утекли» пароли/служебные поля | отдаём сущность | возвращать DTO |
| Дата как `[2026,6,2,14,30]` | `write-dates-as-timestamps=true` | выставить `false` (по умолч. в Boot уже так) |
| `null`-поля засоряют ответ | inclusion = ALWAYS | `@JsonInclude(NON_NULL)` / глобально |
| Jackson не создаёт объект из JSON | нет подходящего конструктора | `record` или конструктор + поля |
| Имя поля в JSON не нравится клиенту | дефолтное имя | `@JsonProperty("...")` |
| `LazyInitializationException` при сериализации | сериализуем ленивую связь сущности | маппить в DTO до выхода из транзакции (см. [модуль 82](../module-82-spring-data-jpa-lazy-loading/theory.md)) |

---

## Дополнительные источники

- [Jackson Annotations (wiki)](https://github.com/FasterXML/jackson-annotations/wiki/Jackson-Annotations).
- [MapStruct](https://mapstruct.org/) — генерация мапперов.
- [модуль 56](../module-56-json-jackson-dto/theory.md) — основы Jackson, `ObjectMapper`, `TypeReference`.

## Что дальше

В [модуле 71](../module-71-spring-rest-validation/theory.md) — валидация входных DTO: Bean Validation, кастомные ограничения, формирование ответа об ошибке.
