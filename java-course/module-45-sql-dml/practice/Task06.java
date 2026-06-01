/**
 * Задача 06 — Модуль 45: DELETE (безопасно)
 *
 * ЗАДАНИЕ (SQL) для таблицы employees:
 *   1) сначала напишите SELECT, показывающий сотрудников с зарплатой
 *      ниже 30000 (проверка перед удалением);
 *   2) удалите этих сотрудников (DELETE с тем же WHERE);
 *   3) удалите сотрудников конкретного отдела (например, 'Временный');
 *   4) в комментарии поясните, чем DELETE без WHERE отличается от
 *      TRUNCATE TABLE и DROP TABLE.
 *
 * ВНИМАНИЕ:
 *   DELETE без WHERE удалит ВСЕ строки. Всегда проверяйте условие
 *   через SELECT заранее.
 *
 * ПОДСКАЗКА:
 *   SELECT * FROM employees WHERE salary < 30000;   -- проверка
 *   DELETE FROM employees WHERE salary < 30000;      -- удаление
 */
public class Task06 {
    public static void main(String[] args) {
        String sql = """
            -- Напишите SELECT-проверку и запросы DELETE
            """;
        System.out.println(sql);
    }
}
