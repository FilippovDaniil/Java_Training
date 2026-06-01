/**
 * Задача 04 — Модуль 45: IN, BETWEEN, NULL
 *
 * ЗАДАНИЕ (SQL) для таблицы employees:
 *   1) сотрудники с зарплатой в диапазоне 50000..90000 (BETWEEN);
 *   2) сотрудники отделов 'IT', 'HR', 'Sales' (IN);
 *   3) сотрудники, у которых НЕ указан отдел (IS NULL);
 *   4) сотрудники, у которых отдел указан (IS NOT NULL);
 *   5) сотрудники НЕ из 'IT' (NOT IN или <>).
 *
 * ВНИМАНИЕ:
 *   NULL проверяется ТОЛЬКО через IS NULL / IS NOT NULL,
 *   а не через = NULL.
 *
 * ПОДСКАЗКА:
 *   WHERE salary BETWEEN 50000 AND 90000;
 *   WHERE department IN ('IT','HR','Sales');
 *   WHERE department IS NULL;
 */
public class Task04 {
    public static void main(String[] args) {
        String sql = """
            -- Напишите запросы с BETWEEN, IN, IS NULL, IS NOT NULL
            """;
        System.out.println(sql);
    }
}
