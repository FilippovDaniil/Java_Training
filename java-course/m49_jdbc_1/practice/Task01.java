package m49_jdbc_1.practice;

/**
 * Задача 01 — Модуль 49: Установка соединения с базой данных
 *
 * ДЛЯ ЗАПУСКА: H2 (com.h2database:h2), URL jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
 * (для компиляции H2 не нужен — только java.sql.* из JDK)
 *
 * ЗАДАНИЕ:
 *   1) Используя DriverManager.getConnection(url, user, pass), получите
 *      объект Connection к базе H2 в памяти.
 *      url  = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1"
 *      user = "sa", pass = ""
 *   2) Выведите на экран: "Соединение установлено: <значение conn.toString()>"
 *   3) Корректно закройте соединение (используйте try-with-resources).
 *   4) После блока try выведите: "Соединение закрыто: <conn.isClosed()>"
 *      — убедитесь, что isClosed() вернёт true.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Соединение установлено: conn session-id=...
 *   Соединение закрыто: true
 *
 * ПОДСКАЗКА:
 *   Connection conn = DriverManager.getConnection(url, user, pass);
 *   conn.isClosed() — проверить, закрыто ли соединение.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Task01 {
    public static void main(String[] args) {
        String url  = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String pass = "";

        // Способ 2: try-with-resources (рекомендуемый для Java 7+)
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            System.out.println("Соединение установлено: " + conn.toString());
            System.out.println("   isClosed(): " + conn.isClosed()); // false
            System.out.println("   Каталог: " + conn.getCatalog());
            System.out.println("   Авто-коммит: " + conn.getAutoCommit());

            // Здесь можно выполнять запросы

        } catch (SQLException e) {
            System.err.println("❌ Ошибка подключения: " + e.getMessage());
        }

        // После try-with-resources соединение автоматически закрыто,
        // но мы не можем обратиться к conn, так как она вне области видимости
        System.out.println("Соединение автоматически закрыто (try-with-resources)");
    }
}
