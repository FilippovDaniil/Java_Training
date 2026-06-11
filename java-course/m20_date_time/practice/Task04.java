package m20_date_time.practice;

/**
 * Задача 04 — Модуль 20: Period и Duration
 *
 * ЗАДАНИЕ:
 *   1. Вычислите промежуток между двумя датами через Period
 *      (выведите годы, месяцы, дни) и полное число дней через
 *      ChronoUnit.DAYS.
 *   2. Вычислите длительность рабочего дня между 9:00 и 18:15
 *      через Duration (выведите часы и минуты).
 *
 * ПРИМЕР:
 *   От 2020-01-01 до 2026-06-01: 6 лет 5 мес. 0 дн. (всего 2343 дн.)
 *   Рабочий день: 9 ч 15 мин (всего 555 мин)
 *
 * ПОДСКАЗКА:
 *   Period.between(d1, d2); ChronoUnit.DAYS.between(d1, d2);
 *   Duration.between(t1, t2); duration.toHours(); duration.toMinutes().
 */
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Task04 {
    public static void main(String[] args) {
        // Ваш код здесь
    }
}
