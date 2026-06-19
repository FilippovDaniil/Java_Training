package m47_transactions.practice;

/**
 * Задача 07 — Модуль 47 (МИНИ-ПРОЕКТ): Банковская система с транзакциями
 *
 * ЗАДАНИЕ (SQL):
 *   Постройте устойчивую к сбоям банковскую логику.
 *   Схема:
 *     accounts(id, owner, balance DECIMAL(12,2))
 *     transactions_log(id, from_acc, to_acc, amount, ts TIMESTAMP, status)
 *
 *   Реализуйте сценарии (каждый — в транзакции):
 *     1) Успешный перевод: списать с A, зачислить B, записать в лог
 *        строку со status='OK' — всё в ОДНОЙ транзакции (COMMIT).
 *     2) Перевод с недостатком средств: проверить баланс; если мало —
 *        записать в лог status='FAILED' и откатить изменения балансов
 *        (но сам лог-факт можно сохранить — продумайте через SAVEPOINT
 *        или отдельную транзакцию).
 *     3) Снятие наличных: уменьшить баланс + лог, атомарно.
 *     4) Покажите, что при «сбое» между операциями ROLLBACK
 *        возвращает систему в согласованное состояние (ни одного
 *        частичного изменения).
 *
 * ЦЕЛЬ:
 *   Применить ACID на практике: атомарность перевода, согласованность
 *   балансов, корректный откат при ошибках.
 *
 * ПОДСКАЗКА:
 *   Группируйте связанные UPDATE/INSERT между BEGIN и COMMIT.
 *   Для частичной фиксации лога при неудаче используйте SAVEPOINT.
 *   В реальном приложении это делается из Java через JDBC (модуль 50).
 */
public class Task07 {
    public static void main(String[] args) {
        String sql = """
            -- ============================================
            -- СОЗДАНИЕ СХЕМЫ БАНКОВСКОЙ СИСТЕМЫ
            -- ============================================
            
            -- 1. Таблица счетов
            CREATE TABLE IF NOT EXISTS accounts (
                id      VARCHAR(10) PRIMARY KEY,
                owner   VARCHAR(100) NOT NULL,
                balance DECIMAL(12, 2) DEFAULT 0 CHECK (balance >= 0)
            );
            
            -- 2. Таблица лога транзакций
            CREATE TABLE IF NOT EXISTS transactions_log (
                id         INT PRIMARY KEY AUTO_INCREMENT,
                from_acc   VARCHAR(10),
                to_acc     VARCHAR(10),
                amount     DECIMAL(12, 2),
                ts         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                status     VARCHAR(20) CHECK (status IN ('OK', 'FAILED', 'PENDING'))
            );
            
            -- 3. Наполнение данными
            INSERT INTO accounts (id, owner, balance) VALUES
                ('A', 'Иван Петров', 1000.00),
                ('B', 'Мария Смирнова', 500.00),
                ('C', 'Петр Сидоров', 2000.00),
                ('D', 'Анна Кузнецова', 300.00);
            
            -- ============================================
            -- СЦЕНАРИЙ 1: УСПЕШНЫЙ ПЕРЕВОД
            -- ============================================
            
            SELECT '=== СЦЕНАРИЙ 1: УСПЕШНЫЙ ПЕРЕВОД (A -> B, 200) ===' AS info;
            
            -- Начало транзакции
            BEGIN;
            
            -- Списание со счета A
            UPDATE accounts SET balance = balance - 200 
            WHERE id = 'A' AND balance >= 200;
            
            -- Зачисление на счет B
            UPDATE accounts SET balance = balance + 200 
            WHERE id = 'B';
            
            -- Запись в лог (успешно)
            INSERT INTO transactions_log (from_acc, to_acc, amount, status) 
            VALUES ('A', 'B', 200, 'OK');
            
            -- Фиксация транзакции
            COMMIT;
            
            -- Проверка после перевода
            SELECT 'Балансы после перевода:' AS info;
            SELECT * FROM accounts ORDER BY id;
            SELECT 'Лог транзакций:' AS info;
            SELECT * FROM transactions_log ORDER BY id;
            
            -- ============================================
            -- СЦЕНАРИЙ 2: ПЕРЕВОД С НЕДОСТАТКОМ СРЕДСТВ
            -- ============================================
            
            SELECT '=== СЦЕНАРИЙ 2: ПЕРЕВОД С НЕДОСТАТКОМ (B -> A, 1000) ===' AS info;
            
            -- Начало транзакции
            BEGIN;
            
            -- Проверка баланса через SAVEPOINT
            SAVEPOINT before_transfer;
            
            -- Проверяем, достаточно ли средств
            SELECT 'Проверка баланса B:' AS info;
            SELECT id, balance FROM accounts WHERE id = 'B';
            
            -- Если недостаточно средств, откатываем и логируем
            -- (проверка через условие UPDATE)
            UPDATE accounts SET balance = balance - 1000 
            WHERE id = 'B' AND balance >= 1000;
            
            -- Проверяем, сколько строк обновлено (в SQL нужно использовать ROW_COUNT)
            -- В этом примере мы проверяем баланс через SELECT
            
            -- Если обновление не сработало (недостаточно средств)
            -- В SQL скрипте мы просто выполняем перевод с проверкой
            -- В реальном приложении используется ROW_COUNT
            
            -- Для демонстрации: откатываем к SAVEPOINT
            ROLLBACK TO SAVEPOINT before_transfer;
            
            -- Записываем в лог о неудаче
            INSERT INTO transactions_log (from_acc, to_acc, amount, status) 
            VALUES ('B', 'A', 1000, 'FAILED');
            
            -- Фиксация лога об ошибке
            COMMIT;
            
            -- Проверка после неудачного перевода
            SELECT 'Балансы после неудачного перевода:' AS info;
            SELECT * FROM accounts ORDER BY id;
            SELECT 'Лог транзакций:' AS info;
            SELECT * FROM transactions_log ORDER BY id;
            
            -- ============================================
            -- СЦЕНАРИЙ 3: СНЯТИЕ НАЛИЧНЫХ
            -- ============================================
            
            SELECT '=== СЦЕНАРИЙ 3: СНЯТИЕ НАЛИЧНЫХ (C, 500) ===' AS info;
            
            -- Начало транзакции
            BEGIN;
            
            -- Списание со счета C (с проверкой достаточности)
            UPDATE accounts SET balance = balance - 500 
            WHERE id = 'C' AND balance >= 500;
            
            -- Запись в лог
            INSERT INTO transactions_log (from_acc, to_acc, amount, status) 
            VALUES ('C', NULL, 500, 'OK');
            
            -- Фиксация транзакции
            COMMIT;
            
            -- Проверка после снятия
            SELECT 'Балансы после снятия:' AS info;
            SELECT * FROM accounts ORDER BY id;
            SELECT 'Лог транзакций:' AS info;
            SELECT * FROM transactions_log ORDER BY id;
            
            -- ============================================
            -- СЦЕНАРИЙ 4: СБОЙ И ROLLBACK
            -- ============================================
            
            SELECT '=== СЦЕНАРИЙ 4: СБОЙ (D -> A, 1000) ===' AS info;
            
            -- Начало транзакции
            BEGIN;
            
            -- Показываем состояние до перевода
            SELECT 'Состояние ДО перевода:' AS info;
            SELECT * FROM accounts WHERE id IN ('D', 'A');
            
            -- Списание со счета D (если достаточно средств)
            UPDATE accounts SET balance = balance - 1000 
            WHERE id = 'D' AND balance >= 1000;
            
            -- Зачисление на счет A (если первая операция успешна)
            UPDATE accounts SET balance = balance + 1000 
            WHERE id = 'A';
            
            -- Имитация сбоя: ошибка в логировании
            -- Например, попытка вставить неверные данные
            -- INSERT INTO transactions_log (from_acc, status) VALUES ('D', 'BROKEN');
            
            -- В случае сбоя выполняем ROLLBACK
            ROLLBACK;
            
            -- Проверка после ROLLBACK
            SELECT 'Состояние ПОСЛЕ ROLLBACK (изменения отменены):' AS info;
            SELECT * FROM accounts WHERE id IN ('D', 'A');
            
            -- ============================================
            -- СЦЕНАРИЙ 5: ВОССТАНОВЛЕНИЕ ПОСЛЕ СБОЯ
            -- ============================================
            
            SELECT '=== СЦЕНАРИЙ 5: ВОССТАНОВЛЕНИЕ ПОСЛЕ СБОЯ ===' AS info;
            
            -- Начало транзакции
            BEGIN;
            
            -- Повторная попытка перевода (успешная)
            UPDATE accounts SET balance = balance - 200 
            WHERE id = 'D' AND balance >= 200;
            
            UPDATE accounts SET balance = balance + 200 
            WHERE id = 'A';
            
            -- Запись в лог
            INSERT INTO transactions_log (from_acc, to_acc, amount, status) 
            VALUES ('D', 'A', 200, 'OK');
            
            COMMIT;
            
            -- Проверка финального состояния
            SELECT '=== ФИНАЛЬНОЕ СОСТОЯНИЕ ===' AS info;
            SELECT * FROM accounts ORDER BY id;
            SELECT 'Все транзакции:' AS info;
            SELECT * FROM transactions_log ORDER BY id;
            
            -- ============================================
            -- СТАТИСТИКА
            -- ============================================
            
            SELECT '=== СТАТИСТИКА ===' AS info;
            SELECT 
                status,
                COUNT(*) AS count,
                SUM(amount) AS total_amount
            FROM transactions_log
            GROUP BY status
            ORDER BY status;
            
            SELECT 
                'Общий баланс всех счетов' AS metric,
                SUM(balance) AS value
            FROM accounts
            UNION ALL
            SELECT 
                'Количество счетов',
                COUNT(*)
            FROM accounts
            UNION ALL
            SELECT 
                'Средний баланс',
                ROUND(AVG(balance), 2)
            FROM accounts;
            """;
        System.out.println(sql);
    }
}
