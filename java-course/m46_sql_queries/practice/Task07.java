package m46_sql_queries.practice;

/**
 * Задача 07 — Модуль 46 (МИНИ-ПРОЕКТ): Аналитика интернет-магазина
 *
 * ДАНО (создайте и наполните схему):
 *   customers(id, name, city)
 *   products(id, name, category, price)
 *   orders(id, customer_id, order_date)
 *   order_items(id, order_id, product_id, quantity)
 *
 * ЗАДАНИЕ (SQL): напишите аналитические запросы, объединяющие
 *   JOIN, GROUP BY, HAVING, агрегаты и подзапросы:
 *     1) топ-5 самых продаваемых товаров (по суммарному quantity);
 *     2) выручка по каждой категории
 *        (нужно умножить price * quantity и просуммировать);
 *     3) клиенты, потратившие суммарно больше 50000 (HAVING);
 *     4) средний чек заказа (сумма позиций заказа, затем среднее);
 *     5) города, в которых больше всего клиентов;
 *     6) товары, которые ни разу не заказывали
 *        (LEFT JOIN order_items ... IS NULL, или NOT IN).
 *
 * ЦЕЛЬ:
 *   Собрать реальные бизнес-отчёты из связанных таблиц — ключевой
 *   навык работы с БД.
 *
 * ПОДСКАЗКА:
 *   Для выручки соединяйте order_items с products:
 *   SUM(oi.quantity * p.price). Стройте запросы по одному и проверяйте.
 */
public class Task07 {
    public static void main(String[] args) {
        String sql = """
            -- Создайте схему, наполните данными и напишите аналитические запросы
            """;
        System.out.println(sql);
    }
}
