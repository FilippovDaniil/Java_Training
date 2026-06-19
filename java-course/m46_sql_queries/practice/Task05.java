package m46_sql_queries.practice;

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
            -- ============================================
            -- ПОДГОТОВКА ТАБЛИЦ customers И orders
            -- ============================================
            
            CREATE TABLE IF NOT EXISTS customers (
                id   INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(100) NOT NULL,
                city VARCHAR(50)
            );
            
            CREATE TABLE IF NOT EXISTS orders (
                id          INT PRIMARY KEY AUTO_INCREMENT,
                customer_id INT,
                amount      DECIMAL(10, 2) CHECK (amount >= 0),
                order_date  DATE,
                FOREIGN KEY (customer_id) REFERENCES customers(id)
            );
            
            INSERT INTO customers (name, city) VALUES
                ('Иван Петров', 'Москва'),
                ('Мария Смирнова', 'Санкт-Петербург'),
                ('Петр Сидоров', 'Москва'),
                ('Анна Кузнецова', 'Новосибирск'),
                ('Сергей Иванов', 'Москва'),
                ('Елена Петрова', 'Казань'),
                ('Алексей Васильев', 'Москва'),     -- без заказов
                ('Ольга Павлова', 'Екатеринбург');  -- без заказов
            
            INSERT INTO orders (customer_id, amount, order_date) VALUES
                (1, 1500.00, '2024-01-15'),
                (1, 2000.00, '2024-01-20'),
                (2, 3000.00, '2024-01-16'),
                (3, 4500.00, '2024-01-17'),
                (3, 1200.00, '2024-01-22'),
                (4, 2500.00, '2024-01-18'),
                (5, 800.00, '2024-01-19'),
                (5, 900.00, '2024-01-23'),
                (5, 1100.00, '2024-01-25');
            
            -- ============================================
            -- 1) ВСЕ КЛИЕНТЫ И ИХ ЗАКАЗЫ (LEFT JOIN)
            -- ============================================
            SELECT 
                c.id AS customer_id,
                c.name AS customer,
                c.city,
                o.id AS order_id,
                o.amount,
                o.order_date
            FROM customers c
            LEFT JOIN orders o ON c.id = o.customer_id
            ORDER BY c.id, o.order_date;
            
            -- ============================================
            -- 2) КЛИЕНТЫ БЕЗ ЗАКАЗОВ
            -- ============================================
            SELECT 
                c.id,
                c.name,
                c.city
            FROM customers c
            LEFT JOIN orders o ON c.id = o.customer_id
            WHERE o.id IS NULL
            ORDER BY c.name;
            
            -- ============================================
            -- 3) КОЛИЧЕСТВО ЗАКАЗОВ У КАЖДОГО КЛИЕНТА
            -- ============================================
            SELECT 
                c.id,
                c.name,
                c.city,
                COUNT(o.id) AS order_count,
                COALESCE(SUM(o.amount), 0) AS total_spent,
                COALESCE(ROUND(AVG(o.amount), 2), 0) AS avg_amount,
                COALESCE(MIN(o.amount), 0) AS min_amount,
                COALESCE(MAX(o.amount), 0) AS max_amount
            FROM customers c
            LEFT JOIN orders o ON c.id = o.customer_id
            GROUP BY c.id, c.name, c.city
            ORDER BY order_count DESC, total_spent DESC;
            """;
        System.out.println(sql);
    }
}
