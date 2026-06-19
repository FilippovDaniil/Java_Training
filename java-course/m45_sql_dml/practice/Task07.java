package m45_sql_dml.practice;

/**
 * Задача 07 — Модуль 45 (МИНИ-ПРОЕКТ): Полный цикл работы с данными
 *
 * ЗАДАНИЕ (SQL):
 *   Смоделируйте управление складом одной таблицей inventory:
 *     id, product, category, quantity, price DECIMAL(10,2), updated DATE.
 *
 *   Выполните полный сценарий:
 *     1) CREATE TABLE inventory;
 *     2) INSERT — наполните 8-10 товарами разных категорий;
 *     3) SELECT — отчёты:
 *        - товары с остатком меньше 10 (заканчиваются);
 *        - товары категории 'Электроника' дороже 1000, по убыванию цены;
 *        - товары, в названии которых есть заданная подстрока (LIKE);
 *        - уникальные категории (DISTINCT);
 *     4) UPDATE — поднимите цену всех товаров одной категории на 15%;
 *        спишите 5 единиц конкретного товара (quantity = quantity - 5);
 *     5) DELETE — удалите товары с нулевым остатком (предварительно
 *        проверив их SELECT-ом).
 *
 * ЦЕЛЬ:
 *   Объединить весь DML: создание, наполнение, выборки с фильтрами,
 *   обновление и безопасное удаление.
 *
 * ПОДСКАЗКА:
 *   Пишите запросы по порядку и мысленно (или в H2) проверяйте
 *   результат каждого шага.
 */
public class Task07 {
    public static void main(String[] args) {
        String sql = """
            -- ============================================
            -- УПРАВЛЕНИЕ СКЛАДОМ - ПОЛНЫЙ ЦИКЛ
            -- ============================================
            
            -- ============================================
            -- 1) CREATE TABLE - создание таблицы
            -- ============================================
            CREATE TABLE IF NOT EXISTS inventory (
                id       INT PRIMARY KEY AUTO_INCREMENT,
                product  VARCHAR(100) NOT NULL,
                category VARCHAR(50),
                quantity INT DEFAULT 0 CHECK (quantity >= 0),
                price    DECIMAL(10, 2) CHECK (price >= 0),
                updated  DATE DEFAULT CURRENT_DATE
            );
            
            -- ============================================
            -- 2) INSERT - наполнение данными
            -- ============================================
            INSERT INTO inventory (product, category, quantity, price, updated) VALUES
                ('Ноутбук ASUS', 'Электроника', 15, 45999.00, '2024-01-15'),
                ('Смартфон Xiaomi', 'Электроника', 25, 29999.00, '2024-01-20'),
                ('Планшет Samsung', 'Электроника', 8, 19999.00, '2024-01-18'),
                ('Наушники Sony', 'Электроника', 30, 4999.00, '2024-01-22'),
                ('Война и мир', 'Книги', 50, 1500.00, '2024-01-10'),
                ('1984', 'Книги', 30, 1350.00, '2024-01-12'),
                ('Мастер и Маргарита', 'Книги', 20, 1400.00, '2024-01-14'),
                ('Джинсы мужские', 'Одежда', 40, 2999.00, '2024-01-16'),
                ('Куртка зимняя', 'Одежда', 12, 4999.00, '2024-01-19'),
                ('Конструктор Lego', 'Игрушки', 35, 2500.00, '2024-01-21'),
                ('Мяч футбольный', 'Спорт', 5, 1500.00, '2024-01-23'),
                ('Ракетка теннисная', 'Спорт', 0, 2000.00, '2024-01-24');
            
            -- ============================================
            -- 3) SELECT - отчеты
            -- ============================================
            
            -- 3.1) Товары с остатком меньше 10
            SELECT '=== 3.1) Товары с остатком < 10 ===' AS info;
            SELECT * FROM inventory WHERE quantity < 10 ORDER BY quantity;
            
            -- 3.2) Электроника дороже 1000, по убыванию цены
            SELECT '=== 3.2) Электроника > 1000 руб ===' AS info;
            SELECT * FROM inventory 
            WHERE category = 'Электроника' AND price > 1000 
            ORDER BY price DESC;
            
            -- 3.3) Товары с подстрокой 'мар' в названии
            SELECT '=== 3.3) LIKE "%мар%" ===' AS info;
            SELECT * FROM inventory WHERE product LIKE '%мар%';
            
            -- 3.4) Уникальные категории (DISTINCT)
            SELECT '=== 3.4) Уникальные категории ===' AS info;
            SELECT DISTINCT category FROM inventory ORDER BY category;
            
            -- ============================================
            -- 4) UPDATE - обновление данных
            -- ============================================
            
            -- 4.1) Повышение цены на 15% для Электроники
            SELECT '=== 4.1) Повышение цены Электроники на 15% ===' AS info;
            
            -- Проверка до обновления
            SELECT 'Цены Электроники ДО обновления:' AS info;
            SELECT product, price FROM inventory WHERE category = 'Электроника';
            
            -- Обновление
            UPDATE inventory 
            SET price = price * 1.15, updated = CURRENT_DATE 
            WHERE category = 'Электроника';
            
            -- Проверка после обновления
            SELECT 'Цены Электроники ПОСЛЕ обновления:' AS info;
            SELECT product, price FROM inventory WHERE category = 'Электроника';
            
            -- 4.2) Списание 5 единиц конкретного товара
            SELECT '=== 4.2) Списание 5 единиц "Ноутбук ASUS" ===' AS info;
            
            -- Проверка до списания
            SELECT product, quantity FROM inventory WHERE product = 'Ноутбук ASUS';
            
            -- Списание
            UPDATE inventory 
            SET quantity = quantity - 5, updated = CURRENT_DATE 
            WHERE product = 'Ноутбук ASUS';
            
            -- Проверка после списания
            SELECT product, quantity FROM inventory WHERE product = 'Ноутбук ASUS';
            
            -- ============================================
            -- 5) DELETE - удаление товаров с нулевым остатком
            -- ============================================
            SELECT '=== 5) Удаление товаров с нулевым остатком ===' AS info;
            
            -- Проверка перед удалением
            SELECT 'Товары с нулевым остатком:' AS info;
            SELECT * FROM inventory WHERE quantity = 0;
            
            -- Удаление
            DELETE FROM inventory WHERE quantity = 0;
            
            -- Проверка после удаления
            SELECT 'Товары с нулевым остатком после удаления:' AS info;
            SELECT * FROM inventory WHERE quantity = 0;
            
            -- ============================================
            -- ФИНАЛЬНЫЙ ОТЧЕТ
            -- ============================================
            SELECT '=== ИТОГОВЫЙ СОСТАВ СКЛАДА ===' AS info;
            SELECT 
                id,
                product,
                category,
                quantity,
                ROUND(price, 2) AS price,
                updated
            FROM inventory 
            ORDER BY category, product;
            
            -- ============================================
            -- АНАЛИТИКА
            -- ============================================
            SELECT '=== АНАЛИТИКА ===' AS info;
            
            -- Общая стоимость товаров на складе
            SELECT 
                'Общая стоимость' AS metric,
                ROUND(SUM(quantity * price), 2) AS value
            FROM inventory
            UNION ALL
            SELECT 
                'Количество товаров',
                COUNT(*) 
            FROM inventory
            UNION ALL
            SELECT 
                'Количество категорий',
                COUNT(DISTINCT category)
            FROM inventory;
            
            -- Статистика по категориям
            SELECT 
                category,
                COUNT(*) AS product_count,
                SUM(quantity) AS total_items,
                ROUND(AVG(price), 2) AS avg_price,
                ROUND(SUM(quantity * price), 2) AS total_value
            FROM inventory
            GROUP BY category
            ORDER BY total_value DESC;
            """;
        System.out.println(sql);
    }
}