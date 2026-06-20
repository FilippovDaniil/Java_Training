package m50_jdbc_2.practice;

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
import java.sql.*;

public class Task01 {

    static final String URL  = "jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1";
    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String[] args) throws SQLException {
        System.out.println("=== РАСШИРЕННЫЙ БАНКОВСКИЙ ПРИМЕР ===\n");

        // try-with-resources для всех ресурсов
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            System.out.println("✅ Соединение установлено");
            System.out.println("   Авто-коммит: " + conn.getAutoCommit());
            System.out.println("   Каталог: " + conn.getCatalog());

            // 1. Создание таблицы
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS accounts (
                    id      BIGINT PRIMARY KEY,
                    owner   VARCHAR(100) NOT NULL,
                    balance BIGINT NOT NULL CHECK (balance >= 0),
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            """);
            System.out.println("✅ Таблица accounts создана");

            // 2. Очистка и вставка данных
            stmt.executeUpdate("DELETE FROM accounts");

            // Используем пакетную вставку
            stmt.addBatch("INSERT INTO accounts (id, owner, balance) VALUES (1, 'Алиса', 5000)");
            stmt.addBatch("INSERT INTO accounts (id, owner, balance) VALUES (2, 'Боб', 3000)");
            stmt.addBatch("INSERT INTO accounts (id, owner, balance) VALUES (3, 'Чарли', 7000)");
            stmt.addBatch("INSERT INTO accounts (id, owner, balance) VALUES (4, 'Диана', 2000)");

            int[] results = stmt.executeBatch();
            System.out.println("✅ Вставлено записей: " + results.length);

            // 3. SELECT с различными условиями
            System.out.println("\n--- ВСЕ СЧЕТА ---");
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM accounts ORDER BY id")) {
                printAccounts(rs);
            }

            System.out.println("\n--- СЧЕТА С БАЛАНСОМ > 3000 ---");
            try (ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM accounts WHERE balance > 3000 ORDER BY balance DESC")) {
                printAccounts(rs);
            }

            System.out.println("\n--- СЧЕТА С БАЛАНСОМ МЕНЬШЕ 4000 ---");
            try (ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM accounts WHERE balance < 4000 ORDER BY balance")) {
                printAccounts(rs);
            }

            // 4. Агрегатные функции
            System.out.println("\n--- СТАТИСТИКА ---");
            try (ResultSet rs = stmt.executeQuery("""
                SELECT 
                    COUNT(*) AS total_accounts,
                    SUM(balance) AS total_balance,
                    ROUND(AVG(balance), 2) AS avg_balance,
                    MIN(balance) AS min_balance,
                    MAX(balance) AS max_balance
                FROM accounts
            """)) {
                if (rs.next()) {
                    System.out.println("   Всего счетов: " + rs.getInt("total_accounts"));
                    System.out.println("   Общий баланс: " + rs.getLong("total_balance"));
                    System.out.println("   Средний баланс: " + rs.getDouble("avg_balance"));
                    System.out.println("   Минимальный баланс: " + rs.getLong("min_balance"));
                    System.out.println("   Максимальный баланс: " + rs.getLong("max_balance"));
                }
            }

            // 5. Обновление данных (перевод между счетами)
            System.out.println("\n--- ПЕРЕВОД СРЕДСТВ (Алиса -> Боб, 500) ---");

            // Начинаем транзакцию
            conn.setAutoCommit(false);

            try {
                // Списываем с Алисы
                stmt.executeUpdate("UPDATE accounts SET balance = balance - 500 WHERE id = 1");
                // Зачисляем Бобу
                stmt.executeUpdate("UPDATE accounts SET balance = balance + 500 WHERE id = 2");

                // Проверяем, что балансы не отрицательные
                try (ResultSet rs = stmt.executeQuery(
                        "SELECT balance FROM accounts WHERE id = 1 AND balance < 0")) {
                    if (rs.next()) {
                        throw new SQLException("Баланс Алисы стал отрицательным!");
                    }
                }

                conn.commit();
                System.out.println("✅ Перевод выполнен успешно");

                // Показываем обновленные балансы
                try (ResultSet rs = stmt.executeQuery(
                        "SELECT * FROM accounts WHERE id IN (1, 2)")) {
                    System.out.println("\nОбновленные счета:");
                    printAccounts(rs);
                }

            } catch (SQLException e) {
                conn.rollback();
                System.err.println("❌ Ошибка при переводе. Транзакция откачена.");
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            System.err.println("\n❌ Ошибка SQL:");
            System.err.println("   Сообщение: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Соединение автоматически закрыто (try-with-resources)");
    }

    /**
     * Вспомогательный метод для вывода счетов
     */
    private static void printAccounts(ResultSet rs) throws SQLException {
        System.out.println("ID | Владелец | Баланс | Дата создания");
        System.out.println("---|----------|--------|---------------");

        boolean hasData = false;
        while (rs.next()) {
            hasData = true;
            long id = rs.getLong("id");
            String owner = rs.getString("owner");
            long balance = rs.getLong("balance");
            String created = rs.getString("created_at");

            System.out.printf("%-2d | %-8s | %6d | %s%n", id, owner, balance,
                    created != null ? created.substring(0, 19) : "N/A");
        }

        if (!hasData) {
            System.out.println("   (нет данных)");
        }
    }
}