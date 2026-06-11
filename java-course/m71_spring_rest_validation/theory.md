# Модуль 71. Валидация входных данных REST-API

DTO ([модуль 70](../m70_spring_rest_dto_json/theory.md)) описывает форму данных. Но клиент может прислать мусор: пустой заголовок, отрицательную цену, кривой email. **Bean Validation** (JSR 380 / Jakarta Validation) позволяет описать правила декларативно — аннотациями на полях DTO, а Spring проверит их **до** входа в метод контроллера.

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-web`, `spring-boot-starter-validation`. Продолжаем **Task Tracker API**.

---

## Базовые ограничения

```java
import jakarta.validation.constraints.*;

public record CreateTaskRequest(
        @NotBlank(message = "Заголовок обязателен")
        @Size(min = 3, max = 100)
        String title,

        @NotNull
        @Size(min = 2, max = 50)
        String assignee,

        @Min(1) @Max(5)
        int priority,

        @FutureOrPresent(message = "Срок не может быть в прошлом")
        LocalDate dueDate
) {}
```

| Аннотация | Проверяет |
|-----------|-----------|
| `@NotNull` | не `null` |
| `@NotBlank` | строка не пустая и не из пробелов |
| `@NotEmpty` | строка/коллекция не пустая |
| `@Size(min,max)` | длина строки/коллекции |
| `@Min` / `@Max` | границы целого |
| `@Positive` / `@PositiveOrZero` | знак числа |
| `@Email` | формат email |
| `@Pattern(regexp)` | регулярное выражение |
| `@Past` / `@Future` / `@FutureOrPresent` | дата относительно «сейчас» |

---

## Запуск проверки: @Valid

Само наличие аннотаций ничего не делает — нужно сказать Spring проверить объект, поставив `@Valid` перед `@RequestBody`:

```java
@PostMapping("/tasks")
public TaskResponse create(@Valid @RequestBody CreateTaskRequest req) { ... }
```

```
   POST /tasks { "title":"" }
        │
        ▼
   @Valid → проверка ограничений
        │
        ├─ не прошла → MethodArgumentNotValidException → 400 (без входа в метод)
        └─ прошла    → вызов метода контроллера
```

> Забыли `@Valid` → ограничения **молча игнорируются**. Самая частая ошибка.

---

## @Valid vs @Validated

| | `@Valid` (Jakarta) | `@Validated` (Spring) |
|-|--------------------|------------------------|
| Откуда | стандарт Jakarta | Spring-расширение |
| Группы валидации | нет | **есть** (`groups`) |
| Где ставят | параметр, поле (вложенная валидация) | класс (для @RequestParam/@PathVariable) и для групп |

---

## Вложенная валидация

Чтобы проверять и вложенный объект, ставьте `@Valid` на поле:

```java
public record CreateTaskRequest(
        @NotBlank String title,
        @Valid @NotNull AssigneeDto assignee   // @Valid — провалидировать и вложенный объект
) {}

public record AssigneeDto(
        @NotNull Long id,
        @NotBlank String name
) {}
```

Без `@Valid` на поле `assignee` его внутренние ограничения **не проверяются**.

---

## Группы валидации

Одно и то же DTO может проверяться по-разному при создании и обновлении. Группы — это маркер-интерфейсы:

```java
interface OnCreate {}
interface OnUpdate {}

public record TaskDto(
        @Null(groups = OnCreate.class)        // при создании id ДОЛЖЕН быть null
        @NotNull(groups = OnUpdate.class)     // при обновлении id ОБЯЗАТЕЛЕН
        Long id,

        @NotBlank(groups = {OnCreate.class, OnUpdate.class})
        String title
) {}
```

```java
@PostMapping
public ... create(@Validated(OnCreate.class) @RequestBody TaskDto dto) { ... }

@PutMapping("/{id}")
public ... update(@Validated(OnUpdate.class) @RequestBody TaskDto dto) { ... }
```

---

## Кастомное ограничение

Когда стандартных аннотаций мало — создают свою. Две части: **аннотация** + **валидатор**.

```java
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StatusValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidStatus {
    String message() default "Недопустимый статус";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

```java
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;

public class StatusValidator implements ConstraintValidator<ValidStatus, String> {
    private static final Set<String> ALLOWED = Set.of("NEW", "IN_PROGRESS", "DONE");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || ALLOWED.contains(value);  // null оставляем @NotNull
    }
}
```

Применение: `@ValidStatus String status`.

---

## Межполевая (cross-field) валидация

Проверка, затрагивающая несколько полей (`startDate ≤ endDate`), вешается на **класс**:

```java
@StartBeforeEnd   // кастомное ограничение уровня класса
public record Period(LocalDate startDate, LocalDate endDate) {}
```

Валидатор реализует `ConstraintValidator<StartBeforeEnd, Period>` и сравнивает оба поля.

---

## Где находятся ошибки

При провале `@Valid` Spring бросает `MethodArgumentNotValidException`. Из него достают ошибки по полям:

```java
ex.getBindingResult().getFieldErrors()
  .forEach(e -> System.out.println(e.getField() + ": " + e.getDefaultMessage()));
```

Формирование красивого ответа об ошибке (единый контракт, `ProblemDetail`) — тема [модуля 72](../m72_spring_rest_error_handling/theory.md). Здесь достаточно вернуть `400` с картой `поле → сообщение`.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Ограничения не срабатывают | нет `@Valid` / нет `starter-validation` | добавить и аннотацию, и зависимость |
| Вложенный объект не проверяется | нет `@Valid` на поле | поставить `@Valid` на поле-объект |
| `@NotNull` на примитиве бесполезен | `int` не может быть `null` | использовать `Integer` или `@Min/@Positive` |
| Группы не работают с `@Valid` | группы понимает только `@Validated` | заменить на `@Validated(Group.class)` |
| `@RequestParam` не валидируется | нужен `@Validated` на классе контроллера | добавить `@Validated` на класс + ограничение на параметр |
| Кастомный валидатор не вызывается | не указан `validatedBy` / неверный `@Target` | проверить `@Constraint(validatedBy=...)` и таргеты |

---

## Дополнительные источники

- [Jakarta Bean Validation — спецификация](https://beanvalidation.org/).
- [Hibernate Validator (референс-реализация)](https://hibernate.org/validator/).
- [модуль 65](../m65_spring_boot_web_config/theory.md) — введение в `@Valid` и базовые ограничения.

## Что дальше

В [модуле 72](../m72_spring_rest_error_handling/theory.md) — единый контракт ошибок: `@RestControllerAdvice`, `@ExceptionHandler` и стандарт `ProblemDetail` (RFC 7807).
