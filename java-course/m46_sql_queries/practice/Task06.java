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
            -- ============================================
            -- ПОДГОТОВКА ТАБЛИЦ И ДАННЫХ
            -- ============================================
            
            CREATE TABLE IF NOT EXISTS sales (
                id         INT PRIMARY KEY AUTO_INCREMENT,
                product    VARCHAR(100) NOT NULL,
                category   VARCHAR(50),
                amount     DECIMAL(10, 2) CHECK (amount >= 0),
                sale_date  DATE
            );
            
            INSERT INTO sales (product, category, amount, sale_date) VALUES
                ('Ноутбук ASUS', 'Электроника', 45999.00, '2024-01-15'),
                ('Смартфон Xiaomi', 'Электроника', 29999.00, '2024-01-16'),
                ('Планшет Samsung', 'Электроника', 19999.00, '2024-01-17'),
                ('Наушники Sony', 'Электроника', 4999.00, '2024-01-18'),
                ('Война и мир', 'Книги', 1500.00, '2024-01-19'),
                ('1984', 'Книги', 1350.00, '2024-01-20'),
                ('Мастер и Маргарита', 'Книги', 1400.00, '2024-01-21'),
                ('Джинсы мужские', 'Одежда', 2999.00, '2024-01-22'),
                ('Куртка зимняя', 'Одежда', 4999.00, '2024-01-23'),
                ('Конструктор Lego', 'Игрушки', 2500.00, '2024-01-24');
            
            CREATE TABLE IF NOT EXISTS customers (
                id   INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(100) NOT NULL,
                city VARCHAR(50)
            );
            
            CREATE TABLE IF NOT EXISTS orders (
                id          INT PRIMARY KEY AUTO_INCREMENT,
                customer_id INT,
                amount      DECIMAL(10, 2),
                order_date  DATE,
                FOREIGN KEY (customer_id) REFERENCES customers(id)
            );
            
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
                (4, 2500.00, '2024-01-18');
            
            -- ============================================
            -- 1) ПРОДАЖИ С СУММОЙ ВЫШЕ СРЕДНЕЙ
            -- ============================================
            SELECT 
                id,
                product,
                category,
                amount,
                ROUND((SELECT AVG(amount) FROM sales), 2) AS avg_amount
            FROM sales
            WHERE amount > (SELECT AVG(amount) FROM sales)
            ORDER BY amount DESC;
            
            -- ============================================
            -- 2) КЛИЕНТЫ, У КОТОРЫХ ЕСТЬ ХОТЯ БЫ ОДИН ЗАКАЗ
            -- ============================================
            SELECT 
                id,
                name,
                city
            FROM customers
            WHERE id IN (SELECT customer_id FROM orders)
            ORDER BY name;
            
            -- ============================================
            -- 3) КЛИЕНТЫ БЕЗ ЗАКАЗОВ (NOT IN / NOT EXISTS)
            -- ============================================
            -- Вариант с NOT IN
            SELECT 
                id,
                name,
                city
            FROM customers
            WHERE id NOT IN (SELECT customer_id FROM orders)
            ORDER BY name;
            
            -- Вариант с NOT EXISTS
            SELECT 
                c.id,
                c.name,
                c.city
            FROM customers c
            WHERE NOT EXISTS (
                SELECT 1 FROM orders o WHERE o.customer_id = c.id
            )
            ORDER BY c.name;
            
            -- ============================================
            -- 4) СОЗДАНИЕ ИНДЕКСА
            -- ============================================
            -- Индекс по часто используемому столбцу
            CREATE INDEX idx_orders_customer ON orders(customer_id);
            
            -- Индекс по категории для sales
            CREATE INDEX idx_sales_category ON sales(category);
            
            -- Составной индекс
            CREATE INDEX idx_sales_category_amount ON sales(category, amount);
            
            -- ============================================
            -- 5) ПОЯСНЕНИЕ ВЛИЯНИЯ ИНДЕКСОВ
            -- ============================================
            /*
            ВЛИЯНИЕ ИНДЕКСА НА SELECT:
            - Ускоряет поиск по индексированному столбцу
            - Ускоряет сортировку (ORDER BY)
            - Ускоряет соединение таблиц (JOIN)
            - Ускоряет группировку (GROUP BY)
            - Ускоряет фильтрацию (WHERE)
            
            ВЛИЯНИЕ ИНДЕКСА НА INSERT/UPDATE/DELETE:
            - Замедляет вставку, т.к. нужно обновлять индекс
            - Замедляет обновление индексированных столбцов
            - Замедляет удаление, т.к. нужно обновлять индекс
            - Каждый дополнительный индекс увеличивает время записи
            
            РЕКОМЕНДАЦИИ:
            - Создавайте индексы на часто используемые в WHERE столбцы
            - Не создавайте слишком много индексов
            - Используйте составные индексы для частых комбинаций
            - Мониторьте использование индексов через EXPLAIN
            - Для маленьких таблиц индексы могут не давать преимущества
            */
            """;
        System.out.println(sql);
    }
}