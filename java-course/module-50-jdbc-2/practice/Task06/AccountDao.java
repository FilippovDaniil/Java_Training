import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// --- Интерфейс DAO (готов, не менять) ---
interface AccountDao {
    /** Найти счёт по id. Вернуть null, если не существует. */
    Account findById(long id) throws SQLException;

    /** Сохранить новый счёт (INSERT). */
    void save(Account account) throws SQLException;

    /** Обновить баланс счёта по id. */
    void updateBalance(long id, long newBalance) throws SQLException;
}
