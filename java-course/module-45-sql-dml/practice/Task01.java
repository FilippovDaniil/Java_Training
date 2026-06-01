/**
 * Задача 01 — Модуль 45: Варианты INSERT
 *
 * ДАНО (создайте перед началом):
 *   CREATE TABLE employees (
 *       id INT PRIMARY KEY AUTO_INCREMENT,
 *       name VARCHAR(100) NOT NULL,
 *       department VARCHAR(50),
 *       salary DECIMAL(10,2),
 *       hired DATE
 *   );
 *
 * ЗАДАНИЕ (SQL):
 *   1) одиночный INSERT с указанием всех столбцов;
 *   2) множественный INSERT (3+ сотрудника одним запросом);
 *   3) INSERT только в часть столбцов (name + department),
 *      остальные останутся NULL/по умолчанию.
 *
 * ПОДСКАЗКА:
 *   INSERT INTO employees (name, department, salary, hired)
 *   VALUES ('Иван', 'IT', 80000, '2020-03-15');
 */
public class Task01 {
    public static void main(String[] args) {
        String sql = """
            -- Напишите три варианта INSERT
            """;
        System.out.println(sql);
    }
}
