package m50_jdbc_2.practice;

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
import java.sql.*;

public class Task05 {

    static final String URL  = "jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1";
    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String[] args) {
        try (Connection conn1 = DriverManager.getConnection(URL, USER, PASS);
             Connection conn2 = DriverManager.getConnection(URL, USER, PASS)) {

            System.out.println("=== ДЕМОНСТРАЦИЯ АНОМАЛИЙ ПРИ РАЗНЫХ УРОВНЯХ ИЗОЛЯЦИИ ===\n");

            // Подготовка таблицы
            setupTable(conn1);

            // Демонстрация Dirty Read на уровне READ_UNCOMMITTED
            System.out.println("--- 1. ДЕМОНСТРАЦИЯ DIRTY READ (READ_UNCOMMITTED) ---");
            demonstrateDirtyRead(conn1, conn2);

            // Демонстрация Non-repeatable Read на уровне READ_COMMITTED
            System.out.println("\n--- 2. ДЕМОНСТРАЦИЯ NON-REPEATABLE READ (READ_COMMITTED) ---");
            demonstrateNonRepeatableRead(conn1, conn2);

            // Демонстрация Phantom Read на уровне REPEATABLE_READ
            System.out.println("\n--- 3. ДЕМОНСТРАЦИЯ PHANTOM READ (REPEATABLE_READ) ---");
            demonstratePhantomRead(conn1, conn2);

            // Вывод таблицы с уровнями изоляции
            printIsolationLevelTable();

        } catch (SQLException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Соединения закрыты");
    }

    private static void setupTable(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
            st.execute("""
                CREATE TABLE IF NOT EXISTS accounts (
                    id      BIGINT PRIMARY KEY,
                    owner   VARCHAR(100),
                    balance BIGINT
                )
            """);
            st.execute("DELETE FROM accounts");
            st.execute("INSERT INTO accounts (id, owner, balance) VALUES (1, 'Алиса', 1000)");
            st.execute("INSERT INTO accounts (id, owner, balance) VALUES (2, 'Боб', 2000)");
            st.execute("INSERT INTO accounts (id, owner, balance) VALUES (3, 'Чарли', 3000)");
        }
    }

    private static void demonstrateDirtyRead(Connection conn1, Connection conn2) throws SQLException {
        // T1: READ_UNCOMMITTED
        conn1.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        // T2: READ_UNCOMMITTED
        conn2.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        System.out.println("   Уровень T1: READ_UNCOMMITTED");
        System.out.println("   Уровень T2: READ_UNCOMMITTED\n");

        // T1: BEGIN
        conn1.setAutoCommit(false);
        System.out.println("   T1: BEGIN");

        // T1: UPDATE (без COMMIT)
        try (Statement st = conn1.createStatement()) {
            st.executeUpdate("UPDATE accounts SET balance = 500 WHERE id = 1");
            System.out.println("   T1: UPDATE id=1 balance=500 (без COMMIT)");
        }

        // T2: SELECT (читает незафиксированные данные)
        try (Statement st = conn2.createStatement();
             ResultSet rs = st.executeQuery("SELECT balance FROM accounts WHERE id = 1")) {
            if (rs.next()) {
                long balance = rs.getLong("balance");
                System.out.printf("   T2: SELECT id=1 balance=%d (DIRTY READ!)\n", balance);
                System.out.println("   ❌ T2 видит незафиксированные данные T1!");
            }
        }

        // T1: ROLLBACK
        conn1.rollback();
        System.out.println("   T1: ROLLBACK (отмена изменений)");
        conn1.setAutoCommit(true);

        // Проверка финального состояния
        try (Statement st = conn1.createStatement();
             ResultSet rs = st.executeQuery("SELECT balance FROM accounts WHERE id = 1")) {
            if (rs.next()) {
                System.out.printf("   Финальный баланс id=1: %d (данные T1 отменены)\n", rs.getLong("balance"));
            }
        }
    }

    private static void demonstrateNonRepeatableRead(Connection conn1, Connection conn2) throws SQLException {
        // T1: READ_COMMITTED
        conn1.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        // T2: READ_COMMITTED
        conn2.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

        System.out.println("   Уровень T1: READ_COMMITTED");
        System.out.println("   Уровень T2: READ_COMMITTED\n");

        // T1: BEGIN
        conn1.setAutoCommit(false);
        System.out.println("   T1: BEGIN");

        // T1: Первое чтение
        try (Statement st = conn1.createStatement();
             ResultSet rs = st.executeQuery("SELECT balance FROM accounts WHERE id = 2")) {
            if (rs.next()) {
                System.out.printf("   T1: SELECT id=2 balance=%d (первое чтение)\n", rs.getLong("balance"));
            }
        }

        // T2: UPDATE и COMMIT
        try (Statement st = conn2.createStatement()) {
            st.executeUpdate("UPDATE accounts SET balance = 2500 WHERE id = 2");
            System.out.println("   T2: UPDATE id=2 balance=2500 и COMMIT");
        }

        // T1: Второе чтение (в той же транзакции)
        try (Statement st = conn1.createStatement();
             ResultSet rs = st.executeQuery("SELECT balance FROM accounts WHERE id = 2")) {
            if (rs.next()) {
                long balance = rs.getLong("balance");
                System.out.printf("   T1: SELECT id=2 balance=%d (второе чтение)\n", balance);
                if (balance == 2500) {
                    System.out.println("   ❌ T1 видит разные значения (NON-REPEATABLE READ)!");
                }
            }
        }

        conn1.commit();
        conn1.setAutoCommit(true);
    }

    private static void demonstratePhantomRead(Connection conn1, Connection conn2) throws SQLException {
        // T1: REPEATABLE_READ
        conn1.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        // T2: REPEATABLE_READ
        conn2.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

        System.out.println("   Уровень T1: REPEATABLE_READ");
        System.out.println("   Уровень T2: REPEATABLE_READ\n");

        // T1: BEGIN
        conn1.setAutoCommit(false);
        System.out.println("   T1: BEGIN");

        // T1: Первое чтение (счет)
        try (Statement st = conn1.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM accounts")) {
            if (rs.next()) {
                System.out.printf("   T1: SELECT COUNT(*) = %d (первое чтение)\n", rs.getLong(1));
            }
        }

        // T2: INSERT и COMMIT
        try (Statement st = conn2.createStatement()) {
            st.executeUpdate("INSERT INTO accounts (id, owner, balance) VALUES (4, 'Диана', 4000)");
            System.out.println("   T2: INSERT нового счета (id=4) и COMMIT");
        }

        // T1: Второе чтение (в той же транзакции)
        try (Statement st = conn1.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM accounts")) {
            if (rs.next()) {
                long count = rs.getLong(1);
                System.out.printf("   T1: SELECT COUNT(*) = %d (второе чтение)\n", count);
                if (count > 3) {
                    System.out.println("   ❌ T1 видит новые строки (PHANTOM READ)!");
                }
            }
        }

        conn1.commit();
        conn1.setAutoCommit(true);
    }

    private static void printIsolationLevelTable() {
        System.out.println("\n=== ТАБЛИЦА УРОВНЕЙ ИЗОЛЯЦИИ ===");
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        System.out.println("│                   АНОМАЛИИ ПО УРОВНЯМ ИЗОЛЯЦИИ                │");
        System.out.println("├───────────────────┬───────────┬───────────┬───────────┬────────┤");
        System.out.println("│     УРОВЕНЬ       │   КОД    │  Dirty   │ Non-rep. │ Phantom │");
        System.out.println("│                   │          │   Read   │   Read   │  Read   │");
        System.out.println("├───────────────────┼───────────┼───────────┼───────────┼────────┤");
        System.out.println("│ READ_UNCOMMITTED  │     1    │   ❌    │   ❌    │   ❌   │");
        System.out.println("│ READ_COMMITTED    │     2    │   ✅    │   ❌    │   ❌   │");
        System.out.println("│ REPEATABLE_READ   │     4    │   ✅    │   ✅    │   ❌   │");
        System.out.println("│ SERIALIZABLE      │     8    │   ✅    │   ✅    │   ✅   │");
        System.out.println("└───────────────────┴───────────┴───────────┴───────────┴────────┘");

        System.out.println("\n   ✅ = защищает от аномалии");
        System.out.println("   ❌ = НЕ защищает от аномалии");
        System.out.println("\n📌 С увеличением уровня изоляции:");
        System.out.println("   - Производительность ↓");
        System.out.println("   - Защита от аномалий ↑");
    }
}
