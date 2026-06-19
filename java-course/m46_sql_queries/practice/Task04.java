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
            -- ============================================
            -- СОЗДАНИЕ ТАБЛИЦ customers И orders
            -- ============================================
            
            -- 1. Таблица клиентов
            CREATE TABLE IF NOT EXISTS customers (
                id   INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(100) NOT NULL,
                city VARCHAR(50)
            );
            
            -- 2. Таблица заказов
            CREATE TABLE IF NOT EXISTS orders (
                id          INT PRIMARY KEY AUTO_INCREMENT,
                customer_id INT,
                amount      DECIMAL(10, 2) CHECK (amount >= 0),
                order_date  DATE,
                FOREIGN KEY (customer_id) REFERENCES customers(id)
            );
            
            -- 3. Наполнение данными
            INSERT INTO customers (name, city) VALUES
                ('Иван Петров', 'Москва'),
                ('Мария Смирнова', 'Санкт-Петербург'),
                ('Петр Сидоров', 'Москва'),
                ('Анна Кузнецова', 'Новосибирск'),
                ('Сергей Иванов', 'Москва'),
                ('Елена Петрова', 'Казань');
            
            INSERT INTO orders (customer_id, amount, order_date) VALUES
                (1, 1500.00, '2024-01-15'),
                (1, 2000.00, '2024-01-20'),
                (2, 3000.00, '2024-01-16'),
                (3, 4500.00, '2024-01-17'),
                (3, 1200.00, '2024-01-22'),
                (4, 2500.00, '2024-01-18'),
                (5, 800.00, '2024-01-19'),
                (5, 900.00, '2024-01-23'),
                (5, 1100.00, '2024-01-25'),
                (1, 1800.00, '2024-01-26');
            
            -- ============================================
            -- 1) ЗАКАЗЫ С ИМЕНЕМ КЛИЕНТА (INNER JOIN)
            -- ============================================
            SELECT 
                o.id AS order_id,
                c.name AS customer_name,
                o.amount,
                o.order_date
            FROM orders o
            INNER JOIN customers c ON o.customer_id = c.id
            ORDER BY o.order_date;
            
            -- ============================================
            -- 2) ЗАКАЗЫ КЛИЕНТОВ ИЗ КОНКРЕТНОГО ГОРОДА
            -- ============================================
            -- Заказы клиентов из Москвы
            SELECT 
                o.id AS order_id,
                c.name AS customer_name,
                c.city,
                o.amount,
                o.order_date
            FROM orders o
            INNER JOIN customers c ON o.customer_id = c.id
            WHERE c.city = 'Москва'
            ORDER BY o.amount DESC;
            
            -- ============================================
            -- 3) СУММАРНАЯ СУММА ЗАКАЗОВ ПО КАЖДОМУ КЛИЕНТУ
            -- ============================================
            SELECT 
                c.id AS customer_id,
                c.name AS customer_name,
                COUNT(o.id) AS orders_count,
                SUM(o.amount) AS total_spent,
                ROUND(AVG(o.amount), 2) AS avg_order,
                MIN(o.amount) AS min_order,
                MAX(o.amount) AS max_order
            FROM customers c
            INNER JOIN orders o ON c.id = o.customer_id
            GROUP BY c.id, c.name
            ORDER BY total_spent DESC;
            """;
        System.out.println(sql);
    }
}
