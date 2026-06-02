/**
 * Задача 07 — Модуль 71: МИНИ-ПРОЕКТ «Полная валидация Task Tracker»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-validation:3.2.x (см. theory.md).
 *
 * ЦЕЛЬ: собрать боевую валидацию создания задачи — стандартные ограничения,
 *       вложенная валидация, кастомное @ValidStatus и аккуратный ответ 400
 *       с картой "поле → сообщение".
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) Кастомное ограничение @ValidPriority + PriorityValidator07:
 *        допустимы значения 1..5 (Integer); null считается валидным.
 *
 *   2) DTO:
 *        AssigneeDto07( @NotNull Long id, @NotBlank @Size(max=50) String name )
 *        CreateTaskRequest07(
 *           @NotBlank @Size(min=3, max=100) String title,
 *           @ValidStatus String status,             // кастомное (NEW/IN_PROGRESS/DONE)
 *           @ValidPriority Integer priority,         // кастомное (1..5)
 *           @Valid @NotNull AssigneeDto07 assignee   // вложенная валидация
 *        )
 *      (@ValidStatus возьмите из Task05 — здесь объявите аналогично)
 *
 *   3) Контроллер TaskController07 (@RequestMapping("/api/tasks")):
 *        POST create: @Valid @RequestBody CreateTaskRequest07 → "OK: " + title.
 *
 *   4) Обработчик ValidationHandler07 (@RestControllerAdvice):
 *        @ExceptionHandler(MethodArgumentNotValidException.class) → 400 +
 *        тело { "status":400, "fields": { поле: сообщение, ... } }.
 *
 *   Проверьте:
 *      OK:   { "title":"Кофе","status":"NEW","priority":3,"assignee":{"id":7,"name":"Иван"} } → 200
 *      FAIL: { "title":"","status":"FOO","priority":9,"assignee":{"id":7,"name":""} }
 *            → 400 со списком ошибок по полям title/status/priority/assignee.name
 *
 * АРХИТЕКТУРА:
 *
 *   [HTTP] ─► @Valid CreateTaskRequest07 ─► (ограничения: стандартные + кастомные + вложенные)
 *                       │ не прошла
 *                       ▼
 *              ValidationHandler07 → 400 { fields: {...} }
 *
 * ПОДСКАЗКА: переиспользуйте подходы Task03 (вложенность), Task05 (кастом), Task02 (@Valid).
 */
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}

// ============================================================
// Кастомные ограничения (каркасы)
// ============================================================

@Documented
// TODO: @Constraint(validatedBy = StatusValidator07.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
@interface ValidStatus {
    String message() default "Недопустимый статус";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class StatusValidator07 implements ConstraintValidator<ValidStatus, String> {
    private static final Set<String> ALLOWED = Set.of("NEW", "IN_PROGRESS", "DONE");
    @Override public boolean isValid(String v, ConstraintValidatorContext c) {
        // TODO: v == null || ALLOWED.contains(v)
        return false;
    }
}

@Documented
// TODO: @Constraint(validatedBy = PriorityValidator07.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
@interface ValidPriority {
    String message() default "Приоритет должен быть от 1 до 5";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class PriorityValidator07 implements ConstraintValidator<ValidPriority, Integer> {
    @Override public boolean isValid(Integer v, ConstraintValidatorContext c) {
        // TODO: v == null || (v >= 1 && v <= 5)
        return false;
    }
}

// ============================================================
// DTO (каркасы)
// ============================================================

record AssigneeDto07(
        /* TODO: @NotNull */ Long id,
        /* TODO: @NotBlank @Size(max = 50) */ String name
) {}

record CreateTaskRequest07(
        /* TODO: @NotBlank @Size(min = 3, max = 100) */ String title,
        /* TODO: @ValidStatus */ String status,
        /* TODO: @ValidPriority */ Integer priority,
        /* TODO: @Valid @NotNull */ AssigneeDto07 assignee
) {}

// ============================================================
// Контроллер (каркас)
// ============================================================

@RestController
@RequestMapping("/api/tasks")
class TaskController07 {

    @PostMapping
    public String create(/* TODO: @Valid */ @RequestBody CreateTaskRequest07 dto) {
        // TODO: верните "OK: " + dto.title()
        return null;
    }
}

// ============================================================
// Обработчик ошибок (каркас)
// ============================================================

// TODO: @RestControllerAdvice
class ValidationHandler07 {

    // TODO: @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handle(MethodArgumentNotValidException ex) {
        Map<String, String> fields = new HashMap<>();
        // TODO: ex.getBindingResult().getFieldErrors()
        //         .forEach(e -> fields.put(e.getField(), e.getDefaultMessage()));
        // TODO: верните ResponseEntity.badRequest().body(Map.of("status", 400, "fields", fields))
        return null;
    }
}
