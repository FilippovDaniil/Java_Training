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
            -- ============================================
            -- СОЗДАНИЕ СВЯЗАННЫХ ТАБЛИЦ authors И books
            -- ============================================
            
            -- 1. Создание таблицы authors
            CREATE TABLE IF NOT EXISTS authors (
                id INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(100) NOT NULL
            );
            
            -- 2. Создание таблицы books с внешним ключом
            CREATE TABLE IF NOT EXISTS books (
                id        INT PRIMARY KEY AUTO_INCREMENT,
                title     VARCHAR(200) NOT NULL,
                author_id INT,
                FOREIGN KEY (author_id) REFERENCES authors(id)
            );
            
            -- 3. Добавление авторов
            INSERT INTO authors (name) VALUES
                ('Лев Толстой'),
                ('Фёдор Достоевский'),
                ('Михаил Булгаков');
            
            -- 4. Добавление книг с ссылками на авторов
            INSERT INTO books (title, author_id) VALUES
                ('Война и мир', 1),
                ('Анна Каренина', 1),
                ('Преступление и наказание', 2),
                ('Идиот', 2),
                ('Мастер и Маргарита', 3),
                ('Собачье сердце', 3);
            
            -- 5. Проверка данных (JOIN запрос)
            SELECT 
                books.id,
                books.title,
                authors.name AS author
            FROM books
            JOIN authors ON books.author_id = authors.id
            ORDER BY books.id;
            
            -- ============================================
            -- ТЕСТИРОВАНИЕ ВНЕШНЕГО КЛЮЧА
            -- ============================================
            
            -- 6. Попытка вставить книгу с несуществующим author_id
            -- INSERT INTO books (title, author_id) VALUES ('Тестовая книга', 99);
            -- ❌ Ошибка: Cannot add or update a child row: a foreign key constraint fails
            -- (books, CONSTRAINT books_author_id_fkey FOREIGN KEY (author_id) REFERENCES authors(id))
            
            -- 7. Попытка удалить автора, у которого есть книги
            -- DELETE FROM authors WHERE id = 1;
            -- ❌ Ошибка: Cannot delete or update a parent row: a foreign key constraint fails
            -- (books, CONSTRAINT books_author_id_fkey FOREIGN KEY (author_id) REFERENCES authors(id))
            
            -- 8. Правильный способ удаления (сначала книги, потом автор)
            -- DELETE FROM books WHERE author_id = 1;
            -- DELETE FROM authors WHERE id = 1;
            """;
        System.out.println(sql);
    }
}