package m49_jdbc_1.practice;

/**
 * Задача 04 — Модуль 49: INSERT через PreparedStatement с параметрами
 *
 * ДЛЯ ЗАПУСКА: H2 (com.h2database:h2), URL jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
 * (для компиляции H2 не нужен — только java.sql.* из JDK)
 *
 * ЗАДАНИЕ:
 *   В этом упражнении нужно вставлять товары безопасно — через PreparedStatement,
 *   а не конкатенацию строк.
 *
 *   1) Создайте таблицу products (как в Task02).
 *   2) Напишите метод insertProduct(Connection conn, String name,
 *      BigDecimal price, int quantity), который:
 *        - готовит PreparedStatement с SQL:
 *            INSERT INTO products(name, price, quantity) VALUES(?, ?, ?)
 *        - устанавливает параметры через ps.setString(1,...),
 *          ps.setBigDecimal(2,...), ps.setInt(3,...);
 *        - выполняет вставку и возвращает число затронутых строк.
 *   3) В main вызовите insertProduct для минимум трёх товаров с разными ценами.
 *   4) Для каждого вызова выведите: "Вставлен: <name>, строк изменено: <rows>"
 *   5) Выполните SELECT COUNT(*) FROM products и выведите итог:
 *        "Итого в таблице: <N>"
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Вставлен: Ноутбук, строк изменено: 1
 *   Вставлен: Мышь, строк изменено: 1
 *   Вставлен: Клавиатура, строк изменено: 1
 *   Итого в таблице: 3
 *
 * ПОДСКАЗКА:
 *   PreparedStatement ps = conn.prepareStatement("INSERT INTO ... VALUES(?,?,?)");
 *   ps.setString(1, name);          // индексы с 1!
 *   ps.setBigDecimal(2, price);
 *   ps.setInt(3, quantity);
 *   int rows = ps.executeUpdate();
 *
 *   Для COUNT: Statement.executeQuery("SELECT COUNT(*) FROM products")
 *   ResultSet rs = ...; rs.next(); long count = rs.getLong(1);
 */
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Task04 {

    // TODO: реализуйте метод insertProduct
    static int insertProduct(Connection conn, String name,
                             BigDecimal price, int quantity) throws Exception {
        // TODO
        return 0;
    }

    public static void main(String[] args) throws Exception {
        String url  = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String pass = "";

        // TODO: создайте таблицу, вызовите insertProduct для 3 товаров,
        //       выведите результат каждой вставки и общий COUNT
    }
}
