package m50_jdbc_2.practice.task06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// --- Реализация (нужно заполнить) ---
class JdbcAccountDao implements AccountDao {

    private final Connection con;

    JdbcAccountDao(Connection con) {
        this.con = con;
    }

    @Override
    public Account findById(long id) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                            rs.getLong("id"),
                            rs.getString("owner"),
                            rs.getLong("balance")
                    );
                }
            }
        }
        return null;
    }

    public Account findByOwner(String owner) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE owner = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, owner);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                            rs.getLong("id"),
                            rs.getString("owner"),
                            rs.getLong("balance")
                    );
                }
            }
        }
        return null;
    }

    public List<Account> findByBalanceGreaterThan(long minBalance) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE balance > ? ORDER BY balance";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, minBalance);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    accounts.add(new Account(
                            rs.getLong("id"),
                            rs.getString("owner"),
                            rs.getLong("balance")
                    ));
                }
            }
        }
        return accounts;
    }

    public List<Account> findAll() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts ORDER BY id";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                accounts.add(new Account(
                        rs.getLong("id"),
                        rs.getString("owner"),
                        rs.getLong("balance")
                ));
            }
        }
        return accounts;
    }

    @Override
    public void save(Account account) throws SQLException {
        String sql = "INSERT INTO accounts (id, owner, balance) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, account.id());
            ps.setString(2, account.owner());
            ps.setLong(3, account.balance());
            ps.executeUpdate();
        }
    }

    @Override
    public void updateBalance(long id, long newBalance) throws SQLException {
        String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, newBalance);
            ps.setLong(2, id);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Счет с ID " + id + " не найден");
            }
        }
    }

    public boolean updateBalanceSafe(long id, long newBalance) throws SQLException {
        String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, newBalance);
            ps.setLong(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(long id) throws SQLException {
        String sql = "DELETE FROM accounts WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean exists(long id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM accounts WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public AccountStats getStats() throws SQLException {
        String sql = """
            
                SELECT 
                COUNT(*) AS count,
                ROUND(AVG(balance)) AS avg_balance,
                MIN(balance) AS min_balance,
                MAX(balance) AS max_balance,
                SUM(balance) AS total_balance
            FROM accounts
            """;
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return new AccountStats(
                        rs.getInt("count"),
                        rs.getLong("avg_balance"),
                        rs.getLong("min_balance"),
                        rs.getLong("max_balance"),
                        rs.getLong("total_balance")
                );
            }
        }
        return new AccountStats(0, 0, 0, 0, 0);
    }
}