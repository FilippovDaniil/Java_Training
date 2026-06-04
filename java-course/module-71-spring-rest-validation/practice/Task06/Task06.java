/**
 * Задача 06 — Модуль 71: Межполевая валидация (start ≤ end) на уровне класса
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-validation:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Проверка, затрагивающая ДВА поля сразу: дата начала не позже даты конца.
 *   1) Аннотация @StartBeforeEnd объявлена на УРОВНЕ ТИПА (@Target TYPE).
 *      Допишите @Constraint(validatedBy = PeriodValidator06.class).
 *   2) PeriodValidator06 реализует ConstraintValidator<StartBeforeEnd, PeriodDto06>:
 *        isValid: true, если start == null || end == null || !start.isAfter(end).
 *   3) PeriodDto06 пометьте @StartBeforeEnd (на record).
 *   4) В контроллере create: @Valid @RequestBody.
 *
 *   Проверьте:
 *      { "start":"2026-06-01", "end":"2026-06-10" } → 200
 *      { "start":"2026-06-10", "end":"2026-06-01" } → 400
 *
 * ЦЕЛЬ: понять, что межполевые правила вешаются на КЛАСС, а не на поле.
 *
 * ПОДСКАЗКА: валидатор получает весь объект PeriodDto06 и сам сравнивает поля.
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
import java.time.LocalDate;

public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
