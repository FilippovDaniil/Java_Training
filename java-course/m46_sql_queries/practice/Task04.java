package m46_sql_queries.practice;

/**
 * Задача 04 — Модуль 46: INNER JOIN
 *
 * ДАНО (две связанные таблицы):
 *   customers(id, name, city)
 *   orders(id, customer_id, amount, order_date)
 *   где orders.customer_id ссылается на customers.id.
 *
 * ЗАДАНИЕ (SQL):
 *   1) выведите заказы вместе с именем клиента (INNER JOIN);
 *   2) выведите заказы клиентов из конкретного города;
 *   3) суммарную сумму заказов по каждому клиенту (JOIN + GROUP BY).
 *
 * ПОДСКАЗКА:
 *   SELECT o.id, c.name, o.amount
 *   FROM orders o INNER JOIN customers c ON o.customer_id = c.id;
 *   Используйте псевдонимы таблиц (o, c) для краткости.
 */
public class Task04 {
    public static void main(String[] args) {
        String sql = """
            -- Создайте таблицы customers/orders, наполните и напишите INNER JOIN
            """;
        System.out.println(sql);
    }
}
