package m44_databases_intro.practice;

/**
 * Задача 04 — Модуль 44: Изменение структуры (ALTER)
 *
 * ЗАДАНИЕ (SQL):
 *   1. Добавьте в таблицу books столбец genre VARCHAR(50).
 *   2. Добавьте столбец in_stock BOOLEAN со значением по умолчанию TRUE.
 *   3. Удалите столбец genre.
 *
 * ПОДСКАЗКА:
 *   ALTER TABLE books ADD COLUMN genre VARCHAR(50);
 *   ALTER TABLE books ADD COLUMN in_stock BOOLEAN DEFAULT TRUE;
 *   ALTER TABLE books DROP COLUMN genre;
 */
public class Task04 {
    public static void main(String[] args) {
        String sql = """
            -- ============================================
            -- ИЗМЕНЕНИЕ СТРУКТУРЫ ТАБЛИЦЫ books
            -- ============================================
            
            -- 1. Добавление столбца genre
            ALTER TABLE books ADD COLUMN genre VARCHAR(50);
            
            -- 2. Добавление столбца in_stock с значением по умолчанию TRUE
            ALTER TABLE books ADD COLUMN in_stock BOOLEAN DEFAULT TRUE;
            
            -- 3. Удаление столбца genre
            ALTER TABLE books DROP COLUMN genre;
            
            -- 4. Проверка структуры таблицы после изменений
            DESCRIBE books;
            """;
        System.out.println(sql);
    }
}