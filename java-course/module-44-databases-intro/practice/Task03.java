/**
 * Задача 03 — Модуль 44: Выборка данных (SELECT)
 *
 * ЗАДАНИЕ (SQL):
 *   Напишите запросы к таблице books:
 *     1) все книги (все столбцы);
 *     2) только названия и цены;
 *     3) книги, изданные после 2015 года;
 *     4) книги дороже 500, отсортированные по цене по убыванию.
 *
 * ПОДСКАЗКА:
 *   SELECT * FROM books;
 *   SELECT title, price FROM books;
 *   SELECT * FROM books WHERE year > 2015;
 *   SELECT * FROM books WHERE price > 500 ORDER BY price DESC;
 */
public class Task03 {
    public static void main(String[] args) {
        String sql = """
            -- Напишите здесь 4 запроса SELECT
            """;
        System.out.println(sql);
    }
}
