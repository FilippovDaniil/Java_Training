package m50_jdbc_2.practice;

/**
 * Задача 03 — Модуль 50: обработка SQLException
 *
 * ДЛЯ ЗАПУСКА: H2 (com.h2database:h2), URL jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1
 *
 * ЗАДАНИЕ:
 *   1) Создайте таблицу accounts с PRIMARY KEY на id.
 *   2) Вставьте строку (1, 'Алиса', 5000).
 *   3) Попробуйте вставить дубликат (1, 'Дубль', 0) — это вызовет SQLException.
 *   4) В блоке catch поймайте SQLException и выведите:
 *        SQLState : <значение из getSQLState()>
 *        ErrorCode: <значение из getErrorCode()>
 *        Message  : <значение из getMessage()>
 *   5) Вызовите con.rollback() в catch, чтобы откатить транзакцию.
 *   6) После catch убедитесь, что таблица не повреждена: выведите все строки.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно, H2):
 *   SQLState : 23505
 *   ErrorCode: 23505
 *   Message  : Unique index or primary key violation: ...
 *   Таблица после ошибки:
 *   1 | Алиса | 5000
 *
 * ПОДСКАЗКА:
 *   con.setAutoCommit(false);
 *   try {
 *       // нормальная вставка, потом дубликат
 *       con.commit();
 *   } catch (SQLException e) {
 *       System.out.println("SQLState : " + e.getSQLState());
 *       System.out.println("ErrorCode: " + e.getErrorCode());
 *       System.out.println("Message  : " + e.getMessage());
 *       con.rollback();
 *   }
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Task03 {

    static final String URL  = "jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1";
    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("=== РАСШИРЕННАЯ ОБРАБОТКА SQLException ===\n");

            // Создание таблицы
            try (Statement st = con.createStatement()) {
                st.execute("""
                    CREATE TABLE IF NOT EXISTS accounts (
                        id      BIGINT PRIMARY KEY,
                        owner   VARCHAR(100),
                        balance BIGINT CHECK (balance >= 0)
                    )
                """);
                System.out.println("✅ Таблица accounts создана");
            }

            // 1. Демонстрация ошибки PRIMARY KEY
            demonstrateDuplicateKeyError(con);

            // 2. Демонстрация ошибки CHECK constraint
            demonstrateCheckConstraintError(con);

            // 3. Демонстрация ошибки NOT NULL (если добавить)
            demonstrateNullError(con);

            // 4. Демонстрация восстановления после ошибки
            demonstrateRecovery(con);

        } catch (SQLException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Соединение закрыто");
    }

    private static void demonstrateDuplicateKeyError(Connection con) throws SQLException{
        System.out.println("\n--- 1. НАРУШЕНИЕ PRIMARY KEY ---");
        con.setAutoCommit(false);

        try {
            // Очищаем таблицу
            try (Statement st = con.createStatement()) {
                st.execute("DELETE FROM accounts");
            }

            // Вставляем корректную запись
            try (Statement st = con.createStatement()) {
                st.executeUpdate(
                        "INSERT INTO accounts (id, owner, balance) VALUES (1, 'Алиса', 5000)"
                );
                System.out.println("   ✅ Вставлена запись (1, 'Алиса', 5000)");
            }

            // Пытаемся вставить дубликат
            try (Statement st = con.createStatement()) {
                st.executeUpdate(
                        "INSERT INTO accounts (id, owner, balance) VALUES (1, 'Дубль', 0)"
                );
                System.out.println("   ❌ Дубликат вставлен (не должно быть!)");
            }

            con.commit();

        } catch (SQLException e) {
            System.out.println("   ❌ ПЕРЕХВАТЧЕНО SQLException:");
            System.out.println("      Тип: DUPLICATE KEY");
            System.out.println("      SQLState: " + e.getSQLState());
            System.out.println("      ErrorCode: " + e.getErrorCode());
            System.out.println("      Message: " + e.getMessage());

            try {
                con.rollback();
                System.out.println("   ✅ ROLLBACK выполнен");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void demonstrateCheckConstraintError(Connection con) throws SQLException{
        System.out.println("\n--- 2. НАРУШЕНИЕ CHECK CONSTRAINT ---");
        con.setAutoCommit(false);

        try {
            // Очищаем таблицу
            try (Statement st = con.createStatement()) {
                st.execute("DELETE FROM accounts");
            }

            // Вставляем корректную запись
            try (Statement st = con.createStatement()) {
                st.executeUpdate(
                        "INSERT INTO accounts (id, owner, balance) VALUES (1, 'Алиса', 5000)"
                );
                System.out.println("   ✅ Вставлена запись (1, 'Алиса', 5000)");
            }

            // Пытаемся вставить запись с отрицательным балансом
            try (Statement st = con.createStatement()) {
                st.executeUpdate(
                        "INSERT INTO accounts (id, owner, balance) VALUES (2, 'Боб', -1000)"
                );
                System.out.println("   ❌ Запись с отрицательным балансом вставлена (не должно быть!)");
            }

            con.commit();

        } catch (SQLException e) {
            System.out.println("   ❌ ПЕРЕХВАТЧЕНО SQLException:");
            System.out.println("      Тип: CHECK CONSTRAINT");
            System.out.println("      SQLState: " + e.getSQLState());
            System.out.println("      ErrorCode: " + e.getErrorCode());
            System.out.println("      Message: " + e.getMessage());

            try {
                con.rollback();
                System.out.println("   ✅ ROLLBACK выполнен");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void demonstrateNullError(Connection con) {
        System.out.println("\n--- 3. НАРУШЕНИЕ NOT NULL (если добавить) ---");
        // В H2 NOT NULL можно добавить через ALTER TABLE, но для демонстрации пропускаем
        System.out.println("   (NOT NULL ошибки не демонстрируются, т.к. столбцы не имеют этого ограничения)");
    }

    private static void demonstrateRecovery(Connection con) {
        System.out.println("\n--- 4. ВОССТАНОВЛЕНИЕ ПОСЛЕ ОШИБКИ ---");

        try (Statement st = con.createStatement()) {
            // Очищаем таблицу
            st.execute("DELETE FROM accounts");

            // Вставляем корректные данные
            st.executeUpdate(
                    "INSERT INTO accounts (id, owner, balance) VALUES (1, 'Алиса', 5000)"
            );
            st.executeUpdate(
                    "INSERT INTO accounts (id, owner, balance) VALUES (2, 'Боб', 3000)"
            );
            st.executeUpdate(
                    "INSERT INTO accounts (id, owner, balance) VALUES (3, 'Чарли', 7000)"
            );
            System.out.println("   ✅ Вставлены корректные данные");

            // Выводим результат
            System.out.println("\n   Содержимое таблицы:");
            try (ResultSet rs = st.executeQuery("SELECT * FROM accounts ORDER BY id")) {
                System.out.println("   ID | Владелец | Баланс");
                System.out.println("   ---|----------|--------");
                while (rs.next()) {
                    System.out.printf("   %-2d | %-8s | %d%n",
                            rs.getLong("id"),
                            rs.getString("owner"),
                            rs.getLong("balance"));
                }
            }

        } catch (SQLException e) {
            System.err.println("   ❌ Ошибка восстановления: " + e.getMessage());
        }
    }
}
