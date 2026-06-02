/**
 * Задача 01 — Модуль 71: Базовые ограничения на DTO
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-validation:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Разметьте поля CreateTaskRequest01 ограничениями:
 *     - title:    @NotBlank, @Size(min = 3, max = 100)
 *     - assignee: @NotNull, @Size(min = 2, max = 50)
 *     - priority: @Min(1), @Max(5)
 *     - dueDate:  @FutureOrPresent (срок не в прошлом)
 *   (метод create вы реализуете в Task02 — здесь только сами ограничения)
 *
 * ЦЕЛЬ: научиться декларативно описывать правила корректности входных данных.
 *
 * ПОДСКАЗКА:
 *   import jakarta.validation.constraints.*;
 *   import java.time.LocalDate;
 *   @FutureOrPresent работает с java.time.LocalDate.
 */
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class Task01 {
    public static void main(String[] args) {
        System.out.println("Разметьте CreateTaskRequest01 ограничениями (см. JavaDoc). " +
                "Проверка сработает в контроллере с @Valid — см. Task02.");
    }
}

record CreateTaskRequest01(
        /* TODO: @NotBlank @Size(min = 3, max = 100) */ String title,
        /* TODO: @NotNull @Size(min = 2, max = 50) */ String assignee,
        /* TODO: @Min(1) @Max(5) */ int priority,
        /* TODO: @FutureOrPresent */ LocalDate dueDate
) {}
