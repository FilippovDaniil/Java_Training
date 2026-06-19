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
            -- ============================================
            -- ПОДГОТОВКА ТАБЛИЦЫ accounts
            -- ============================================
            CREATE TABLE IF NOT EXISTS accounts (
                id      VARCHAR(10) PRIMARY KEY,
                balance DECIMAL(12, 2) CHECK (balance >= 0)
            );
            
            -- Очищаем таблицу перед вставкой
            DELETE FROM accounts;
            
            -- Начальные данные
            INSERT INTO accounts (id, balance) VALUES
                ('A', 1000.00),
                ('B', 500.00);
            
            -- Проверка начальных балансов
            SELECT '=== Начальные балансы ===' AS info;
            SELECT * FROM accounts ORDER BY id;
            
            -- ============================================
            -- ТРАНЗАКЦИЯ ПЕРЕВОДА 200 СО СЧЕТА A НА B
            -- ============================================
            
            -- Начало транзакции
            BEGIN;
            
            -- Уменьшаем баланс счета A на 200
            UPDATE accounts 
            SET balance = balance - 200 
            WHERE id = 'A';
            
            -- Увеличиваем баланс счета B на 200
            UPDATE accounts 
            SET balance = balance + 200 
            WHERE id = 'B';
            
            -- Фиксация транзакции
            COMMIT;
            
            -- ============================================
            -- ПРОВЕРКА ПОСЛЕ ТРАНЗАКЦИИ
            -- ============================================
            SELECT '=== Балансы после перевода ===' AS info;
            SELECT * FROM accounts ORDER BY id;
            
            -- ============================================
            -- ДОПОЛНИТЕЛЬНАЯ ДЕМОНСТРАЦИЯ
            -- ============================================
            
            -- Проверка, что баланс A уменьшился на 200
            SELECT 
                'A' AS account,
                balance AS current_balance,
                'Должно быть 800.00' AS expected
            FROM accounts WHERE id = 'A';
            
            -- Проверка, что баланс B увеличился на 200
            SELECT 
                'B' AS account,
                balance AS current_balance,
                'Должно быть 700.00' AS expected
            FROM accounts WHERE id = 'B';
            """;
        System.out.println(sql);
    }
}
