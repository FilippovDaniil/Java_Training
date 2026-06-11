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
            -- Напишите здесь INSERT INTO books ...
            """;
        System.out.println(sql);
    }
}
