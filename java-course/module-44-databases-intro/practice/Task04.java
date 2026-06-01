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
            -- Напишите здесь ALTER TABLE ...
            """;
        System.out.println(sql);
    }
}
