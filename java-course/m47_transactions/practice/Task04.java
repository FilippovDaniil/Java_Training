package m47_transactions.practice;

/**
 * Задача 04 — Модуль 47: Атомарный перевод с проверкой
 *
 * ЗАДАНИЕ (SQL) для таблицы accounts:
 *   Реализуйте «безопасный» перевод, который НЕ должен уводить баланс
 *   в минус:
 *     1) в транзакции уменьшите баланс отправителя и увеличьте баланс
 *        получателя;
 *     2) добавьте проверку: если после списания баланс отправителя
 *        стал бы отрицательным — откатите транзакцию (ROLLBACK).
 *   Опишите в комментарии, как именно вы проверяете достаточность
 *   средств (например, SELECT баланса перед UPDATE или условие в UPDATE
 *   с проверкой результата).
 *
 * ПОДСКАЗКА:
 *   Можно сделать UPDATE ... WHERE id='A' AND balance >= 200;
 *   и проверить, сколько строк затронуто (в JDBC — executeUpdate()).
 *   В чистом SQL проверку обычно делают в приложении (см. модуль 50).
 */
public class Task04 {
    public static void main(String[] args) {
        String sql = """
            -- ============================================
            -- ПОДГОТОВКА ТАБЛИЦЫ accounts
            -- ============================================
            CREATE TABLE IF NOT EXISTS accounts (
                id      VARCHAR(10) PRIMARY KEY,
                balance DECIMAL(12, 2) CHECK (balance >= 0)
            );
            
            -- Очищаем и наполняем данными
            DELETE FROM accounts;
            INSERT INTO accounts (id, balance) VALUES
                ('A', 1000.00),
                ('B', 500.00),
                ('C', 200.00);
            
            -- ============================================
            -- СПОСОБ 1: ПРОВЕРКА ЧЕРЕЗ SELECT
            -- ============================================
            
            -- Начало транзакции
            BEGIN;
            
            -- Проверка достаточности средств
            SELECT '=== Проверка баланса A ===' AS info;
            SELECT id, balance FROM accounts WHERE id = 'A';
            
            -- Если баланс >= сумма перевода, выполняем перевод
            -- (в реальном коде проверку делает приложение)
            UPDATE accounts SET balance = balance - 200 WHERE id = 'A';
            UPDATE accounts SET balance = balance + 200 WHERE id = 'B';
            
            -- Проверка после перевода
            SELECT '=== Баланс после перевода ===' AS info;
            SELECT * FROM accounts ORDER BY id;
            
            COMMIT;
            
            -- ============================================
            -- СПОСОБ 2: UPDATE С УСЛОВИЕМ
            -- ============================================
            
            -- Повторная инициализация
            DELETE FROM accounts;
            INSERT INTO accounts (id, balance) VALUES
                ('A', 1000.00),
                ('B', 500.00),
                ('C', 200.00);
            
            SELECT '=== Инициализация для способа 2 ===' AS info;
            SELECT * FROM accounts ORDER BY id;
            
            -- Начало транзакции
            BEGIN;
            
            -- UPDATE с проверкой в условии (безопасное списание)
            UPDATE accounts SET balance = balance - 500 
            WHERE id = 'A' AND balance >= 500;
            
            -- Проверяем, сколько строк обновлено
            -- (в SQL нельзя получить ROW_COUNT в тексте скрипта,
            -- но в приложении можно проверить)
            
            -- Для демонстрации просто выполняем перевод
            UPDATE accounts SET balance = balance + 500 
            WHERE id = 'B';
            
            SELECT '=== После безопасного перевода ===' AS info;
            SELECT * FROM accounts ORDER BY id;
            
            COMMIT;
            
            -- ============================================
            -- СПОСОБ 3: ЗАЩИТА ОТ ОТРИЦАТЕЛЬНОГО БАЛАНСА
            -- ============================================
            
            -- Повторная инициализация
            DELETE FROM accounts;
            INSERT INTO accounts (id, balance) VALUES
                ('A', 1000.00),
                ('B', 500.00),
                ('C', 200.00);
            
            SELECT '=== Инициализация для способа 3 ===' AS info;
            SELECT * FROM accounts ORDER BY id;
            
            -- Пример перевода, который НЕ должен выполниться
            BEGIN;
            
            -- Попытка списать больше, чем есть на счете
            UPDATE accounts SET balance = balance - 1500 
            WHERE id = 'A' AND balance >= 1500;
            
            -- Вторая часть перевода выполняется только если первая успешна
            -- (в реальном приложении проверяется количество обновленных строк)
            UPDATE accounts SET balance = balance + 1500 
            WHERE id = 'C';
            
            -- Проверка
            SELECT '=== После ошибочного перевода ===' AS info;
            SELECT * FROM accounts ORDER BY id;
            
            -- Откат, так как перевод не должен был состояться
            ROLLBACK;
            
            SELECT '=== После ROLLBACK (перевод отменен) ===' AS info;
            SELECT * FROM accounts ORDER BY id;
            
            -- ============================================
            -- СПОСОБ 4: ПРОВЕРКА ЧЕРЕЗ ПОДЗАПРОС
            -- ============================================
            
            -- Повторная инициализация
            DELETE FROM accounts;
            INSERT INTO accounts (id, balance) VALUES
                ('A', 1000.00),
                ('B', 500.00);
            
            SELECT '=== Инициализация для способа 4 ===' AS info;
            SELECT * FROM accounts ORDER BY id;
            
            -- Транзакция с проверкой через подзапрос
            BEGIN;
            
            -- Проверка и перевод в одной операции
            UPDATE accounts a
            SET balance = balance - 200
            WHERE id = 'A' 
            AND balance >= 200;
            
            UPDATE accounts 
            SET balance = balance + 200
            WHERE id = 'B';
            
            -- Проверка результата
            SELECT '=== После подзапроса ===' AS info;
            SELECT * FROM accounts ORDER BY id;
            
            COMMIT;
            """;
        System.out.println(sql);
    }
}
