/**
 * Задача 05 — Модуль 46: LEFT JOIN
 *
 * ЗАДАНИЕ (SQL) для таблиц customers и orders (см. Task04):
 *   1) выведите ВСЕХ клиентов и их заказы — включая клиентов без
 *      заказов (LEFT JOIN), у них поля заказа будут NULL;
 *   2) найдите клиентов, у которых НЕТ заказов
 *      (LEFT JOIN + WHERE o.id IS NULL);
 *   3) количество заказов у каждого клиента, включая нулевые
 *      (LEFT JOIN + GROUP BY + COUNT по полю заказа).
 *
 * ВНИМАНИЕ:
 *   COUNT(o.id) считает только не-NULL, поэтому у клиентов без заказов
 *   будет 0 (а COUNT(*) дал бы 1 из-за строки с NULL).
 *
 * ПОДСКАЗКА:
 *   SELECT c.name, o.id FROM customers c
 *   LEFT JOIN orders o ON o.customer_id = c.id;
 *   ... WHERE o.id IS NULL;  -- клиенты без заказов
 */
public class Task05 {
    public static void main(String[] args) {
        String sql = """
            -- Напишите запросы с LEFT JOIN
            """;
        System.out.println(sql);
    }
}
