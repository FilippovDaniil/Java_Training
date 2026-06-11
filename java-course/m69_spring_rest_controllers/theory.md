# Модуль 69. Spring MVC: контроллеры и обработка запроса

Мы знаем, как должен выглядеть HTTP-запрос ([модуль 67](../m67_spring_rest_http_backend/theory.md)) и как проектировать URI ([модуль 68](../m68_spring_rest_design/theory.md)). Теперь — **как Spring MVC превращает запрос в вызов вашего метода**: что делает `DispatcherServlet`, откуда метод-обработчик берёт аргументы, как тело JSON становится объектом и обратно.

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-web`. Продолжаем **Task Tracker API**.

---

## Путь запроса внутри Spring MVC

```
   HTTP-запрос
        │
        ▼
   ┌─────────────────┐
   │ DispatcherServlet│  ← единая «входная дверь» (front controller)
   └─────────────────┘
        │ 1. HandlerMapping: найти метод по URI+методу
        │ 2. HandlerAdapter: подготовить аргументы
        │ 3. (для @RequestBody) HttpMessageConverter: JSON → объект
        ▼
   ┌─────────────────┐
   │ ваш @RestController.метод(...) │  ← бизнес-логика
   └─────────────────┘
        │ 4. (для тела ответа) HttpMessageConverter: объект → JSON
        ▼
   HTTP-ответ
```

Вы пишете только метод-обработчик — всё вокруг делает Spring.

---

## @Controller vs @RestController

| | `@Controller` | `@RestController` |
|-|---------------|-------------------|
| Что возвращает метод | имя представления (view, напр. JSP) | данные (тело ответа) |
| `@ResponseBody` | нужен на каждом методе | встроен (не нужен) |
| Применение | классические веб-страницы | REST-API (JSON/XML) |

```java
@RestController                  // = @Controller + @ResponseBody
@RequestMapping("/api/tasks")    // общий префикс пути
public class TaskController { ... }
```

---

## Маппинг методов

`@RequestMapping` — общий, плюс «короткие» аннотации под каждый HTTP-метод:

```java
@GetMapping            @PostMapping         @PutMapping
@PatchMapping          @DeleteMapping

@RequestMapping(value = "/tasks", method = RequestMethod.GET)  // длинная форма
```

Можно задавать дополнительные условия:

```java
@PostMapping(value = "/tasks",
             consumes = "application/json",   // принимаем только JSON
             produces = "application/json")   // отдаём JSON
@GetMapping(value = "/tasks", params = "status")  // только если есть параметр status
```

Шаблоны путей:

```java
@GetMapping("/tasks/{id}")            // одна переменная
@GetMapping("/tasks/{id}/comments/{cid}")  // несколько
@GetMapping("/files/{*path}")         // «жадный» хвост пути
```

---

## Источники аргументов метода-обработчика

```java
@PostMapping("/tasks/{id}")
public TaskDto handle(
        @PathVariable Long id,                       // из пути: /tasks/{id}
        @RequestParam(defaultValue="0") int page,    // из query: ?page=0
        @RequestBody CreateTaskDto body,             // из тела (JSON → объект)
        @RequestHeader("Authorization") String auth, // из заголовка
        @CookieValue(value="sid", required=false) String sid  // из cookie
) { ... }
```

| Аннотация | Источник | Пример |
|-----------|----------|--------|
| `@PathVariable` | сегмент URI | `/tasks/{id}` |
| `@RequestParam` | query-строка / форма | `?status=DONE` |
| `@RequestBody` | тело запроса (JSON) | `{ "title": ... }` |
| `@RequestHeader` | заголовок | `Authorization` |
| `@CookieValue` | cookie | `sid` |
| (объект-модель) | связывание параметров формы | `?title=X&status=NEW` → `TaskForm` |

Параметры `required` (обязателен ли) и `defaultValue` (значение по умолчанию) есть у `@RequestParam`, `@RequestHeader`, `@CookieValue`.

---

## Конвертация тела: HttpMessageConverter

`@RequestBody` и возвращаемый объект проходят через **HttpMessageConverter**. Для JSON это `MappingJackson2HttpMessageConverter` (Jackson, входит в `starter-web`).

```
   Запрос:  { "title":"X" }  ──Jackson──►  CreateTaskDto(title="X")
   Ответ:   TaskDto(...)      ──Jackson──►  { "id":1, "title":"X", ... }
```

Выбор конвертера зависит от `Content-Type` (вход) и `Accept` (выход) — это и есть content negotiation из [модуля 67](../m67_spring_rest_http_backend/theory.md).

---

## Возвращаемые значения

```java
// 1. Просто объект → 200 OK + JSON
@GetMapping("/{id}")
public TaskDto get(@PathVariable Long id) { ... }

// 2. ResponseEntity → полный контроль статуса/заголовков
@PostMapping
public ResponseEntity<TaskDto> create(@RequestBody CreateTaskDto dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
}

// 3. @ResponseStatus → фиксированный статус
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public TaskDto create2(@RequestBody CreateTaskDto dto) { ... }

// 4. void + @ResponseStatus(NO_CONTENT) → 204
@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void delete(@PathVariable Long id) { ... }
```

| Способ | Когда удобен |
|--------|--------------|
| объект | простой GET, статус всегда 200 |
| `ResponseEntity<T>` | нужно менять статус/заголовки динамически (200 vs 404) |
| `@ResponseStatus` | статус фиксирован и известен заранее |

---

## Преобразование типов и форматирование

Spring сам конвертирует строку параметра в тип аргумента (`String` → `Long`, `int`, `enum`, `LocalDate`). Для дат — `@DateTimeFormat`:

```java
@GetMapping("/tasks")
public List<TaskDto> byDate(
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from) { ... }
// запрос: /tasks?from=2026-06-02
```

`enum` связывается по имени: `?status=DONE` → `Status.DONE`. Неверное значение → `400`.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| `@RequestBody` → `null` поля | Jackson не нашёл конструктор/сеттеры | использовать `record` или поля + конструктор |
| `415 Unsupported Media Type` | нет `Content-Type: application/json` | клиент задаёт заголовок |
| `400` на отсутствующий параметр | `@RequestParam` обязателен по умолчанию | `required=false` или `defaultValue` |
| Имя `@PathVariable` не совпало | переменная пути ≠ имя аргумента | `@PathVariable("id") Long taskId` |
| Два метода на один путь конфликтуют | одинаковые URI+метод без различий | различать `params`/`consumes`/`produces` |
| `enum` из параметра падает с 400 | значение не из набора | валидация / `required=false` + проверка |

---

## Дополнительные источники

- [Spring Web MVC — Annotated Controllers](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller.html).
- [модуль 39](../m39_servlets/theory.md) — сервлеты (что под капотом `DispatcherServlet`).
- [модуль 65](../m65_spring_boot_web_config/theory.md) — введение в `@RestController` и параметры.

## Что дальше

В [модуле 70](../m70_spring_rest_dto_json/theory.md) — DTO и Jackson: форма данных на границе API, аннотации сериализации, обёртки ответа, generics.
