/**
 * Задача 05 — Модуль 71: Кастомное ограничение @ValidStatus
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-validation:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Создайте собственное ограничение, проверяющее, что статус — из допустимого набора.
 *   1) Аннотация @ValidStatus уже объявлена ниже — допишите её атрибуты
 *      (message / groups / payload) и @Constraint(validatedBy = StatusValidator05.class).
 *   2) StatusValidator05 реализует ConstraintValidator<ValidStatus, String>:
 *        - метод isValid: вернуть true, если value == null ИЛИ value ∈ {NEW, IN_PROGRESS, DONE}.
 *   3) В StatusDto05 поле status пометьте @ValidStatus.
 *   4) В контроллере create: @Valid @RequestBody.
 *
 *   Проверьте:
 *      { "status":"DONE" } → 200
 *      { "status":"FOO" }  → 400
 *
 * ПОДСКАЗКА:
 *   null оставляем валидным — за «обязательность» отвечает отдельный @NotNull.
 *   import jakarta.validation.*; import jakarta.validation.constraints.*;
 */
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.Valid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.*;
import java.util.Set;

@SpringBootApplication
public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}

// --- Аннотация ограничения (каркас) ---
@Documented
// TODO: @Constraint(validatedBy = StatusValidator05.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
@interface ValidStatus {
    String message() default "Недопустимый статус";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

// --- Валидатор (каркас) ---
class StatusValidator05 implements ConstraintValidator<ValidStatus, String> {
    private static final Set<String> ALLOWED = Set.of("NEW", "IN_PROGRESS", "DONE");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // TODO: вернуть value == null || ALLOWED.contains(value)
        return false;
    }
}

// --- DTO с кастомным ограничением ---
record StatusDto05(/* TODO: @ValidStatus */ String status) {}

@RestController
@RequestMapping("/api/tasks")
class CustomValidationController05 {

    @PostMapping("/status")
    public String create(/* TODO: @Valid */ @RequestBody StatusDto05 dto) {
        // TODO: верните "Статус принят: " + dto.status()
        return null;
    }
}
