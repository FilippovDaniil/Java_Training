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

    public static void main(String[] args) throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            // Подготовка: создать таблицу и добавить счета
            setup(con);

            System.out.println("До перевода:");
            printBalances(con);

            // TODO: вызовите transfer(con, 1, 2, 1500)
            transfer(con, 1, 2, 1500);

            System.out.println("После перевода:");
            printBalances(con);
        }
    }

    /**
     * Переводит amount из счёта fromId на счёт toId в одной транзакции.
     * При ошибке делает rollback и перебрасывает исключение.
     */
    static void transfer(Connection con, long fromId, long toId, long amount)
            throws SQLException {
        // TODO: con.setAutoCommit(false)
        // TODO: PreparedStatement — UPDATE ... WHERE id = ? (списать)
        // TODO: PreparedStatement — UPDATE ... WHERE id = ? (зачислить)
        // TODO: con.commit()
        // TODO: catch (SQLException) -> con.rollback(); throw e;
    }

    // --- вспомогательные методы (готовы, не менять) ---

    static void setup(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS accounts " +
                       "(id BIGINT PRIMARY KEY, owner VARCHAR(100), balance BIGINT)");
            st.execute("MERGE INTO accounts VALUES(1, 'Алиса', 5000)");
            st.execute("MERGE INTO accounts VALUES(2, 'Боб',   1000)");
        }
    }

    static void printBalances(Connection con) throws SQLException {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, owner, balance FROM accounts ORDER BY id")) {
            while (rs.next()) {
                System.out.printf("  %s = %d%n", rs.getString("owner"), rs.getLong("balance"));
            }
        }
    }
}
