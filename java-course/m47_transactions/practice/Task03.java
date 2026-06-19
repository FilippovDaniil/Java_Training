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
            -- ============================================
            -- ПОДГОТОВКА ТАБЛИЦЫ log
            -- ============================================
            CREATE TABLE IF NOT EXISTS log (
                id      INT PRIMARY KEY AUTO_INCREMENT,
                message VARCHAR(100),
                created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            
            -- Очищаем таблицу
            DELETE FROM log;
            
            -- ============================================
            -- ТРАНЗАКЦИЯ С SAVEPOINT
            -- ============================================
            
            -- Начало транзакции
            BEGIN;
            
            -- Шаг 1: вставка первой записи
            INSERT INTO log (message) VALUES ('шаг 1');
            
            -- Создание точки сохранения
            SAVEPOINT sp1;
            
            -- Шаг 2: вставка второй записи
            INSERT INTO log (message) VALUES ('шаг 2');
            
            -- Откат до SAVEPOINT sp1 (отменяет только 'шаг 2')
            ROLLBACK TO SAVEPOINT sp1;
            
            -- Шаг 3: вставка третьей записи
            INSERT INTO log (message) VALUES ('шаг 3');
            
            -- Фиксация транзакции
            COMMIT;
            
            -- ============================================
            -- ПРОВЕРКА РЕЗУЛЬТАТА
            -- ============================================
            SELECT '=== СОДЕРЖИМОЕ ТАБЛИЦЫ log ===' AS info;
            SELECT * FROM log ORDER BY id;
            
            -- Ожидаемый результат: "шаг 1" и "шаг 3", но НЕ "шаг 2"
            SELECT 
                message,
                CASE 
                    WHEN message = 'шаг 2' THEN '❌ НЕ ДОЛЖНО БЫТЬ!'
                    ELSE '✅'
                END AS status
            FROM log
            ORDER BY id;
            """;
        System.out.println(sql);
    }
}
