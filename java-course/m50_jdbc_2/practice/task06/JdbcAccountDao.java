package m50_jdbc_2.practice.task06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// --- Реализация (нужно заполнить) ---
class JdbcAccountDao implements AccountDao {

    private final Connection con;

    JdbcAccountDao(Connection con) {
        this.con = con;
    }

    @Override
    public Account findById(long id) throws SQLException {
        // TODO: PreparedStatement "SELECT * FROM accounts WHERE id = ?"
        // TODO: ps.setLong(1, id)
        // TODO: rs.next() -> new Account(rs.getLong("id"), rs.getString("owner"), rs.getLong("balance"))
        // TODO: вернуть null, если строка не найдена
        return null;
    }

    @Override
    public void save(Account account) throws SQLException {
        // TODO: PreparedStatement "INSERT INTO accounts(id, owner, balance) VALUES(?, ?, ?)"
        // TODO: заполнить параметры из account и выполнить
    }

    @Override
    public void updateBalance(long id, long newBalance) throws SQLException {
        // TODO: PreparedStatement "UPDATE accounts SET balance = ? WHERE id = ?"
        // TODO: заполнить параметры и выполнить
    }
}
