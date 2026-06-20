package m49_jdbc_1.practice;

/**
 * Задача 02 — Модуль 49: CREATE TABLE и INSERT через Statement
 *
 * ДЛЯ ЗАПУСКА: H2 (com.h2database:h2), URL jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
 * (для компиляции H2 не нужен — только java.sql.* из JDK)
 *
 * ЗАДАНИЕ:
 *   1) Откройте Connection к H2 in-memory БД.
 *   2) Через Statement.executeUpdate() создайте таблицу:
 *        CREATE TABLE products (
 *            id       BIGINT AUTO_INCREMENT PRIMARY KEY,
 *            name     VARCHAR(100) NOT NULL,
 *            price    DECIMAL(10,2) NOT NULL,
 *            quantity INT NOT NULL DEFAULT 0
 *        )
 *   3) Вставьте через Statement.executeUpdate() минимум три товара, например:
 *        ('Ноутбук', 89999.00, 5)
 *        ('Мышь',     1299.50, 100)
 *        ('Монитор', 34500.00, 12)
 *   4) После каждой вставки выводите число затронутых строк:
 *        "Вставлено строк: 1"
 *   5) Все ресурсы закрывайте через try-with-resources.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Таблица создана
 *   Вставлено строк: 1
 *   Вставлено строк: 1
 *   Вставлено строк: 1
 *
 * ПОДСКАЗКА:
 *   stmt.executeUpdate(sql) — для DDL и DML; возвращает int.
 *   Statement не принимает параметры — значения вставляйте прямо в строку SQL.
 */
import java.sql.*;

public class Task02 {
    public static void main(String[] args) throws Exception {
        String url  = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String pass = "";

        // TODO: откройте Connection и Statement через try-with-resources,
        //       создайте таблицу products и вставьте три товара,
        //       выводя число затронутых строк после каждого INSERT
        // Способ 2: try-with-resources (рекомендуемый для Java 7+)
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement  stmt = conn.createStatement()) {
            System.out.println("Соединение установлено: " + conn.toString());
            System.out.println("   isClosed(): " + conn.isClosed()); // false
            System.out.println("   Каталог: " + conn.getCatalog());
            System.out.println("   Авто-коммит: " + conn.getAutoCommit());

            // Здесь можно выполнять запросы
            stmt.executeUpdate("""
                         CREATE TABLE products (
                             id       BIGINT AUTO_INCREMENT PRIMARY KEY,
                             name     VARCHAR(100) NOT NULL,
                             price    DECIMAL(10,2) NOT NULL,
                             quantity INT NOT NULL DEFAULT 0
                         );
                """);

            System.out.println("Таблица создана");

            int rows = stmt.executeUpdate(
                    "INSERT INTO products(name,price,quantity) VALUES('Ноутбук', 89999.00, 5)"
            );
            System.out.println("Вставлено строк: " + rows);
            int rows2 = stmt.executeUpdate(
                    "INSERT INTO products(name,price,quantity) VALUES('Планшет', 55555.00, 3)"
            );
            System.out.println("Вставлено строк: " + rows2);
            int rows3 = stmt.executeUpdate(
                    "INSERT INTO products(name,price,quantity) VALUES('Айфон', 666666.00, 2)"
            );
            System.out.println("Вставлено строк: " + rows3);

            // DQL — SELECT
            ResultSet rs = stmt.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                long   id   = rs.getLong("id");
                String name = rs.getString("name");
                long price = rs.getLong("price");
                int quantity = rs.getInt("quantity");
                System.out.println(id + " " + name + " По цене: " + price + " В количестве: " + quantity);
            }



        } catch (SQLException e) {
            System.err.println("❌ Ошибка подключения: " + e.getMessage());
        }

        // После try-with-resources соединение автоматически закрыто,
        // но мы не можем обратиться к conn, так как она вне области видимости
        System.out.println("Соединение автоматически закрыто (try-with-resources)");
    }
}
