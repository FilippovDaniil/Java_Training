package m44_databases_intro.practice;

/**
 * Задача 07 — Модуль 44 (МИНИ-ПРОЕКТ): Схема интернет-магазина
 *
 * ЗАДАНИЕ (SQL):
 *   Спроектируйте и создайте схему БД небольшого интернет-магазина.
 *   Таблицы (с ключами и ограничениями):
 *     - categories (id, name);
 *     - products   (id, name, price DECIMAL, category_id → categories);
 *     - customers  (id, name, email UNIQUE);
 *     - orders     (id, customer_id → customers, created_at TIMESTAMP);
 *     - order_items(id, order_id → orders, product_id → products, qty).
 *   Затем наполните таблицы тестовыми данными (несколько категорий,
 *   товаров, клиентов, хотя бы один заказ с парой позиций) и напишите
 *   проверочные SELECT:
 *     - все товары с названием категории;
 *     - все позиции конкретного заказа.
 *
 * ЦЕЛЬ:
 *   Связать DDL (создание таблиц + внешние ключи) и DML (наполнение),
 *   получив рабочую реляционную схему.
 *
 * ПОДСКАЗКА:
 *   Создавайте таблицы в порядке зависимостей (сначала categories и
 *   customers, потом products и orders, затем order_items).
 *   JOIN-запросы подробно разбираются в модуле 46 — здесь можно
 *   использовать простой JOIN для проверки.
 */
public class Task07 {
    public static void main(String[] args) {
        String sql = """
            -- Напишите здесь полную схему магазина (CREATE), данные (INSERT)
            -- и проверочные SELECT
            """;
        System.out.println(sql);
    }
}
