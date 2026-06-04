/**
 * Задача 03 — Модуль 84: сидинг данных и repeatable-миграция
 *
 * ФОРМАТ: SQL-носитель (.java с text-блоком + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ (две миграции):
 *
 *   A) Versioned-сидинг V3__seed_categories.sql:
 *        INSERT INTO categories (name) VALUES ('Электроника'), ('Книги'), ('Одежда');
 *      Применится один раз, как и любой V-скрипт.
 *
 *   B) Repeatable-миграция R__product_overview.sql:
 *        CREATE OR REPLACE VIEW product_overview AS
 *            SELECT c.name AS category, COUNT(p.id) AS product_count
 *            FROM categories c LEFT JOIN products p ON p.category_id = c.id
 *            GROUP BY c.name;
 *      Файл с префиксом R (без версии) применяется ЗАНОВО каждый раз, когда меняется
 *      его содержимое (checksum) — удобно для вьюх, функций, процедур.
 *
 *   Впишите оба скрипта в SQL ниже (через комментарии-разделители).
 *
 * ОТЛИЧИЕ V от R:
 *   V{n}__  — однократно, строго по возрастанию версии (история фиксирует версию).
 *   R__     — без версии; повторно при изменении контента; применяется ПОСЛЕ всех V.
 *
 * ЦЕЛЬ: понять, когда нужна repeatable-миграция, а когда versioned.
 *
 * ПОДСКАЗКА: данные-справочники иногда кладут в V (фиксированный сид), а
 *            переопределяемые объекты (VIEW/FUNCTION) — в R (CREATE OR REPLACE).
 */
public class Task03 {
    public static void main(String[] args) {
        String sql = """
                -- ===== V3__seed_categories.sql (versioned) =====
                -- TODO: INSERT INTO categories (name) VALUES ('Электроника'), ('Книги'), ('Одежда');

                -- ===== R__product_overview.sql (repeatable) =====
                -- TODO: CREATE OR REPLACE VIEW product_overview AS
                --       SELECT c.name AS category, COUNT(p.id) AS product_count
                --       FROM categories c LEFT JOIN products p ON p.category_id = c.id
                --       GROUP BY c.name;
                """;
        System.out.println(sql);
    }
}
