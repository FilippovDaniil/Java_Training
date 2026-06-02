/**
 * Задача 07 — Модуль 50: МИНИ-ПРОЕКТ — сервис переводов TransferService
 *
 * ДЛЯ ЗАПУСКА: H2 (com.h2database:h2), URL jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1
 *
 * ЗАДАНИЕ:
 *   Реализуйте TransferService.transfer(long fromId, long toId, long amount):
 *     1) Найдите счёт-источник через dao.findById(fromId).
 *        Если не найден — бросьте IllegalArgumentException("Счёт не найден: " + fromId).
 *     2) Найдите счёт-получатель через dao.findById(toId).
 *        Если не найден — бросьте IllegalArgumentException("Счёт не найден: " + toId).
 *     3) Если баланс источника меньше amount — бросьте
 *        IllegalStateException("Недостаточно средств: " + fromBalance + " < " + amount).
 *     4) Всё в одной транзакции:
 *        a) con.setAutoCommit(false)
 *        b) dao.updateBalance(fromId, fromBalance - amount)
 *        c) dao.updateBalance(toId,   toBalance   + amount)
 *        d) con.commit()
 *        e) в catch (Exception) -> con.rollback(), throw e (перебросить)
 *   В main() создайте три счёта, выполните успешный перевод и перевод
 *   с недостаточным балансом (перехватите исключение), выведите итоговые балансы.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Перевод 1500 от Алисы к Бобу...
 *   Успешно переведено.
 *   Перевод 9999 от Боба к Сергею (недостаточно средств)...
 *   Ошибка: Недостаточно средств: 2500 < 9999
 *   Итоговые балансы:
 *     Алиса   = 3500
 *     Боб     = 2500
 *     Сергей  = 1000
 *
 * ПОДСКАЗКА:
 *   - TransferService получает Connection и AccountDao2 в конструктор.
 *   - Все проверки (счёт существует, баланс достаточен) делать ДО начала транзакции.
 *   - rollback вызывать только если setAutoCommit(false) уже был вызван
 *     (используйте флаг или finally с проверкой con.getAutoCommit()).
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Task07 {

    static final String URL  = "jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1";
    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String[] args) throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            // Инициализация
            setup(con);

            AccountDao2     dao = new JdbcAccountDao2(con);
            TransferService svc = new TransferService(con, dao);

            // Успешный перевод
            System.out.println("Перевод 1500 от Алисы к Бобу...");
            try {
                svc.transfer(1, 2, 1500);
                System.out.println("Успешно переведено.");
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

            // Перевод с недостаточным балансом
            System.out.println("Перевод 9999 от Боба к Сергею (недостаточно средств)...");
            try {
                svc.transfer(2, 3, 9999);
                System.out.println("Успешно переведено.");
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

            // Итоговые балансы
            System.out.println("Итоговые балансы:");
            printBalances(con);
        }
    }

    // --- TransferService (нужно реализовать) ---

    static class TransferService {
        private final Connection  con;
        private final AccountDao2 dao;

        TransferService(Connection con, AccountDao2 dao) {
            this.con = con;
            this.dao = dao;
        }

        /**
         * Переводит amount со счёта fromId на счёт toId атомарно (транзакция).
         * Бросает IllegalArgumentException если счёт не найден.
         * Бросает IllegalStateException если недостаточно средств.
         */
        void transfer(long fromId, long toId, long amount) throws Exception {
            // TODO: найти оба счёта через dao.findById, проверить null
            // TODO: проверить баланс источника >= amount
            // TODO: con.setAutoCommit(false)
            // TODO: dao.updateBalance(fromId, fromBalance - amount)
            // TODO: dao.updateBalance(toId,   toBalance   + amount)
            // TODO: con.commit()
            // TODO: catch (Exception e) { con.rollback(); throw e; }
        }
    }

    // --- Вспомогательные методы (готовы, не менять) ---

    static void setup(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS accounts " +
                       "(id BIGINT PRIMARY KEY, owner VARCHAR(100), balance BIGINT)");
            st.execute("MERGE INTO accounts VALUES(1, 'Алиса',  5000)");
            st.execute("MERGE INTO accounts VALUES(2, 'Боб',    1000)");
            st.execute("MERGE INTO accounts VALUES(3, 'Сергей', 1000)");
        }
    }

    static void printBalances(Connection con) throws SQLException {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT owner, balance FROM accounts ORDER BY id")) {
            while (rs.next()) {
                System.out.printf("  %-8s = %d%n",
                        rs.getString("owner"), rs.getLong("balance"));
            }
        }
    }
}

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

// --- Интерфейс DAO (готов) ---

interface AccountDao2 {
    /** Найти счёт по id. Вернуть null если не существует. */
    Account2 findById(long id) throws SQLException;
    /** Сохранить новый счёт (INSERT). */
    void save(Account2 account) throws SQLException;
    /** Обновить баланс счёта. */
    void updateBalance(long id, long newBalance) throws SQLException;
}

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
            ps.executeUpdate();
        }
    }
}
