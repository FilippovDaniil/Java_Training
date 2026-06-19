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
            -- ============================================
            -- СОЗДАНИЕ СХЕМЫ ИНТЕРНЕТ-МАГАЗИНА
            -- ============================================
            
            -- 1. Клиенты
            CREATE TABLE IF NOT EXISTS customers (
                id   INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(100) NOT NULL,
                city VARCHAR(50)
            );
            
            -- 2. Товары
            CREATE TABLE IF NOT EXISTS products (
                id       INT PRIMARY KEY AUTO_INCREMENT,
                name     VARCHAR(100) NOT NULL,
                category VARCHAR(50),
                price    DECIMAL(10, 2) CHECK (price >= 0)
            );
            
            -- 3. Заказы
            CREATE TABLE IF NOT EXISTS orders (
                id          INT PRIMARY KEY AUTO_INCREMENT,
                customer_id INT,
                order_date  DATE,
                FOREIGN KEY (customer_id) REFERENCES customers(id)
            );
            
            -- 4. Позиции заказов
            CREATE TABLE IF NOT EXISTS order_items (
                id         INT PRIMARY KEY AUTO_INCREMENT,
                order_id   INT,
                product_id INT,
                quantity   INT CHECK (quantity > 0),
                FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
                FOREIGN KEY (product_id) REFERENCES products(id)
            );
            
            -- ============================================
            -- НАПОЛНЕНИЕ ДАННЫМИ
            -- ============================================
            
            -- Клиенты
            INSERT INTO customers (name, city) VALUES
                ('Иван Петров', 'Москва'),
                ('Мария Смирнова', 'Санкт-Петербург'),
                ('Петр Сидоров', 'Москва'),
                ('Анна Кузнецова', 'Новосибирск'),
                ('Сергей Иванов', 'Москва'),
                ('Елена Петрова', 'Казань'),
                ('Алексей Васильев', 'Москва'),
                ('Ольга Павлова', 'Екатеринбург'),
                ('Дмитрий Новиков', 'Санкт-Петербург'),
                ('Наталья Соколова', 'Новосибирск');
            
            -- Товары
            INSERT INTO products (name, category, price) VALUES
                ('Ноутбук ASUS', 'Электроника', 45999.00),
                ('Смартфон Xiaomi', 'Электроника', 29999.00),
                ('Планшет Samsung', 'Электроника', 19999.00),
                ('Наушники Sony', 'Электроника', 4999.00),
                ('Война и мир', 'Книги', 1500.00),
                ('1984', 'Книги', 1350.00),
                ('Мастер и Маргарита', 'Книги', 1400.00),
                ('Джинсы мужские', 'Одежда', 2999.00),
                ('Куртка зимняя', 'Одежда', 4999.00),
                ('Конструктор Lego', 'Игрушки', 2500.00);
            
            -- Заказы
            INSERT INTO orders (customer_id, order_date) VALUES
                (1, '2024-01-15'),
                (1, '2024-01-20'),
                (2, '2024-01-16'),
                (3, '2024-01-17'),
                (3, '2024-01-22'),
                (4, '2024-01-18'),
                (5, '2024-01-19'),
                (5, '2024-01-23'),
                (5, '2024-01-25'),
                (6, '2024-01-26'),
                (7, '2024-01-27'),
                (8, '2024-01-28'),
                (9, '2024-01-29');
            
            -- Позиции заказов
            INSERT INTO order_items (order_id, product_id, quantity) VALUES
                (1, 1, 1),   -- Ноутбук
                (1, 5, 2),   -- Война и мир x2
                (2, 2, 1),   -- Смартфон
                (3, 3, 1),   -- Планшет
                (3, 4, 2),   -- Наушники x2
                (4, 8, 2),   -- Джинсы x2
                (4, 9, 1),   -- Куртка
                (5, 5, 1),   -- Война и мир
                (5, 6, 1),   -- 1984
                (6, 10, 1),  -- Lego
                (7, 7, 1),   -- Мастер и Маргарита
                (7, 5, 1),   -- Война и мир
                (8, 2, 1),   -- Смартфон
                (8, 4, 1),   -- Наушники
                (9, 1, 1),   -- Ноутбук
                (9, 3, 1),   -- Планшет
                (10, 6, 2),  -- 1984 x2
                (10, 7, 1),  -- Мастер и Маргарита
                (11, 9, 1),  -- Куртка
                (12, 8, 1),  -- Джинсы
                (13, 10, 1); -- Lego
            
            -- ============================================
            -- 1) ТОП-5 САМЫХ ПРОДАВАЕМЫХ ТОВАРОВ
            -- ============================================
            SELECT 
                p.id,
                p.name AS product,
                p.category,
                SUM(oi.quantity) AS total_sold,
                p.price,
                SUM(oi.quantity * p.price) AS total_revenue
            FROM products p
            INNER JOIN order_items oi ON p.id = oi.product_id
            GROUP BY p.id, p.name, p.category, p.price
            ORDER BY total_sold DESC
            LIMIT 5;
            
            -- ============================================
            -- 2) ВЫРУЧКА ПО КАЖДОЙ КАТЕГОРИИ
            -- ============================================
            SELECT 
                p.category,
                COUNT(DISTINCT oi.order_id) AS orders_count,
                SUM(oi.quantity) AS total_items_sold,
                ROUND(SUM(oi.quantity * p.price), 2) AS total_revenue,
                ROUND(AVG(oi.quantity * p.price), 2) AS avg_order_value
            FROM products p
            INNER JOIN order_items oi ON p.id = oi.product_id
            GROUP BY p.category
            ORDER BY total_revenue DESC;
            
            -- ============================================
            -- 3) КЛИЕНТЫ, ПОТРАТИВШИЕ СУММАРНО > 50000
            -- ============================================
            SELECT 
                c.id,
                c.name,
                c.city,
                COUNT(DISTINCT o.id) AS orders_count,
                SUM(oi.quantity * p.price) AS total_spent,
                ROUND(AVG(oi.quantity * p.price), 2) AS avg_order_value,
                MIN(o.order_date) AS first_order,
                MAX(o.order_date) AS last_order
            FROM customers c
            INNER JOIN orders o ON c.id = o.customer_id
            INNER JOIN order_items oi ON o.id = oi.order_id
            INNER JOIN products p ON oi.product_id = p.id
            GROUP BY c.id, c.name, c.city
            HAVING SUM(oi.quantity * p.price) > 50000
            ORDER BY total_spent DESC;
            
            -- ============================================
            -- 4) СРЕДНИЙ ЧЕК ЗАКАЗА
            -- ============================================
            SELECT 
                ROUND(AVG(order_total), 2) AS average_order_value,
                MIN(order_total) AS min_order,
                MAX(order_total) AS max_order,
                COUNT(*) AS total_orders
            FROM (
                SELECT 
                    o.id AS order_id,
                    SUM(oi.quantity * p.price) AS order_total
                FROM orders o
                INNER JOIN order_items oi ON o.id = oi.order_id
                INNER JOIN products p ON oi.product_id = p.id
                GROUP BY o.id
            ) AS order_totals;
            
            -- ============================================
            -- 5) ГОРОДА С БОЛЬШИМ КОЛИЧЕСТВОМ КЛИЕНТОВ
            -- ============================================
            SELECT 
                city,
                COUNT(*) AS customers_count,
                SUM(CASE WHEN o.id IS NOT NULL THEN 1 ELSE 0 END) AS customers_with_orders,
                SUM(CASE WHEN o.id IS NULL THEN 1 ELSE 0 END) AS customers_without_orders,
                COUNT(o.id) AS total_orders,
                ROUND(COALESCE(SUM(oi.quantity * p.price), 0), 2) AS total_revenue
            FROM customers c
            LEFT JOIN orders o ON c.id = o.customer_id
            LEFT JOIN order_items oi ON o.id = oi.order_id
            LEFT JOIN products p ON oi.product_id = p.id
            GROUP BY city
            ORDER BY customers_count DESC, total_revenue DESC;
            
            -- ============================================
            -- 6) ТОВАРЫ, КОТОРЫЕ НИ РАЗУ НЕ ЗАКАЗЫВАЛИ
            -- ============================================
            -- Вариант с LEFT JOIN + IS NULL
            SELECT 
                p.id,
                p.name,
                p.category,
                p.price
            FROM products p
            LEFT JOIN order_items oi ON p.id = oi.product_id
            WHERE oi.id IS NULL
            ORDER BY p.category, p.name;
            
            -- Вариант с NOT IN
            SELECT 
                p.id,
                p.name,
                p.category,
                p.price
            FROM products p
            WHERE p.id NOT IN (
                SELECT DISTINCT product_id 
                FROM order_items
            )
            ORDER BY p.category, p.name;
            """;
        System.out.println(sql);
    }
}
