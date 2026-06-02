# Модуль 72. Обработка ошибок: единый контракт и ProblemDetail

В [модуле 71](../module-71-spring-rest-validation/theory.md) валидация бросала `400` при неверных данных. Но ошибки бывают разные: ресурс не найден (`404`), конфликт (`409`), нет прав (`403`). Чтобы клиент получал их **в одном предсказуемом формате**, в Spring есть централизованная обработка исключений и стандарт тела ошибки — **ProblemDetail** (RFC 7807).

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-web`, `spring-boot-starter-validation`. Продолжаем **Task Tracker API**.

---

## Зачем единый контракт ошибок

```
   ❌ Каждый эндпоинт отвечает по-своему:
      { "error": "not found" }
      { "msg": "Задача отсутствует", "code": 404 }
      "Task 42 not found"

   ✅ Единый формат — клиент парсит ошибки одинаково ВЕЗДЕ:
      { "type": "...", "title": "Not Found", "status": 404,
        "detail": "Задача 42 не найдена", "instance": "/api/tasks/42" }
```

Без единого контракта каждый клиент пишет особый разбор под каждый эндпоинт — это хрупко.

---

## Способ 1: ResponseStatusException

Самый быстрый — бросить исключение с нужным статусом прямо в коде:

```java
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@GetMapping("/{id}")
public TaskDto get(@PathVariable Long id) {
    return repo.find(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Задача " + id + " не найдена"));
}
```

Удобно для разовых случаев, но логика статусов размазана по контроллерам.

---

## Способ 2: @ExceptionHandler (локально в контроллере)

Метод-обработчик внутри контроллера ловит исключение и формирует ответ:

```java
@RestController
class TaskController {

    @GetMapping("/{id}")
    public TaskDto get(@PathVariable Long id) {
        if (!exists(id)) throw new TaskNotFoundException(id);
        ...
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handle(TaskNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
```

Минус: работает только в этом контроллере.

---

## Способ 3: @RestControllerAdvice (глобально)

Один класс ловит исключения со **всех** контроллеров — это и есть единый контракт:

```java
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorBody> handleNotFound(TaskNotFoundException ex) {
        var body = new ErrorBody(404, "Not Found", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(Exception.class)   // «ловушка» для всего остального → 500
    public ResponseEntity<ErrorBody> handleAny(Exception ex) {
        var body = new ErrorBody(500, "Internal Error", "Что-то пошло не так");
        return ResponseEntity.internalServerError().body(body);
    }
}
```

```
   Любой контроллер бросает исключение
              │
              ▼
   @RestControllerAdvice (глобальный перехват)
              │
              ▼
   нужный @ExceptionHandler → единое тело ошибки
```

> Порядок: ищется самый **специфичный** обработчик. `@ExceptionHandler(Exception.class)` — последний рубеж (catch-all → 500).

---

## ProblemDetail (RFC 7807) — стандарт Spring 6 / Boot 3

`ProblemDetail` — встроенный класс для тела ошибки по стандарту. Поля:

| Поле | Смысл |
|------|-------|
| `type` | URI типа проблемы (по умолчанию `about:blank`) |
| `title` | краткое название |
| `status` | HTTP-код |
| `detail` | человекочитаемое описание |
| `instance` | URI конкретного запроса |
| (свои поля) | через `setProperty(...)` |

```java
import org.springframework.http.ProblemDetail;
import org.springframework.http.HttpStatus;

@ExceptionHandler(TaskNotFoundException.class)
public ProblemDetail handle(TaskNotFoundException ex) {
    ProblemDetail pd = ProblemDetail.forStatusAndDetail(
        HttpStatus.NOT_FOUND, ex.getMessage());
    pd.setTitle("Задача не найдена");
    pd.setProperty("taskId", ex.getTaskId());     // кастомное поле
    pd.setProperty("timestamp", Instant.now());
    return pd;                                     // Spring сам отдаст со статусом 404
}
```

```json
{
  "type": "about:blank",
  "title": "Задача не найдена",
  "status": 404,
  "detail": "Задача 42 не найдена",
  "taskId": 42,
  "timestamp": "2026-06-02T10:15:30Z"
}
```

> Возвращая `ProblemDetail`, статус ответа берётся из самого `ProblemDetail` — отдельно `ResponseEntity` не нужен.

---

## Обработка ошибок валидации в едином формате

`MethodArgumentNotValidException` (из [модуля 71](../module-71-spring-rest-validation/theory.md)) тоже приводим к `ProblemDetail`:

```java
@ExceptionHandler(MethodArgumentNotValidException.class)
public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
    ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    pd.setTitle("Ошибка валидации");
    Map<String,String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors()
      .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
    pd.setProperty("errors", errors);
    return pd;
}
```

---

## Иерархия исключений приложения

Удобно завести базовый класс и наследников — обработчик ловит по типу:

```
   RuntimeException
        └── BusinessException (базовое)
              ├── TaskNotFoundException   → 404
              ├── DuplicateTaskException  → 409
              └── InvalidStatusException  → 400
```

Каждому подтипу — свой `@ExceptionHandler`, или один обработчик базового типа с маппингом «класс → статус».

---

## Сравнение способов

| Способ | Область | Когда применять |
|--------|---------|-----------------|
| `ResponseStatusException` | точечно | разовый случай, быстро |
| `@ExceptionHandler` (в контроллере) | один контроллер | специфичная обработка |
| `@RestControllerAdvice` | всё приложение | **единый контракт ошибок (основной способ)** |
| `ProblemDetail` | формат тела | стандартный, машиночитаемый ответ |

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Разные форматы ошибок на разных эндпоинтах | нет глобального обработчика | `@RestControllerAdvice` |
| Клиент видит стектрейс / внутренние детали | необработанное исключение → дефолтный 500 | ловить `Exception.class`, не раскрывать детали |
| `@ExceptionHandler(Exception.class)` перехватывает раньше специфичного | слишком общий обработчик | объявить специфичные типы — Spring выберет точнейший |
| Валидация и «бизнес»-ошибки в разном формате | обрабатываются отдельно | привести оба к `ProblemDetail` |
| Статус не тот, что в `ProblemDetail` | обернули в `ResponseEntity` с другим статусом | возвращать `ProblemDetail` напрямую |
| `200 OK` с телом `{"error":...}` | ошибку «прячут» в успешный ответ | вернуть корректный 4xx/5xx |

---

## Дополнительные источники

- [RFC 7807 — Problem Details for HTTP APIs](https://www.rfc-editor.org/rfc/rfc7807).
- [Spring: Error Responses / ProblemDetail](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-ann-rest-exceptions.html).
- [модуль 71](../module-71-spring-rest-validation/theory.md) — валидация и `MethodArgumentNotValidException`.
- [модуль 17](../module-17-exceptions/theory.md) — исключения в Java.

## Что дальше

В [модуле 73](../module-73-spring-rest-collections/theory.md) — работа с коллекциями: пагинация, сортировка, фильтрация и форма ответа для списков.
