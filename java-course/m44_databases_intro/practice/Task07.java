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
            -- ============================================
            -- СХЕМА ИНТЕРНЕТ-МАГАЗИНА
            -- ============================================
            
            -- 1. СОЗДАНИЕ ТАБЛИЦ
            -- ============================================
            
            -- 1.1 Категории товаров
            CREATE TABLE IF NOT EXISTS categories (
                id   INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(100) NOT NULL UNIQUE,
                description TEXT
            );
            
            -- 1.2 Товары
            CREATE TABLE IF NOT EXISTS products (
                id          INT PRIMARY KEY AUTO_INCREMENT,
                name        VARCHAR(200) NOT NULL,
                price       DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
                category_id INT,
                description TEXT,
                stock       INT DEFAULT 0 CHECK (stock >= 0),
                FOREIGN KEY (category_id) REFERENCES categories(id)
            );
            
            -- 1.3 Клиенты
            CREATE TABLE IF NOT EXISTS customers (
                id         INT PRIMARY KEY AUTO_INCREMENT,
                name       VARCHAR(100) NOT NULL,
                email      VARCHAR(100) NOT NULL UNIQUE,
                phone      VARCHAR(20),
                address    TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            
            -- 1.4 Заказы
            CREATE TABLE IF NOT EXISTS orders (
                id          INT PRIMARY KEY AUTO_INCREMENT,
                customer_id INT NOT NULL,
                order_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                status      VARCHAR(50) DEFAULT 'new',
                total       DECIMAL(10, 2) DEFAULT 0,
                FOREIGN KEY (customer_id) REFERENCES customers(id)
            );
            
            -- 1.5 Позиции заказов
            CREATE TABLE IF NOT EXISTS order_items (
                id         INT PRIMARY KEY AUTO_INCREMENT,
                order_id   INT NOT NULL,
                product_id INT NOT NULL,
                quantity   INT NOT NULL CHECK (quantity > 0),
                price      DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
                FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
                FOREIGN KEY (product_id) REFERENCES products(id)
            );
            
            -- ============================================
            -- 2. НАПОЛНЕНИЕ ДАННЫМИ
            -- ============================================
            
            -- 2.1 Категории
            INSERT INTO categories (name, description) VALUES
                ('Электроника', 'Телефоны, ноутбуки, планшеты'),
                ('Книги', 'Художественная и техническая литература'),
                ('Одежда', 'Мужская и женская одежда'),
                ('Дом и сад', 'Товары для дома и сада'),
                ('Игрушки', 'Детские игрушки и игры');
            
            -- 2.2 Товары
            INSERT INTO products (name, price, category_id, description, stock) VALUES
                ('Ноутбук ASUS', 45999.00, 1, 'Ноутбук для работы и учебы', 10),
                ('Смартфон Xiaomi', 29999.00, 1, 'Смартфон с отличной камерой', 25),
                ('Планшет Samsung', 19999.00, 1, 'Планшет для развлечений', 15),
                ('Война и мир', 1500.00, 2, 'Роман Льва Толстого', 50),
                ('1984', 1350.00, 2, 'Роман-антиутопия Джорджа Оруэлла', 30),
                ('Мастер и Маргарита', 1400.00, 2, 'Роман Михаила Булгакова', 20),
                ('Джинсы мужские', 2999.00, 3, 'Классические джинсы', 40),
                ('Куртка женская', 4999.00, 3, 'Зимняя куртка', 15),
                ('Набор кастрюль', 3999.00, 4, 'Набор из 5 кастрюль', 10),
                ('Конструктор Lego', 2500.00, 5, 'Конструктор для детей 6+', 30);
            
            -- 2.3 Клиенты
            INSERT INTO customers (name, email, phone, address) VALUES
                ('Иван Петров', 'ivan@mail.com', '+7 999 123-45-67', 'Москва, ул. Ленина, д. 10'),
                ('Мария Смирнова', 'maria@mail.com', '+7 999 234-56-78', 'Москва, ул. Пушкина, д. 5'),
                ('Петр Сидоров', 'petr@mail.com', '+7 999 345-67-89', 'Санкт-Петербург, Невский пр., д. 15'),
                ('Анна Кузнецова', 'anna@mail.com', '+7 999 456-78-90', 'Новосибирск, ул. Советская, д. 20');
            
            -- 2.4 Заказы
            INSERT INTO orders (customer_id, status, total) VALUES
                (1, 'completed', 0),  -- заказ #1
                (2, 'processing', 0),  -- заказ #2
                (3, 'new', 0),         -- заказ #3
                (1, 'completed', 0);   -- заказ #4
            
            -- 2.5 Позиции заказов
            -- Заказ №1 (Иван)
            INSERT INTO order_items (order_id, product_id, quantity, price) VALUES
                (1, 1, 1, 45999.00),   -- Ноутбук ASUS
                (1, 5, 2, 1350.00),    -- 1984 x2
                (1, 10, 1, 2500.00);   -- Lego
            
            -- Заказ №2 (Мария)
            INSERT INTO order_items (order_id, product_id, quantity, price) VALUES
                (2, 2, 1, 29999.00),   -- Xiaomi
                (2, 3, 1, 19999.00),   -- Samsung
                (2, 4, 1, 1500.00);    -- Война и мир
            
            -- Заказ №3 (Петр)
            INSERT INTO order_items (order_id, product_id, quantity, price) VALUES
                (3, 7, 2, 2999.00),    -- Джинсы x2
                (3, 9, 1, 3999.00);    -- Набор кастрюль
            
            -- Заказ №4 (Иван, повторный)
            INSERT INTO order_items (order_id, product_id, quantity, price) VALUES
                (4, 6, 1, 1400.00),    -- Мастер и Маргарита
                (4, 8, 1, 4999.00);    -- Куртка
            
            -- 2.6 Обновление общей суммы заказов
            UPDATE orders o 
            SET total = (
                SELECT SUM(oi.quantity * oi.price) 
                FROM order_items oi 
                WHERE oi.order_id = o.id
            );
            
            -- ============================================
            -- 3. ПРОВЕРОЧНЫЕ SELECT-ЗАПРОСЫ
            -- ============================================
            
            -- 3.1 Все товары с названиями категорий
            SELECT 
                p.id,
                p.name AS product,
                p.price,
                c.name AS category,
                p.stock,
                p.description
            FROM products p
            LEFT JOIN categories c ON p.category_id = c.id
            ORDER BY c.name, p.name;
            
            -- 3.2 Все позиции конкретного заказа (заказ #1)
            SELECT 
                oi.id,
                p.name AS product,
                oi.quantity,
                oi.price AS unit_price,
                (oi.quantity * oi.price) AS total_price
            FROM order_items oi
            JOIN products p ON oi.product_id = p.id
            WHERE oi.order_id = 1;
            
            -- 3.3 Детальная информация о заказе #1
            SELECT 
                o.id AS order_id,
                c.name AS customer,
                c.email,
                o.order_date,
                o.status,
                o.total AS order_total,
                COUNT(oi.id) AS items_count
            FROM orders o
            JOIN customers c ON o.customer_id = c.id
            LEFT JOIN order_items oi ON o.id = oi.order_id
            WHERE o.id = 1
            GROUP BY o.id, c.name, c.email, o.order_date, o.status, o.total;
            
            -- ============================================
            -- 4. ДОПОЛНИТЕЛЬНЫЕ ПОЛЕЗНЫЕ ЗАПРОСЫ
            -- ============================================
            
            -- 4.1 Статистика по клиентам (количество заказов и сумма)
            SELECT 
                c.id,
                c.name,
                COUNT(o.id) AS orders_count,
                COALESCE(SUM(o.total), 0) AS total_spent
            FROM customers c
            LEFT JOIN orders o ON c.id = o.customer_id
            GROUP BY c.id, c.name
            ORDER BY total_spent DESC;
            
            -- 4.2 Самые продаваемые товары
            SELECT 
                p.id,
                p.name AS product,
                SUM(oi.quantity) AS total_sold,
                SUM(oi.quantity * oi.price) AS total_revenue
            FROM products p
            JOIN order_items oi ON p.id = oi.product_id
            GROUP BY p.id, p.name
            ORDER BY total_revenue DESC
            LIMIT 5;
            
            -- 4.3 Товары, которых не хватает на складе
            SELECT 
                p.id,
                p.name AS product,
                p.stock,
                p.price
            FROM products p
            WHERE p.stock < 5
            ORDER BY p.stock;
            
            -- 4.4 Заказы по статусам
            SELECT 
                status,
                COUNT(*) AS count,
                SUM(total) AS total_amount
            FROM orders
            GROUP BY status
            ORDER BY status;
            
            -- 4.5 Полная детализация заказа (все поля)
            SELECT 
                o.id AS order_id,
                c.name AS customer,
                c.email,
                c.phone,
                o.order_date,
                o.status,
                p.name AS product,
                oi.quantity,
                oi.price,
                (oi.quantity * oi.price) AS line_total
            FROM orders o
            JOIN customers c ON o.customer_id = c.id
            JOIN order_items oi ON o.id = oi.order_id
            JOIN products p ON oi.product_id = p.id
            WHERE o.id = 1
            ORDER BY oi.id;
            """;
        System.out.println(sql);
    }
}
