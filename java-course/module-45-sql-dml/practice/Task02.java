/**
 * Задача 02 — Модуль 45: SELECT с условиями
 *
 * ЗАДАНИЕ (SQL) для таблицы employees (см. Task01):
 *   1) сотрудники с зарплатой больше 70000;
 *   2) сотрудники отдела 'IT' с зарплатой >= 60000 (AND);
 *   3) сотрудники отделов 'IT' или 'HR' (OR);
 *   4) все сотрудники, отсортированные по зарплате по убыванию;
 *   5) только имена и зарплаты, с псевдонимами (AS).
 *
 * ПОДСКАЗКА:
 *   SELECT * FROM employees WHERE salary > 70000;
 *   SELECT name AS Сотрудник, salary AS Оклад FROM employees;
 */
public class Task02 {
    public static void main(String[] args) {
        String sql = """
            -- Напишите 5 запросов SELECT с условиями и сортировкой
            """;
        System.out.println(sql);
    }
}
