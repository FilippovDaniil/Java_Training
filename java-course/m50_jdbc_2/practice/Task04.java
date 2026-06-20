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

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("=== СРАВНЕНИЕ ПРОИЗВОДИТЕЛЬНОСТИ BATCH vs ОБЫЧНАЯ ВСТАВКА ===\n");

            // Создание таблицы
            try (Statement st = con.createStatement()) {
                st.execute("""
                    CREATE TABLE IF NOT EXISTS accounts (
                        id      BIGINT PRIMARY KEY,
                        owner   VARCHAR(100),
                        balance BIGINT
                    )
                """);
                System.out.println("✅ Таблица accounts создана");
            }

            int testCount = 1000;

            // 1. Обычная вставка (без batch)
            System.out.println("\n--- 1. ОБЫЧНАЯ ВСТАВКА (без batch) ---");
            clearTable(con);
            long startTime = System.currentTimeMillis();
            insertWithoutBatch(con, testCount);
            long endTime = System.currentTimeMillis();
            System.out.println("   Время: " + (endTime - startTime) + " ms");
            printCount(con);

            // 2. Batch-вставка
            System.out.println("\n--- 2. BATCH-ВСТАВКА ---");
            clearTable(con);
            startTime = System.currentTimeMillis();
            insertWithBatch(con, testCount);
            endTime = System.currentTimeMillis();
            System.out.println("   Время: " + (endTime - startTime) + " ms");
            printCount(con);

            // 3. Batch с оптимальным размером
            System.out.println("\n--- 3. BATCH С ОПТИМАЛЬНЫМ РАЗМЕРОМ (100 записей) ---");
            clearTable(con);
            startTime = System.currentTimeMillis();
            insertWithOptimalBatch(con, testCount, 100);
            endTime = System.currentTimeMillis();
            System.out.println("   Время: " + (endTime - startTime) + " ms");
            printCount(con);

            // Вывод рекомендаций
            printRecommendations();

        } catch (SQLException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Соединение закрыто");
    }

    /**
     * Вставка без batch (каждая команда отдельно)
     */
    static void insertWithoutBatch(Connection con, int count) throws SQLException {
        String sql = "INSERT INTO accounts (id, owner, balance) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 1; i <= count; i++) {
                ps.setLong(1, i);
                ps.setString(2, "Клиент " + i);
                ps.setLong(3, i * 100L);
                ps.executeUpdate();
            }
        }
    }

    /**
     * Batch-вставка (один пакет)
     */
    static void insertWithBatch(Connection con, int count) throws SQLException {
        con.setAutoCommit(false);

        String sql = "INSERT INTO accounts (id, owner, balance) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 1; i <= count; i++) {
                ps.setLong(1, i);
                ps.setString(2, "Клиент " + i);
                ps.setLong(3, i * 100L);
                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
        } finally {
            con.setAutoCommit(true);
        }
    }

    /**
     * Batch-вставка с оптимальным размером пакета
     */
    static void insertWithOptimalBatch(Connection con, int count, int batchSize) throws SQLException {
        con.setAutoCommit(false);

        String sql = "INSERT INTO accounts (id, owner, balance) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            int batchCount = 0;
            for (int i = 1; i <= count; i++) {
                ps.setLong(1, i);
                ps.setString(2, "Клиент " + i);
                ps.setLong(3, i * 100L);
                ps.addBatch();
                batchCount++;

                // Выполняем пакет при достижении batchSize
                if (batchCount % batchSize == 0) {
                    ps.executeBatch();
                    batchCount = 0;
                }
            }
            // Выполняем оставшиеся записи
            if (batchCount > 0) {
                ps.executeBatch();
            }
            con.commit();
        } finally {
            con.setAutoCommit(true);
        }
    }

    private static void clearTable(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
            st.execute("DELETE FROM accounts");
        }
    }

    private static void printCount(Connection con) throws SQLException {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM accounts")) {
            if (rs.next()) {
                System.out.println("   Всего записей: " + rs.getLong(1));
            }
        }
    }

    private static void printRecommendations() {
        System.out.println("\n=== РЕКОМЕНДАЦИИ ===");
        System.out.println("📌 Batch-вставка значительно быстрее обычной для большого количества записей");
        System.out.println("📌 Рекомендуемый размер пакета: 100-1000 записей");
        System.out.println("📌 Используйте batch для массовых вставок, обновлений и удалений");
        System.out.println("📌 Не забывайте про commit() и rollback() при ошибках");
        System.out.println("📌 Для очень больших пакетов используйте разбивку на части");
    }
}