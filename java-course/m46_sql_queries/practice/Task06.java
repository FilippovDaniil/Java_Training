package m46_sql_queries.practice;

/**
 * Задача 06 — Модуль 46: Подзапросы и индексы
 *
 * ЗАДАНИЕ (SQL):
 *   Подзапросы (для sales или orders):
 *     1) продажи с суммой выше средней (amount > подзапрос AVG);
 *     2) клиенты, у которых есть хотя бы один заказ
 *        (id IN (SELECT customer_id FROM orders));
 *     3) клиенты БЕЗ заказов (NOT IN / NOT EXISTS).
 *   Индексы:
 *     4) создайте индекс по столбцу, по которому часто фильтруете
 *        (например, orders.customer_id или sales.category);
 *     5) в комментарии поясните, как индекс влияет на SELECT и на INSERT.
 *
 * ПОДСКАЗКА:
 *   SELECT * FROM sales WHERE amount > (SELECT AVG(amount) FROM sales);
 *   CREATE INDEX idx_orders_customer ON orders(customer_id);
 */
public class Task06 {
    public static void main(String[] args) {
        String sql = """
            -- Напишите подзапросы и создание индекса
            """;
        System.out.println(sql);
    }
}
