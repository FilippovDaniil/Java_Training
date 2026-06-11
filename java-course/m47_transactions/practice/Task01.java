package m47_transactions.practice;

/**
 * Задача 01 — Модуль 47: Базовая транзакция (BEGIN/COMMIT)
 *
 * ДАНО:
 *   CREATE TABLE accounts (id VARCHAR(10) PRIMARY KEY, balance DECIMAL(12,2));
 *   INSERT INTO accounts VALUES ('A', 1000), ('B', 500);
 *
 * ЗАДАНИЕ (SQL):
 *   Оберните в транзакцию (BEGIN ... COMMIT) перевод 200 со счёта A на B:
 *     - уменьшите баланс A на 200;
 *     - увеличьте баланс B на 200.
 *   После COMMIT проверьте балансы SELECT-ом (должно быть A=800, B=700).
 *
 * ПОДСКАЗКА:
 *   BEGIN;
 *   UPDATE accounts SET balance = balance - 200 WHERE id = 'A';
 *   UPDATE accounts SET balance = balance + 200 WHERE id = 'B';
 *   COMMIT;
 */
public class Task01 {
    public static void main(String[] args) {
        String sql = """
            -- Напишите транзакцию перевода BEGIN ... COMMIT
            """;
        System.out.println(sql);
    }
}
