package m46_sql_queries.practice;

/**
 * Задача 03 — Модуль 46: HAVING
 *
 * ЗАДАНИЕ (SQL) для таблицы sales:
 *   1) категории, у которых суммарная выручка больше 10000
 *      (GROUP BY + HAVING SUM);
 *   2) категории, в которых более 3 продаж (HAVING COUNT);
 *   3) категории со средним чеком выше 1000, при этом учитывайте
 *      только продажи с amount > 0 (WHERE до группировки + HAVING после).
 *
 * ЦЕЛЬ:
 *   Понять разницу: WHERE фильтрует строки ДО группировки,
 *   HAVING — группы ПОСЛЕ агрегации.
 *
 * ПОДСКАЗКА:
 *   SELECT category, SUM(amount) FROM sales
 *   GROUP BY category HAVING SUM(amount) > 10000;
 */
public class Task03 {
    public static void main(String[] args) {
        String sql = """
            -- ============================================
            -- ПОДГОТОВКА ТАБЛИЦЫ sales
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
                ('Чехол для телефона', 'Электроника', 500.00, '2024-01-28');
            
            -- ============================================
            -- 1) КАТЕГОРИИ С ВЫРУЧКОЙ > 10000
            -- ============================================
            SELECT 
                category,
                SUM(amount) AS total_revenue,
                COUNT(*) AS sales_count
            FROM sales
            GROUP BY category
            HAVING SUM(amount) > 10000
            ORDER BY total_revenue DESC;
            
            -- ============================================
            -- 2) КАТЕГОРИИ С > 3 ПРОДАЖАМИ
            -- ============================================
            SELECT 
                category,
                COUNT(*) AS sales_count,
                SUM(amount) AS total_revenue,
                ROUND(AVG(amount), 2) AS avg_amount
            FROM sales
            GROUP BY category
            HAVING COUNT(*) > 3
            ORDER BY sales_count DESC;
            
            -- ============================================
            -- 3) КАТЕГОРИИ СО СРЕДНИМ ЧЕКОМ > 1000
            --    (WHERE amount > 0 + HAVING AVG > 1000)
            -- ============================================
            -- WHERE amount > 0 - фильтрует строки до группировки
            -- HAVING AVG(amount) > 1000 - фильтрует группы после агрегации
            SELECT 
                category,
                COUNT(*) AS sales_count,
                ROUND(AVG(amount), 2) AS avg_amount,
                SUM(amount) AS total_revenue,
                MIN(amount) AS min_amount,
                MAX(amount) AS max_amount
            FROM sales
            WHERE amount > 0  -- исключаем нулевые продажи
            GROUP BY category
            HAVING AVG(amount) > 1000
            ORDER BY avg_amount DESC;
            """;
        System.out.println(sql);
    }
}
