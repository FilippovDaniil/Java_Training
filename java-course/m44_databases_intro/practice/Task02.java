package m44_databases_intro.practice;

/**
 * Задача 02 — Модуль 44: Вставка данных (INSERT)
 *
 * ЗАДАНИЕ (SQL):
 *   Для таблицы books из Task01 напишите INSERT-запросы, добавляющие
 *   минимум 5 книг. Используйте как одиночную, так и множественную
 *   вставку (несколько строк одним INSERT).
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
 *   После выполнения SELECT COUNT(*) FROM books вернёт 5.
 *
 * ПОДСКАЗКА:
 *   INSERT INTO books (title, author, year, price)
 *   VALUES ('...', '...', 2020, 599.00),
 *          ('...', '...', 2019, 450.50);
 */
public class Task02 {
    public static void main(String[] args) {
        String sql = """
            -- ============================================
            -- Вставка данных в таблицу books
            -- ============================================
            
            -- 1. Одиночная вставка (одна запись)
            INSERT INTO books (title, author, year, price)
            VALUES ('Война и мир', 'Лев Толстой', 1869, 1500.00);
            
            INSERT INTO books (title, author, year, price)
            VALUES ('Преступление и наказание', 'Фёдор Достоевский', 1866, 1200.00);
            
            INSERT INTO books (title, author, year, price)
            VALUES ('Мастер и Маргарита', 'Михаил Булгаков', 1967, 1300.00);
            
            -- 2. Множественная вставка (несколько записей одним INSERT)
            INSERT INTO books (title, author, year, price) VALUES
                ('Евгений Онегин', 'Александр Пушкин', 1833, 900.00),
                ('Мёртвые души', 'Николай Гоголь', 1842, 1100.00),
                ('Герой нашего времени', 'Михаил Лермонтов', 1840, 950.00),
                ('Отцы и дети', 'Иван Тургенев', 1862, 1050.00),
                ('Тихий Дон', 'Михаил Шолохов', 1940, 1400.00);
            
            -- 3. Проверка количества записей
            SELECT COUNT(*) AS total_books FROM books;
            
            -- 4. Просмотр всех добавленных книг
            SELECT * FROM books ORDER BY id;
            """;
        System.out.println(sql);
    }
}
