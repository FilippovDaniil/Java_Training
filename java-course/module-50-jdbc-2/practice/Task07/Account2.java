import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// --- Доменный объект (готов) ---
class Account2 {
    final long   id;
    final String owner;
    final long   balance;

    Account2(long id, String owner, long balance) {
        this.id      = id;
        this.owner   = owner;
        this.balance = balance;
    }
}
