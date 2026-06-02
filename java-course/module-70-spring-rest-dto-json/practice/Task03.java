/**
 * Задача 03 — Модуль 70: Форматирование даты в JSON (@JsonFormat)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) В TaskWithDate03 поле createdAt (LocalDateTime) разметьте
 *      @JsonFormat(pattern = "dd.MM.yyyy HH:mm") — чтобы дата выводилась как "02.06.2026 14:30".
 *   2) Поле dueDate (LocalDate) разметьте @JsonFormat(pattern = "yyyy-MM-dd").
 *   3) Метод getOne вернёт объект с фиксированными датами.
 *
 * ОЖИДАЕМЫЙ JSON (примерно):
 *   { "id":1, "title":"X", "createdAt":"02.06.2026 14:30", "dueDate":"2026-06-10" }
 *
 * СРАВНЕНИЕ: уберите @JsonFormat и посмотрите формат по умолчанию (ISO-8601).
 *
 * ПОДСКАЗКА:
 *   import com.fasterxml.jackson.annotation.JsonFormat;
 *   import java.time.LocalDate; import java.time.LocalDateTime;
 */
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}

record TaskWithDate03(
        Long id,
        String title,
        /* TODO: @JsonFormat(pattern = "dd.MM.yyyy HH:mm") */ LocalDateTime createdAt,
        /* TODO: @JsonFormat(pattern = "yyyy-MM-dd") */ LocalDate dueDate
) {}

@RestController
@RequestMapping("/api/tasks")
class DateController03 {

    @GetMapping("/1")
    public TaskWithDate03 getOne() {
        return new TaskWithDate03(1L, "Купить кофе",
                LocalDateTime.of(2026, 6, 2, 14, 30),
                LocalDate.of(2026, 6, 10));
    }
}
