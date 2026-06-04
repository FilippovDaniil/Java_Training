/**
 * Задача 02 — Модуль 84: эволюция схемы (V2__add_categories.sql)
 *
 * ФОРМАТ: SQL-носитель (.java с text-блоком + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Схема развивается — добавляем категории. Создайте миграцию
 *   src/main/resources/db/migration/V2__add_categories.sql, которая:
 *     1) создаёт таблицу categories (id BIGINT IDENTITY PK, name VARCHAR(255) NOT NULL UNIQUE);
 *     2) добавляет в products колонку category_id BIGINT (nullable);
 *     3) добавляет внешний ключ:
 *          ALTER TABLE products ADD CONSTRAINT fk_products_category
 *              FOREIGN KEY (category_id) REFERENCES categories(id);
 *   Впишите этот DDL в SQL ниже.
 *
 * ВАЖНО: НЕ редактируйте уже применённый V1 — для изменений ВСЕГДА новая версия (V2, V3...).
 *        Правка применённого файла меняет checksum → Flyway validate упадёт с
 *        "Migration checksum mismatch".
 *
 * ЦЕЛЬ: освоить аддитивную эволюцию схемы версионируемыми миграциями.
 *
 * ПОДСКАЗКА: ALTER TABLE ... ADD COLUMN и ADD CONSTRAINT — отдельными операторами.
 */
public class Task02 {
    public static void main(String[] args) {
        String sql = """
                -- Файл: src/main/resources/db/migration/V2__add_categories.sql
                -- TODO 1: CREATE TABLE categories (...);
                -- TODO 2: ALTER TABLE products ADD COLUMN category_id BIGINT;
                -- TODO 3: ALTER TABLE products ADD CONSTRAINT fk_products_category
                --             FOREIGN KEY (category_id) REFERENCES categories(id);
                """;
        System.out.println(sql);
    }
}
