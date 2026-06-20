package m50_jdbc_2.practice.task07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// --- Реализация DAO (готова, не менять) ---
class JdbcAccountDao2 implements AccountDao2 {

    private final Connection con;

    JdbcAccountDao2(Connection con) { this.con = con; }

    @Override
    public Account2 findById(long id) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM accounts WHERE id = ?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account2(
                            rs.getLong("id"),
                            rs.getString("owner"),
                            rs.getLong("balance")
                    );
                }
                return null;
            }
        }
    }

    @Override
    public void save(Account2 account) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "INSERT INTO accounts(id, owner, balance) VALUES(?, ?, ?)")) {
            ps.setLong(1, account.id);
            ps.setString(2, account.owner);
            ps.setLong(3, account.balance);
            ps.executeUpdate();
        }
    }

    @Override
    public void updateBalance(long id, long newBalance) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "UPDATE accounts SET balance = ? WHERE id = ?")) {
            ps.setLong(1, newBalance);
            ps.setLong(2, id);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Счет с ID " + id + " не найден");
            }
        }
    }
}
