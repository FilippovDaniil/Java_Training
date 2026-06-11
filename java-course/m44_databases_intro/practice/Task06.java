package m44_databases_intro.practice;

/**
 * Задача 06 — Модуль 44: Внешний ключ (связь таблиц)
 *
 * ЗАДАНИЕ (SQL):
 *   Создайте две связанные таблицы:
 *     - authors (id PK, name);
 *     - books (id PK, title, author_id — внешний ключ на authors.id).
 *   Добавьте пару авторов и несколько книг, ссылающихся на них.
 *   Попробуйте вставить книгу с несуществующим author_id и опишите,
 *   что произойдёт (нарушение внешнего ключа).
 *
 * ПОДСКАЗКА:
 *   CREATE TABLE books (
 *       id INT PRIMARY KEY AUTO_INCREMENT,
 *       title VARCHAR(200),
 *       author_id INT,
 *       FOREIGN KEY (author_id) REFERENCES authors(id)
 *   );
 */
public class Task06 {
    public static void main(String[] args) {
        String sql = """
            -- Напишите здесь CREATE TABLE authors / books с FOREIGN KEY и INSERT
            """;
        System.out.println(sql);
    }
}
