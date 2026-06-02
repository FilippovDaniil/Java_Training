/**
 * Задача 01 — Модуль 50: try-with-resources для JDBC-ресурсов
 *
 * ДЛЯ ЗАПУСКА: H2 (com.h2database:h2), URL jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1
 *
 * ЗАДАНИЕ:
 *   1) Создайте таблицу accounts (id BIGINT PRIMARY KEY, owner VARCHAR(100), balance BIGINT).
 *   2) Вставьте две строки: (1, 'Алиса', 5000), (2, 'Боб', 3000).
 *   3) Считайте все строки через SELECT и выведите на экран.
 *   Все JDBC-ресурсы (Connection, Statement, ResultSet) должны быть
 *   открыты через try-with-resources — закрытие гарантировано автоматически.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   1 | Алиса | 5000
 *   2 | Боб   | 3000
 *
 * ПОДСКАЗКА:
 *   try (Connection con = DriverManager.getConnection(URL);
 *        Statement  st  = con.createStatement()) {
 *       st.execute("CREATE TABLE ...");
 *       ...
 *       try (ResultSet rs = st.executeQuery("SELECT * FROM accounts")) {
 *           while (rs.next()) { ... }
 *       }
 *   }
 *   DriverManager.getConnection("jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1", "sa", "")
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Task01 {

    static final String URL  = "jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1";
    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String[] args) throws Exception {
        // TODO: откройте Connection через try-with-resources
        // TODO: создайте таблицу accounts (id, owner, balance)
        // TODO: вставьте двух клиентов
        // TODO: сделайте SELECT и выведите строки через System.out.println
        // TODO: убедитесь, что ResultSet и Statement тоже в try-with-resources
    }
}
