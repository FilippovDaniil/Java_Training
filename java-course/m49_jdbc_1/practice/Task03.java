package m49_jdbc_1.practice;

/**
 * Задача 03 — Модуль 49: SELECT и обход ResultSet
 *
 * ДЛЯ ЗАПУСКА: H2 (com.h2database:h2), URL jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
 * (для компиляции H2 не нужен — только java.sql.* из JDK)
 *
 * ЗАДАНИЕ:
 *   1) Откройте Connection и создайте таблицу products (как в Task02).
 *      Вставьте несколько товаров через Statement.
 *   2) Выполните Statement.executeQuery("SELECT * FROM products ORDER BY id").
 *   3) Пройдите ResultSet в цикле while (rs.next()) и для каждой строки выведите:
 *        "ID=<id>  Название=<name>  Цена=<price>  Кол-во=<quantity>"
 *   4) После цикла выведите: "Всего товаров выведено: <N>"
 *   5) Закройте все ресурсы.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (при трёх товарах):
 *   ID=1  Название=Ноутбук  Цена=89999.00  Кол-во=5
 *   ID=2  Название=Мышь     Цена=1299.50   Кол-во=100
 *   ID=3  Название=Монитор  Цена=34500.00  Кол-во=12
 *   Всего товаров выведено: 3
 *
 * ПОДСКАЗКА:
 *   rs.next()         — переходит к следующей строке, возвращает false когда строк нет.
 *   rs.getLong("id")  — получить значение столбца по имени.
 *   rs.getString("name"), rs.getBigDecimal("price"), rs.getInt("quantity").
 */
import java.sql.*;

public class Task03 {
    public static void main(String[] args) throws Exception {
        String url  = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String pass = "";

        // TODO: создайте таблицу products, вставьте несколько товаров,
        //       затем выполните SELECT и распечатайте каждую строку ResultSet,
        //       в конце выведите общее число строк
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
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                System.out.println("Id товара: " + id + " Название: " + name + " По цене: " + price + " В количестве: " + quantity);
            }



        } catch (SQLException e) {
            System.err.println("❌ Ошибка подключения: " + e.getMessage());
        }

        // После try-with-resources соединение автоматически закрыто,
        // но мы не можем обратиться к conn, так как она вне области видимости
        System.out.println("Соединение автоматически закрыто (try-with-resources)");
    }
}
