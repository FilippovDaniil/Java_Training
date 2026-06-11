package m50_jdbc_2.practice;

/**
 * Задача 04 — Модуль 50: batch-вставка
 *
 * ДЛЯ ЗАПУСКА: H2 (com.h2database:h2), URL jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1
 *
 * ЗАДАНИЕ:
 *   Реализуйте метод insertBatch(Connection con, int count):
 *     1) Отключите auto-commit.
 *     2) Подготовьте PreparedStatement:
 *        INSERT INTO accounts(id, owner, balance) VALUES(?, ?, ?)
 *     3) В цикле (i от 1 до count включительно):
 *        - setLong(1, i), setString(2, "Клиент " + i), setLong(3, i * 100L)
 *        - ps.addBatch()
 *     4) Вызовите ps.executeBatch() — одна отправка пакета в БД.
 *     5) Вызовите con.commit().
 *   В main() вставьте 500 строк, подсчитайте COUNT(*) и выведите результат.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Вставлено строк: 500
 *
 * ПОДСКАЗКА:
 *   // Каждый addBatch() НЕ отправляет запрос — только добавляет в очередь.
 *   // executeBatch() отправляет все накопленные команды одним roundtrip.
 *   // Сравните: без batch = 500 roundtrip; с batch = 1 roundtrip.
 *   int[] results = ps.executeBatch(); // results[i] = кол-во строк i-й команды
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Task04 {

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

            // Вставить 500 строк одним batch
            insertBatch(con, 500);

            // Подсчитать и вывести количество строк
            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM accounts")) {
                if (rs.next()) {
                    System.out.println("Вставлено строк: " + rs.getLong(1));
                }
            }
        }
    }

    /**
     * Вставляет count строк в таблицу accounts одним batch-запросом.
     */
    static void insertBatch(Connection con, int count) throws SQLException {
        // TODO: con.setAutoCommit(false)
        // TODO: PreparedStatement с INSERT INTO accounts(id, owner, balance) VALUES(?, ?, ?)
        // TODO: цикл: setLong / setString / setLong / addBatch()
        // TODO: ps.executeBatch()
        // TODO: con.commit()
    }
}
