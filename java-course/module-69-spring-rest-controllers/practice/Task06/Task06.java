/**
 * Задача 06 — Модуль 69: Преобразование типов — enum и дата в параметрах
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) byStatus(): GET /api/tasks/by-status?status=DONE
 *      Принять @RequestParam Status06 status (enum!). Spring сам сконвертирует
 *      строку "DONE" в Status06.DONE. Вернуть "Фильтр по статусу: " + status.
 *      Неверное значение (?status=FOO) → автоматически 400 Bad Request.
 *   2) createdAfter(): GET /api/tasks/created-after?from=2026-06-02
 *      Принять @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from.
 *      Вернуть "Создано после: " + from.
 *
 * ЦЕЛЬ: увидеть авто-конвертацию String → enum и String → LocalDate.
 *
 * ПОДСКАЗКА:
 *   import org.springframework.format.annotation.DateTimeFormat;
 *   import java.time.LocalDate;
 *   enum-параметр связывается ПО ИМЕНИ константы.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
