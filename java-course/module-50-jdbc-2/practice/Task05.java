/**
 * Задача 05 — Модуль 50: уровни изоляции транзакций
 *
 * ДЛЯ ЗАПУСКА: H2 (com.h2database:h2), URL jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1
 *
 * ТЕОРИЯ (повторение):
 *   Dirty Read        — читаем незафиксированные данные параллельной транзакции.
 *   Non-repeatable Read — тот же SELECT возвращает разные данные в одной транзакции.
 *   Phantom Read      — повторный SELECT возвращает другой набор строк.
 *
 *   Уровни (от слабого к сильному):
 *     READ_UNCOMMITTED  — не защищает ни от чего (максимальная скорость)
 *     READ_COMMITTED    — защита от Dirty Read
 *     REPEATABLE_READ   — защита от Dirty + Non-repeatable Read
 *     SERIALIZABLE      — защита от всех трёх (минимальная скорость)
 *
 * ЗАДАНИЕ:
 *   Реализуйте метод showIsolationLevel(Connection con):
 *     1) Вызовите con.getTransactionIsolation() и выведите числовой код.
 *     2) Переведите код в строку с помощью isolationName(int) (метод дан ниже).
 *   В main() откройте два соединения и для каждого:
 *     - установите уровень: READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE
 *       через con.setTransactionIsolation(Connection.TRANSACTION_*);
 *     - вызовите showIsolationLevel(con) и выведите результат.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Уровень соединения 1: 2 -> READ_COMMITTED
 *   Уровень соединения 2: 4 -> REPEATABLE_READ
 *   Уровень соединения 3: 8 -> SERIALIZABLE
 *
 * ПОДСКАЗКА:
 *   con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
 *   int level = con.getTransactionIsolation(); // возвращает числовой код
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Task05 {

    static final String URL  = "jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1";
    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String[] args) throws Exception {
        // TODO: открыть три Connection через try-with-resources
        // TODO: на каждом установить разный уровень изоляции и вызвать showIsolationLevel
    }

    /**
     * Выводит текущий уровень изоляции соединения.
     */
    static void showIsolationLevel(Connection con, int connectionNumber) throws SQLException {
        // TODO: con.getTransactionIsolation() -> вывести номер и имя уровня
    }

    // Вспомогательный метод (готов, не менять)
    static String isolationName(int level) {
        switch (level) {
            case Connection.TRANSACTION_NONE:             return "NONE";
            case Connection.TRANSACTION_READ_UNCOMMITTED: return "READ_UNCOMMITTED";
            case Connection.TRANSACTION_READ_COMMITTED:   return "READ_COMMITTED";
            case Connection.TRANSACTION_REPEATABLE_READ:  return "REPEATABLE_READ";
            case Connection.TRANSACTION_SERIALIZABLE:     return "SERIALIZABLE";
            default: return "UNKNOWN(" + level + ")";
        }
    }
}
