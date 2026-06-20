package m50_jdbc_2.practice;

/**
 * Задача 02 — Модуль 50: транзакция — перевод денег между счетами
 *
 * ДЛЯ ЗАПУСКА: H2 (com.h2database:h2), URL jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1
 *
 * ЗАДАНИЕ:
 *   Реализуйте метод transfer(Connection con, long fromId, long toId, long amount):
 *     1) отключите auto-commit: con.setAutoCommit(false);
 *     2) спишите amount с fromId: UPDATE accounts SET balance = balance - amount WHERE id = ?
 *     3) зачислите amount на toId: UPDATE accounts SET balance = balance + amount WHERE id = ?
 *     4) вызовите con.commit() — оба UPDATE фиксируются атомарно.
 *     5) в блоке catch (SQLException) вызовите con.rollback() и перебросьте исключение.
 *   В main() создайте таблицу, добавьте два счёта (Алиса 5000, Боб 1000),
 *   переведите 1500 от Алисы к Бобу, выведите итоговые балансы.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   До перевода:  Алиса=5000, Боб=1000
 *   После перевода: Алиса=3500, Боб=2500
 *
 * ПОДСКАЗКА:
 *   con.setAutoCommit(false);
 *   try {
 *       // два UPDATE через PreparedStatement
 *       con.commit();
 *   } catch (SQLException e) {
 *       con.rollback();
 *       throw e;
 *   }
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Task02 {

    static final String URL  = "jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1";
    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            setup(con);

            System.out.println("=== РАСШИРЕННАЯ БАНКОВСКАЯ СИСТЕМА ===\n");
            System.out.println("Начальные балансы:");
            printBalances(con);

            // 1. Успешный перевод
            System.out.println("\n--- 1. Успешный перевод (Алиса -> Боб, 1500) ---");
            transferWithLog(con, 1, 2, 1500, "Перевод от Алисы к Бобу");

            // 2. Перевод с недостатком средств
            System.out.println("\n--- 2. Перевод с недостатком (Боб -> Алиса, 2000) ---");
            try {
                transferWithLog(con, 2, 1, 2000, "Перевод от Боба к Алисе");
            } catch (SQLException e) {
                System.out.println("   ❌ Ошибка: " + e.getMessage());
            }

            // 3. Перевод между несколькими счетами
            System.out.println("\n--- 3. Перевод с несколькими счетами ---");
            transferWithLog(con, 3, 1, 1000, "Перевод от Чарли к Алисе");
            transferWithLog(con, 1, 2, 500, "Перевод от Алисы к Бобу");

            // 4. Попытка перевода на несуществующий счет
            System.out.println("\n--- 4. Попытка перевода на несуществующий счет ---");
            try {
                transferWithLog(con, 1, 99, 100, "Перевод на несуществующий счет");
            } catch (SQLException e) {
                System.out.println("   ❌ Ошибка: " + e.getMessage());
            }

            System.out.println("\nФинальные балансы:");
            printBalances(con);

        } catch (SQLException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Соединение закрыто");
    }

    /**
     * Перевод с дополнительным логированием
     */
    static void transferWithLog(Connection con, long fromId, long toId, long amount, String description)
            throws SQLException {
        System.out.printf("   %s: %d -> %d, сумма: %d%n", description, fromId, toId, amount);

        try {
            transfer(con, fromId, toId, amount);
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Переводит amount из счёта fromId на счёт toId в одной транзакции.
     * С дополнительной проверкой и логированием.
     */
    static void transfer(Connection con, long fromId, long toId, long amount)
            throws SQLException {
        if (amount <= 0) {
            throw new SQLException("Сумма перевода должна быть положительной");
        }
        if (fromId == toId) {
            throw new SQLException("Нельзя перевести деньги самому себе");
        }

        con.setAutoCommit(false);

        try {
            // Проверка баланса
            long fromBalance = getBalance(con, fromId);
            if (fromBalance < amount) {
                throw new SQLException(
                        String.format("Недостаточно средств. Баланс: %d, требуется: %d",
                                fromBalance, amount)
                );
            }

            // Проверка существования счетов
            if (!accountExists(con, toId)) {
                throw new SQLException("Счет получателя не найден: " + toId);
            }

            // Списываем
            String debitSQL = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(debitSQL)) {
                ps.setLong(1, amount);
                ps.setLong(2, fromId);
                ps.executeUpdate();
                System.out.println("      ✅ Списано " + amount + " со счета " + fromId);
            }

            // Зачисляем
            String creditSQL = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(creditSQL)) {
                ps.setLong(1, amount);
                ps.setLong(2, toId);
                ps.executeUpdate();
                System.out.println("      ✅ Зачислено " + amount + " на счет " + toId);
            }

            con.commit();
            System.out.println("      ✅ Транзакция завершена");

        } catch (SQLException e) {
            System.out.println("      ❌ Откат транзакции");
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }

    private static long getBalance(Connection con, long accountId) throws SQLException {
        String sql = "SELECT balance FROM accounts WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("balance");
                }
                throw new SQLException("Счет не найден: " + accountId);
            }
        }
    }

    private static boolean accountExists(Connection con, long accountId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM accounts WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
                return false;
            }
        }
    }

    static void setup(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
            st.execute("""
                CREATE TABLE IF NOT EXISTS accounts (
                    id      BIGINT PRIMARY KEY,
                    owner   VARCHAR(100),
                    balance BIGINT CHECK (balance >= 0)
                )
            """);
            st.execute("DELETE FROM accounts");
            st.execute("INSERT INTO accounts (id, owner, balance) VALUES (1, 'Алиса', 5000)");
            st.execute("INSERT INTO accounts (id, owner, balance) VALUES (2, 'Боб', 1000)");
            st.execute("INSERT INTO accounts (id, owner, balance) VALUES (3, 'Чарли', 3000)");
        }
    }

    static void printBalances(Connection con) throws SQLException {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, owner, balance FROM accounts ORDER BY id")) {
            System.out.println("ID | Владелец | Баланс");
            System.out.println("---|----------|--------");
            while (rs.next()) {
                System.out.printf("%-2d | %-8s | %d%n",
                        rs.getLong("id"),
                        rs.getString("owner"),
                        rs.getLong("balance"));
            }
        }
    }
}