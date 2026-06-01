/**
 * Задача 06 — Модуль 20: Сравнение дат и часовые пояса
 *
 * ЗАДАНИЕ:
 *   1. Сравните две даты (isBefore/isAfter) и выведите, какая раньше.
 *   2. Выведите текущее время в двух часовых поясах: Europe/Moscow
 *      и Asia/Tokyo (используйте ZonedDateTime).
 *
 * ПРИМЕР:
 *   2026-01-01 раньше, чем 2026-12-31
 *   Москва: 2026-06-01T14:30+03:00[Europe/Moscow]
 *   Токио:  2026-06-01T20:30+09:00[Asia/Tokyo]
 *
 * ПОДСКАЗКА:
 *   a.isBefore(b);
 *   ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
 */
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Task06 {
    public static void main(String[] args) {
        LocalDate a = LocalDate.of(2026, 1, 1);
        LocalDate b = LocalDate.of(2026, 12, 31);
        // Ваш код здесь
    }
}
