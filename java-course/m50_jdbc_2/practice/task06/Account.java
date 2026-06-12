package m50_jdbc_2.practice.task06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// --- Доменный объект (готов, не менять) ---
record Account(long id, String owner, long balance) {
    @Override
    public String toString() {
        return "Account[id=" + id + ", owner=" + owner + ", balance=" + balance + "]";
    }
}
