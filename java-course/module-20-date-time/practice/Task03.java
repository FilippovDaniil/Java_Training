/**
 * Задача 03 — Модуль 20: Арифметика дат
 *
 * ЗАДАНИЕ:
 *   Дана дата 2026-06-01. Вычислите и выведите:
 *     - дату через 90 дней;
 *     - дату 3 месяца назад;
 *     - дату ровно через 1 год;
 *     - какой день недели будет через 100 дней.
 *
 * ВНИМАНИЕ:
 *   Объекты LocalDate неизменяемы — результат каждого метода нужно
 *   сохранять (присваивать), иначе он теряется.
 *
 * ПОДСКАЗКА:
 *   date.plusDays(90), date.minusMonths(3), date.plusYears(1),
 *   date.plusDays(100).getDayOfWeek().
 */
import java.time.LocalDate;

public class Task03 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2026, 6, 1);
        // Ваш код здесь
    }
}
