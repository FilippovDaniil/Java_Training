/**
 * Задача 05 — Модуль 45: UPDATE
 *
 * ЗАДАНИЕ (SQL) для таблицы employees:
 *   1) повысьте зарплату конкретному сотруднику (по id) до 95000;
 *   2) поднимите зарплату всем сотрудникам отдела 'IT' на 10%
 *      (salary = salary * 1.1);
 *   3) переведите сотрудников без отдела (department IS NULL) в отдел
 *      'Общий', одновременно установив зарплату 40000.
 *
 * ВНИМАНИЕ:
 *   Каждый UPDATE — с WHERE! Перед изменением проверьте тем же WHERE
 *   через SELECT, какие строки будут затронуты.
 *
 * ПОДСКАЗКА:
 *   UPDATE employees SET salary = salary * 1.1 WHERE department = 'IT';
 */
public class Task05 {
    public static void main(String[] args) {
        String sql = """
            -- Напишите три запроса UPDATE (каждый с WHERE)
            """;
        System.out.println(sql);
    }
}
