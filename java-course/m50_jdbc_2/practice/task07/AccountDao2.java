package m50_jdbc_2.practice.task07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// --- Интерфейс DAO (готов) ---
interface AccountDao2 {
    /** Найти счёт по id. Вернуть null если не существует. */
    Account2 findById(long id) throws SQLException;
    /** Сохранить новый счёт (INSERT). */
    void save(Account2 account) throws SQLException;
    /** Обновить баланс счёта. */
    void updateBalance(long id, long newBalance) throws SQLException;
}
