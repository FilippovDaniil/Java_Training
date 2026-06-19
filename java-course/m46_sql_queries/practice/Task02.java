package m46_sql_queries.practice;

/**
 * Задача 02 — Модуль 46: GROUP BY
 *
 * ЗАДАНИЕ (SQL) для таблицы sales (см. Task01):
 *   1) выручка по каждой категории (SUM по группам);
 *   2) количество продаж по каждой категории;
 *   3) средний чек по каждой категории, отсортированный по убыванию;
 *   4) выручка по каждому продукту.
 *
 * ПОДСКАЗКА:
 *   SELECT category, SUM(amount) FROM sales GROUP BY category;
 *   SELECT category, AVG(amount) AS avg_check FROM sales
 *   GROUP BY category ORDER BY avg_check DESC;
 */
public class Task02 {
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
                ('Ракетка теннисная', 'Спорт', 2000.00, '2024-01-26');
            
            -- ============================================
            -- 1) ВЫРУЧКА ПО КАЖДОЙ КАТЕГОРИИ
            -- ============================================
            SELECT 
                category,
                SUM(amount) AS total_revenue,
                COUNT(*) AS sales_count
            FROM sales
            GROUP BY category
            ORDER BY total_revenue DESC;
            
            -- ============================================
            -- 2) КОЛИЧЕСТВО ПРОДАЖ ПО КАЖДОЙ КАТЕГОРИИ
            -- ============================================
            SELECT 
                category,
                COUNT(*) AS sales_count
            FROM sales
            GROUP BY category
            ORDER BY sales_count DESC;
            
            -- ============================================
            -- 3) СРЕДНИЙ ЧЕК ПО КАЖДОЙ КАТЕГОРИИ (ПО УБЫВАНИЮ)
            -- ============================================
            SELECT 
                category,
                ROUND(AVG(amount), 2) AS avg_check,
                MIN(amount) AS min_check,
                MAX(amount) AS max_check
            FROM sales
            GROUP BY category
            ORDER BY avg_check DESC;
            
            -- ============================================
            -- 4) ВЫРУЧКА ПО КАЖДОМУ ПРОДУКТУ
            -- ============================================
            SELECT 
                product,
                category,
                SUM(amount) AS total_revenue,
                COUNT(*) AS sales_count
            FROM sales
            GROUP BY product, category
            ORDER BY total_revenue DESC;
            """;
        System.out.println(sql);
    }
}
