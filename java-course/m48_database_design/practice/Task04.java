package m48_database_design.practice;

/**
 * Задача 04 — Модуль 48: Приведение ко 2НФ
 *
 * ДАНО (в 1НФ, но не во 2НФ):
 *   order_items(order_id, product_id, product_name, product_price, quantity)
 *   Составной ключ: (order_id, product_id).
 *   Проблема: product_name и product_price зависят только от product_id
 *   (части ключа) → частичная зависимость.
 *
 * ЗАДАНИЕ:
 *   1) объясните в комментарии частичную зависимость;
 *   2) приведите ко 2НФ: вынесите атрибуты товара в отдельную таблицу
 *      products(product_id, product_name, product_price);
 *   3) напишите CREATE TABLE для результата.
 *
 * ПОДСКАЗКА:
 *   После нормализации order_items хранит только (order_id, product_id,
 *   quantity), а данные о товаре — в products.
 */
public class Task04 {
    public static void main(String[] args) {
        String sql = """
            -- ============================================
            -- ПРИВЕДЕНИЕ КО 2НФ (ВТОРАЯ НОРМАЛЬНАЯ ФОРМА)
            -- ============================================
            
            /*
            ================================================
            ЧАСТЬ 1: АНАЛИЗ НАРУШЕНИЯ 2НФ
            ================================================
            
            ИСХОДНАЯ ТАБЛИЦА (В 1НФ, НО НЕ ВО 2НФ):
            order_items(order_id, product_id, product_name, product_price, quantity)
            
            СОСТАВНОЙ ПЕРВИЧНЫЙ КЛЮЧ: (order_id, product_id)
            
            ДАННЫЕ:
            order_id | product_id | product_name | product_price | quantity
            ---------|------------|--------------|---------------|----------
            1        | 101        | Ноутбук      | 50000.00      | 2
            1        | 102        | Мышь         | 1500.00       | 5
            2        | 101        | Ноутбук      | 50000.00      | 1
            
            ЧАСТИЧНАЯ ЗАВИСИМОСТЬ:
            product_name и product_price зависят только от product_id,
            а не от полного составного ключа (order_id, product_id)
            
            Почему это проблема?
            - Избыточность: название и цена повторяются для каждого заказа
            - Аномалии обновления: нужно обновлять цену во всех строках
            - Аномалии вставки: нельзя добавить товар без заказа
            - Аномалии удаления: при удалении заказа теряется информация о товаре
            
            ОПРЕДЕЛЕНИЕ 2НФ:
            Таблица находится во 2НФ, если:
            1. Она уже в 1НФ
            2. Все неключевые атрибуты зависят от ВСЕГО первичного ключа
               (нет частичных зависимостей)
            */
            
            /*
            ================================================
            ЧАСТЬ 2: ПРИВЕДЕНИЕ КО 2НФ (РЕШЕНИЕ)
            ================================================
            
            СПОСОБ РЕШЕНИЯ:
            1. Создать отдельную таблицу products для данных о товарах
            2. В order_items оставить только (order_id, product_id, quantity)
            3. Установить внешний ключ на products
            
            РЕЗУЛЬТИРУЮЩИЕ ТАБЛИЦЫ:
            
            1. products (product_id, product_name, product_price)
               - PK: product_id
               - Все атрибуты зависят от product_id
            
            2. order_items (order_id, product_id, quantity)
               - PK: (order_id, product_id)
               - FK: product_id -> products.product_id
               - В таблице только атрибуты, зависящие от ВСЕГО ключа
            
            ПРЕИМУЩЕСТВА:
            - Нет дублирования названий и цен
            - Легко обновлять цены
            - Можно добавлять товары без заказов
            - Можно удалять заказы без потери информации о товарах
            - Соответствие 2НФ
            */
            
            -- ============================================
            -- ЧАСТЬ 3: CREATE TABLE (2НФ)
            -- ============================================
            
            -- 1. Таблица товаров (вынесенные атрибуты)
            CREATE TABLE IF NOT EXISTS products (
                id             INT PRIMARY KEY AUTO_INCREMENT,
                name           VARCHAR(200) NOT NULL,
                description    TEXT,
                price          DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
                category       VARCHAR(50),
                stock          INT DEFAULT 0 CHECK (stock >= 0),
                created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                
                INDEX idx_name (name),
                INDEX idx_category (category)
            );
            
            -- 2. Таблица заказов (если ещё не создана)
            CREATE TABLE IF NOT EXISTS orders (
                id             INT PRIMARY KEY AUTO_INCREMENT,
                customer_id    INT,
                order_date     DATE NOT NULL,
                status         VARCHAR(20) DEFAULT 'NEW' 
                               CHECK (status IN ('NEW', 'PROCESSING', 'SHIPPED', 'DELIVERED', 'CANCELLED')),
                total          DECIMAL(12, 2) DEFAULT 0 CHECK (total >= 0),
                created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
            );
            
            -- 3. Таблица позиций заказа (2НФ)
            CREATE TABLE IF NOT EXISTS order_items (
                order_id       INT NOT NULL,
                product_id     INT NOT NULL,
                quantity       INT NOT NULL CHECK (quantity > 0),
                created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                
                -- Составной первичный ключ
                PRIMARY KEY (order_id, product_id),
                
                -- Внешние ключи
                FOREIGN KEY (order_id) REFERENCES orders(id) 
                    ON DELETE CASCADE 
                    ON UPDATE CASCADE,
                FOREIGN KEY (product_id) REFERENCES products(id) 
                    ON DELETE RESTRICT 
                    ON UPDATE CASCADE,
                
                -- Индексы
                INDEX idx_order_product (order_id, product_id)
            );
            
            -- ============================================
            -- ДОПОЛНИТЕЛЬНО: ИНДЕКСЫ
            -- ============================================
            CREATE INDEX idx_order_items_product ON order_items(product_id);
            CREATE INDEX idx_orders_date ON orders(order_date);
            CREATE INDEX idx_orders_status ON orders(status);
            
            -- ============================================
            -- ПРИМЕР ЗАПОЛНЕНИЯ ДАННЫМИ
            -- ============================================
            
            -- Вставка товаров
            INSERT INTO products (id, name, price, category, stock) VALUES
                (101, 'Ноутбук ASUS', 50000.00, 'Электроника', 15),
                (102, 'Мышь Logitech', 1500.00, 'Электроника', 50),
                (103, 'Клавиатура', 2000.00, 'Электроника', 30);
            
            -- Вставка заказов
            INSERT INTO orders (id, customer_id, order_date, status) VALUES
                (1, 1, '2024-01-15', 'DELIVERED'),
                (2, 2, '2024-01-20', 'PROCESSING');
            
            -- Вставка позиций заказов (только связь и количество)
            INSERT INTO order_items (order_id, product_id, quantity) VALUES
                (1, 101, 2),   -- 2 ноутбука
                (1, 102, 3),   -- 3 мыши
                (2, 101, 1),   -- 1 ноутбук
                (2, 103, 2);   -- 2 клавиатуры
            
            -- ============================================
            -- ПРОВЕРКА 2НФ
            -- ============================================
            
            -- 1. Проверка структуры
            SELECT '=== Товары ===' AS info;
            SELECT * FROM products ORDER BY id;
            
            SELECT '=== Заказы ===' AS info;
            SELECT * FROM orders ORDER BY id;
            
            SELECT '=== Позиции заказов ===' AS info;
            SELECT * FROM order_items ORDER BY order_id, product_id;
            
            -- 2. Запрос с JOIN (получение полной информации)
            SELECT '=== Полная информация о заказах ===' AS info;
            SELECT 
                oi.order_id,
                oi.product_id,
                p.name AS product_name,
                p.price AS product_price,
                oi.quantity,
                (oi.quantity * p.price) AS total_price
            FROM order_items oi
            JOIN products p ON oi.product_id = p.id
            JOIN orders o ON oi.order_id = o.id
            ORDER BY oi.order_id, oi.product_id;
            
            -- 3. Проверка отсутствия частичных зависимостей
            SELECT '=== Проверка: все неключевые атрибуты зависят от ВСЕГО ключа ===' AS info;
            SELECT 'В order_items есть только: order_id, product_id, quantity' AS message;
            SELECT 'Нет product_name и product_price - они вынесены в products' AS message;
            
            -- ============================================
            -- СРАВНЕНИЕ ДО И ПОСЛЕ 2НФ
            -- ============================================
            
            /*
            ДО 2НФ (order_items):
            ┌──────────┬────────────┬──────────────┬───────────────┬──────────┐
            │ order_id │ product_id │ product_name │ product_price │ quantity │
            ├──────────┼────────────┼──────────────┼───────────────┼──────────┤
            │ 1        │ 101        │ Ноутбук      │ 50000.00      │ 2        │
            │ 1        │ 102        │ Мышь         │ 1500.00       │ 3        │
            │ 2        │ 101        │ Ноутбук      │ 50000.00      │ 1        │
            └──────────┴────────────┴──────────────┴───────────────┴──────────┘
            ПРОБЛЕМЫ: повторяются названия и цены (избыточность)
            
            ПОСЛЕ 2НФ:
            
            products:
            ┌────────────┬──────────────┬───────────────┐
            │ product_id │ product_name │ product_price │
            ├────────────┼──────────────┼───────────────┤
            │ 101        │ Ноутбук      │ 50000.00      │
            │ 102        │ Мышь         │ 1500.00       │
            │ 103        │ Клавиатура   │ 2000.00       │
            └────────────┴──────────────┴───────────────┘
            
            order_items:
            ┌──────────┬────────────┬──────────┐
            │ order_id │ product_id │ quantity │
            ├──────────┼────────────┼──────────┤
            │ 1        │ 101        │ 2        │
            │ 1        │ 102        │ 3        │
            │ 2        │ 101        │ 1        │
            │ 2        │ 103        │ 2        │
            └──────────┴────────────┴──────────┘
            
            ПРЕИМУЩЕСТВА:
            ✅ Нет дублирования названий и цен
            ✅ Легко обновлять цены (одно место)
            ✅ Можно добавлять товары без заказов
            ✅ Можно удалять заказы без потери информации о товарах
            ✅ Соответствие 2НФ
            */
            """;
        System.out.println(sql);
    }
}
