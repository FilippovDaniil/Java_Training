package m49_jdbc_1.practice;

/**
 * Задача 06 — Модуль 49: ResultSetMetaData и маппинг в record
 *
 * ДЛЯ ЗАПУСКА: H2 (com.h2database:h2), URL jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
 * (для компиляции H2 не нужен — только java.sql.* из JDK)
 *
 * ЗАДАНИЕ:
 *   Часть A — метаданные столбцов:
 *     1) Создайте таблицу products и вставьте несколько товаров.
 *     2) Выполните SELECT * FROM products.
 *     3) Получите ResultSetMetaData через rs.getMetaData().
 *     4) В цикле по числу столбцов (getColumnCount()) выведите для каждого:
 *          "Столбец <i>: имя=<getColumnName(i)>  тип=<getColumnTypeName(i)>"
 *
 *   Часть B — маппинг строк в record:
 *     5) Используя готовый record Product (объявлен ниже), пройдите ResultSet и
 *        создайте List<Product>. Добавьте каждый объект в список через list.add(...).
 *     6) Выведите список через for-each:
 *          "Product{id=<id>, name='<name>', price=<price>, quantity=<quantity>}"
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример, 4 столбца + 3 товара):
 *   Столбец 1: имя=ID           тип=BIGINT
 *   Столбец 2: имя=NAME         тип=VARCHAR
 *   Столбец 3: имя=PRICE        тип=DECIMAL
 *   Столбец 4: имя=QUANTITY     тип=INTEGER
 *   Product{id=1, name='Ноутбук', price=89999.00, quantity=5}
 *   Product{id=2, name='Мышь', price=1299.50, quantity=100}
 *   Product{id=3, name='Монитор', price=34500.00, quantity=12}
 *
 * ПОДСКАЗКА:
 *   ResultSetMetaData meta = rs.getMetaData();
 *   int cols = meta.getColumnCount();        // число столбцов
 *   meta.getColumnName(i)                    // имя столбца (i начинается с 1)
 *   meta.getColumnTypeName(i)                // строковое имя типа SQL
 *
 *   rs.getLong("id"), rs.getString("name"),
 *   rs.getBigDecimal("price"), rs.getInt("quantity")
 */
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Task06 {

    // Готовый record — использовать в маппинге (не изменять)
    record Product(long id, String name, BigDecimal price, int quantity) {
        @Override
        public String toString() {
            return "Product{id=" + id +
                   ", name='" + name + "'" +
                   ", price=" + price +
                   ", quantity=" + quantity + "}";
        }
    }

    public static void main(String[] args) throws Exception {
        String url  = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String pass = "";

        // TODO: часть A — создайте таблицу, вставьте товары,
        //       выполните SELECT, распечатайте метаданные столбцов через ResultSetMetaData

        // TODO: часть B — повторите SELECT, пройдите ResultSet и соберите List<Product>,
        //       затем выведите каждый элемент списка
    }
}
