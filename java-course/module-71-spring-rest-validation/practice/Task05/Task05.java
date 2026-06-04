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

public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}
