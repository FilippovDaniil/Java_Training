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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Task03 {
    public static void main(String[] args) throws Exception {
        String url  = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String pass = "";

        // TODO: создайте таблицу products, вставьте несколько товаров,
        //       затем выполните SELECT и распечатайте каждую строку ResultSet,
        //       в конце выведите общее число строк
    }
}
