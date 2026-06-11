package m49_jdbc_1.practice;

/**
 * Задача 05 — Модуль 49: UPDATE и DELETE через PreparedStatement
 *
 * ДЛЯ ЗАПУСКА: H2 (com.h2database:h2), URL jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
 * (для компиляции H2 не нужен — только java.sql.* из JDK)
 *
 * ЗАДАНИЕ:
 *   1) Создайте таблицу products и вставьте 4 товара (используйте Statement или
 *      PreparedStatement на ваш выбор).
 *   2) Напишите метод updatePrice(Connection conn, long id, BigDecimal newPrice),
 *      который обновляет цену товара по id через PreparedStatement:
 *        UPDATE products SET price = ? WHERE id = ?
 *      Возвращает число затронутых строк.
 *   3) Напишите метод deleteProduct(Connection conn, long id),
 *      который удаляет товар по id:
 *        DELETE FROM products WHERE id = ?
 *      Возвращает число затронутых строк.
 *   4) В main:
 *        - обновите цену у товара с id=2 до 1500.00; выведите: "Обновлено строк: <N>"
 *        - удалите товар с id=4; выведите: "Удалено строк: <N>"
 *        - попробуйте удалить несуществующий id=99; выведите: "Удалено строк: 0"
 *        - выведите финальный SELECT * (как в Task03)
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Обновлено строк: 1
 *   Удалено строк: 1
 *   Удалено строк: 0
 *   ID=1  Название=Ноутбук   Цена=89999.00  Кол-во=5
 *   ID=2  Название=Мышь      Цена=1500.00   Кол-во=100
 *   ID=3  Название=Монитор   Цена=34500.00  Кол-во=12
 *
 * ПОДСКАЗКА:
 *   executeUpdate() возвращает int — число строк, которые реально изменились.
 *   Если WHERE не нашёл строку → вернёт 0 (исключение не бросает).
 */
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Task05 {

    // TODO: реализуйте метод updatePrice
    static int updatePrice(Connection conn, long id, BigDecimal newPrice) throws Exception {
        // TODO
        return 0;
    }

    // TODO: реализуйте метод deleteProduct
    static int deleteProduct(Connection conn, long id) throws Exception {
        // TODO
        return 0;
    }

    public static void main(String[] args) throws Exception {
        String url  = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String pass = "";

        // TODO: создайте таблицу, вставьте товары, вызовите updatePrice и deleteProduct,
        //       выводите число затронутых строк, завершите финальным SELECT
    }
}
