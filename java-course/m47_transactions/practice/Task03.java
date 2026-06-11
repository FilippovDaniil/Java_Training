package m47_transactions.practice;

/**
 * Задача 03 — Модуль 47: SAVEPOINT
 *
 * ЗАДАНИЕ (SQL):
 *   Используя таблицу log(id, message), продемонстрируйте частичный откат:
 *     1) BEGIN;
 *     2) вставьте запись "шаг 1";
 *     3) создайте SAVEPOINT sp1;
 *     4) вставьте запись "шаг 2";
 *     5) ROLLBACK TO sp1 (отменит только "шаг 2");
 *     6) вставьте запись "шаг 3";
 *     7) COMMIT.
 *   После COMMIT в таблице должны остаться "шаг 1" и "шаг 3", но НЕ "шаг 2".
 *
 * ПОДСКАЗКА:
 *   SAVEPOINT sp1;  ...  ROLLBACK TO sp1;
 *   SAVEPOINT позволяет откатить часть транзакции, не отменяя её целиком.
 */
public class Task03 {
    public static void main(String[] args) {
        String sql = """
            -- Напишите транзакцию с SAVEPOINT и частичным откатом
            """;
        System.out.println(sql);
    }
}
