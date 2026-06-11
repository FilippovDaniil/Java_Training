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

public class Task01 {
    public static void main(String[] args) throws Exception {
        String url  = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String pass = "";

        // TODO: откройте Connection через DriverManager.getConnection(url, user, pass)
        //       выведите подтверждение и закройте соединение через try-with-resources

        Connection conn = null; // замените на правильный код

        System.out.println("Соединение закрыто: " + (conn == null || conn.isClosed()));
    }
}
