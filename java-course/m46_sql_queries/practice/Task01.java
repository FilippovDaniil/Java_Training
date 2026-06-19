package m46_sql_queries.practice;

/**
 * Задача 01 — Модуль 46: Агрегатные функции
 *
 * ДАНО (таблица для всех задач модуля):
 *   sales(id, product, category, amount DECIMAL, sale_date DATE)
 *   Наполните её 10-15 строками перед началом.
 *
 * ЗАДАНИЕ (SQL):
 *   1) общее количество продаж (COUNT);
 *   2) суммарная выручка (SUM amount);
 *   3) средний, минимальный и максимальный чек (AVG, MIN, MAX);
 *   4) количество разных категорий (COUNT DISTINCT category).
 *
 * ПОДСКАЗКА:
 *   SELECT COUNT(*), SUM(amount), AVG(amount), MIN(amount), MAX(amount) FROM sales;
 *   SELECT COUNT(DISTINCT category) FROM sales;
 */
public class Task01 {
    public static void main(String[] args) {
        String sql = """
            -- ============================================
            -- ПОДГОТОВКА ТАБЛИЦЫ И ДАННЫХ
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
                ('Конструктор Lego', 'Игрушки', 2500.00, '2024-01-24'),
                ('Мяч футбольный', 'Спорт', 1500.00, '2024-01-25'),
                ('Ракетка теннисная', 'Спорт', 2000.00, '2024-01-26'),
                ('Книга рецептов', 'Книги', 800.00, '2024-01-27'),
                ('Чехол для телефона', 'Электроника', 500.00, '2024-01-28'),
                ('Шапка зимняя', 'Одежда', 1500.00, '2024-01-29');
            
            -- ============================================
            -- 1) ОБЩЕЕ КОЛИЧЕСТВО ПРОДАЖ (COUNT)
            -- ============================================
            SELECT 
                'Общее количество продаж' AS metric,
                COUNT(*) AS value
            FROM sales;
            
            -- ============================================
            -- 2) СУММАРНАЯ ВЫРУЧКА (SUM amount)
            -- ============================================
            SELECT 
                'Суммарная выручка' AS metric,
                SUM(amount) AS value
            FROM sales;
            
            -- ============================================
            -- 3) СРЕДНИЙ, МИНИМАЛЬНЫЙ И МАКСИМАЛЬНЫЙ ЧЕК
            -- ============================================
            SELECT 
                ROUND(AVG(amount), 2) AS avg_check,
                MIN(amount) AS min_check,
                MAX(amount) AS max_check
            FROM sales;
            
            -- ============================================
            -- 4) КОЛИЧЕСТВО РАЗНЫХ КАТЕГОРИЙ
            -- ============================================
            SELECT 
                COUNT(DISTINCT category) AS unique_categories
            FROM sales;
            
            -- ============================================
            -- ВСЕ АГРЕГАТЫ В ОДНОМ ЗАПРОСЕ
            -- ============================================
            SELECT 
                COUNT(*) AS total_sales,
                SUM(amount) AS total_revenue,
                ROUND(AVG(amount), 2) AS avg_revenue,
                MIN(amount) AS min_revenue,
                MAX(amount) AS max_revenue,
                COUNT(DISTINCT category) AS unique_categories
            FROM sales;
            """;
        System.out.println(sql);
    }
}
