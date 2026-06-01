/**
 * Задача 02 — Модуль 46: GROUP BY
 *
 * ЗАДАНИЕ (SQL) для таблицы sales (см. Task01):
 *   1) выручка по каждой категории (SUM по группам);
 *   2) количество продаж по каждой категории;
 *   3) средний чек по каждой категории, отсортированный по убыванию;
 *   4) выручка по каждому продукту.
 *
 * ПОДСКАЗКА:
 *   SELECT category, SUM(amount) FROM sales GROUP BY category;
 *   SELECT category, AVG(amount) AS avg_check FROM sales
 *   GROUP BY category ORDER BY avg_check DESC;
 */
public class Task02 {
    public static void main(String[] args) {
        String sql = """
            -- Напишите запросы с GROUP BY
            """;
        System.out.println(sql);
    }
}
