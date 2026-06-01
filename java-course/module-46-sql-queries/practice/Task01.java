/**
 * Задача 01 — Модуль 46: Агрегатные функции
 *
 * ДАНО (таблица для всех задач модуля):
 *   sales(id, product, category, amount DECIMAL, sale_date DATE)
 *   Наполните её 10-15 строками перед началом.
 *
 * ЗАДАНИЕ (SQL):
 *   1) общее количество продаж (COUNT);
 *   2) суммарная выручка (SUM amount);
 *   3) средний, минимальный и максимальный чек (AVG, MIN, MAX);
 *   4) количество разных категорий (COUNT DISTINCT category).
 *
 * ПОДСКАЗКА:
 *   SELECT COUNT(*), SUM(amount), AVG(amount), MIN(amount), MAX(amount) FROM sales;
 *   SELECT COUNT(DISTINCT category) FROM sales;
 */
public class Task01 {
    public static void main(String[] args) {
        String sql = """
            -- Напишите запросы с агрегатными функциями
            """;
        System.out.println(sql);
    }
}
