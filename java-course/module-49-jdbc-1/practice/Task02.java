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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Task02 {
    public static void main(String[] args) throws Exception {
        String url  = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String pass = "";

        // TODO: откройте Connection и Statement через try-with-resources,
        //       создайте таблицу products и вставьте три товара,
        //       выводя число затронутых строк после каждого INSERT
    }
}
