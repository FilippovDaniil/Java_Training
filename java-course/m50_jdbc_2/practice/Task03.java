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

    public static void main(String[] args) throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            // Создать таблицу
            try (Statement st = con.createStatement()) {
                st.execute("CREATE TABLE IF NOT EXISTS accounts " +
                           "(id BIGINT PRIMARY KEY, owner VARCHAR(100), balance BIGINT)");
            }

            con.setAutoCommit(false);
            try {
                // TODO: вставить строку (1, 'Алиса', 5000) — должна пройти
                // TODO: вставить строку (1, 'Дубль', 0) — должна выбросить SQLException
                // TODO: con.commit()
                // Заглушка для компиляции; удалить после реализации TODO выше:
                if (con == null) throw new java.sql.SQLException("stub");
            } catch (java.sql.SQLException e) {
                // TODO: напечатать getSQLState(), getErrorCode(), getMessage()
                // TODO: con.rollback()
            }

            // После ошибки: выведите все строки из accounts
            System.out.println("Таблица после ошибки:");
            // TODO: SELECT * FROM accounts и напечатать строки
        }
    }
}
