/**
 * Задача 06 — Модуль 50: паттерн DAO
 *
 * ДЛЯ ЗАПУСКА: H2 (com.h2database:h2), URL jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1
 *
 * ЗАДАНИЕ:
 *   Реализуйте класс JdbcAccountDao, который имплементирует интерфейс AccountDao.
 *   Методы для реализации:
 *     - findById(long id)      — SELECT по id, вернуть Account или null если не найден.
 *     - save(Account account)  — INSERT строки в таблицу accounts.
 *     - updateBalance(long id, long newBalance) — UPDATE balance WHERE id = ?
 *   Используйте PreparedStatement с параметрами (?), не строковую конкатенацию.
 *   В main() создайте таблицу, создайте AccountDao, сохраните счёт,
 *   найдите его по id, обновите баланс, найдите снова — выведите на экран.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Найден: Account[id=1, owner=Алиса, balance=5000]
 *   После обновления: Account[id=1, owner=Алиса, balance=7500]
 *
 * ПОДСКАЗКА:
 *   try (PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts WHERE id = ?")) {
 *       ps.setLong(1, id);
 *       try (ResultSet rs = ps.executeQuery()) {
 *           if (rs.next()) return new Account(rs.getLong("id"), ...);
 *       }
 *   }
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Task06 {

    static final String URL  = "jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1";
    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String[] args) throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            // Создать таблицу
            try (Statement st = con.createStatement()) {
                st.execute("CREATE TABLE IF NOT EXISTS accounts " +
                           "(id BIGINT PRIMARY KEY, owner VARCHAR(100), balance BIGINT)");
            }

            AccountDao dao = new JdbcAccountDao(con);

            // Сохранить счёт
            dao.save(new Account(1L, "Алиса", 5000L));

            // Найти и вывести
            Account found = dao.findById(1L);
            System.out.println("Найден: " + found);

            // Обновить баланс и снова найти
            dao.updateBalance(1L, 7500L);
            System.out.println("После обновления: " + dao.findById(1L));
        }
    }
}

// --- Доменный объект (готов, не менять) ---

record Account(long id, String owner, long balance) {
    @Override
    public String toString() {
        return "Account[id=" + id + ", owner=" + owner + ", balance=" + balance + "]";
    }
}

// --- Интерфейс DAO (готов, не менять) ---

interface AccountDao {
    /** Найти счёт по id. Вернуть null, если не существует. */
    Account findById(long id) throws SQLException;

    /** Сохранить новый счёт (INSERT). */
    void save(Account account) throws SQLException;

    /** Обновить баланс счёта по id. */
    void updateBalance(long id, long newBalance) throws SQLException;
}

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
