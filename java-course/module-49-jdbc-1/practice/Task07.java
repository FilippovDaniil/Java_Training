/**
 * Задача 07 — Модуль 49: Мини-проект — консольный CRUD-каталог товаров
 *
 * ДЛЯ ЗАПУСКА: H2 (com.h2database:h2), URL jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
 * (для компиляции H2 не нужен — только java.sql.* из JDK)
 *
 * ЗАДАНИЕ:
 *   Реализуйте все методы класса ProductDao, помеченные // TODO.
 *   Класс должен предоставлять полный CRUD через JDBC.
 *
 *   Методы для реализации:
 *     - createTable()           — CREATE TABLE IF NOT EXISTS products (...)
 *     - add(Product p)          — INSERT; вернуть сгенерированный id
 *     - findAll()               — SELECT * ORDER BY id; вернуть List<Product>
 *     - findById(long id)       — SELECT WHERE id=?; вернуть Optional<Product>
 *     - update(Product p)       — UPDATE name, price, quantity WHERE id=?;
 *                                 вернуть число затронутых строк
 *     - delete(long id)         — DELETE WHERE id=?; вернуть число затронутых строк
 *
 *   В main продемонстрируйте все операции:
 *     1) Создать таблицу.
 *     2) Добавить 3 товара через add(), вывести назначенные id.
 *     3) Вывести findAll().
 *     4) Найти товар по id через findById(), вывести или "не найден".
 *     5) Обновить один товар (новое имя/цена), вывести "Обновлено строк: <N>".
 *     6) Удалить один товар, вывести "Удалено строк: <N>".
 *     7) Вывести финальный findAll().
 *
 * ПОДСКАЗКИ:
 *   - Для получения сгенерированного id используйте:
 *       PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
 *       ps.executeUpdate();
 *       ResultSet keys = ps.getGeneratedKeys();
 *       if (keys.next()) { long id = keys.getLong(1); }
 *
 *   - findById: верните Optional.of(product) или Optional.empty() если строк нет.
 *
 *   - Connection передавайте в конструктор ProductDao, не создавайте внутри методов.
 *
 *   - Все SQL-параметры — через PreparedStatement (никакой конкатенации строк!).
 */
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Task07 {

    // Готовый record — не изменять
    record Product(long id, String name, BigDecimal price, int quantity) {
        // Конструктор для создания нового (id=0, заполняется после INSERT)
        Product(String name, BigDecimal price, int quantity) {
            this(0, name, price, quantity);
        }

        @Override
        public String toString() {
            return String.format("Product[id=%d, name='%s', price=%s, qty=%d]",
                    id, name, price, quantity);
        }
    }

    // Готовый каркас DAO — реализовать все TODO
    static class ProductDao {
        private final Connection conn;

        ProductDao(Connection conn) {
            this.conn = conn;
        }

        /** Создаёт таблицу products если её ещё нет */
        void createTable() throws Exception {
            // TODO: выполните CREATE TABLE IF NOT EXISTS products (
            //         id       BIGINT AUTO_INCREMENT PRIMARY KEY,
            //         name     VARCHAR(100) NOT NULL,
            //         price    DECIMAL(10,2) NOT NULL,
            //         quantity INT NOT NULL DEFAULT 0
            //       ) через Statement.executeUpdate()
        }

        /** Вставляет товар; возвращает id, присвоенный базой */
        long add(Product p) throws Exception {
            // TODO: подготовьте PreparedStatement с RETURN_GENERATED_KEYS,
            //       установите параметры, выполните, извлеките и верните сгенерированный id
            return -1;
        }

        /** Возвращает все товары, упорядоченные по id */
        List<Product> findAll() throws Exception {
            // TODO: выполните SELECT * FROM products ORDER BY id,
            //       смаппьте каждую строку ResultSet в Product, верните список
            return new ArrayList<>();
        }

        /** Ищет товар по id */
        Optional<Product> findById(long id) throws Exception {
            // TODO: выполните SELECT ... WHERE id=? через PreparedStatement,
            //       если строка найдена — верните Optional.of(product),
            //       иначе — Optional.empty()
            return Optional.empty();
        }

        /** Обновляет имя, цену и количество товара; возвращает число затронутых строк */
        int update(Product p) throws Exception {
            // TODO: UPDATE products SET name=?, price=?, quantity=? WHERE id=?
            return 0;
        }

        /** Удаляет товар по id; возвращает число затронутых строк */
        int delete(long id) throws Exception {
            // TODO: DELETE FROM products WHERE id=?
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        String url  = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String pass = "";

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            ProductDao dao = new ProductDao(conn);

            // TODO шаг 1: dao.createTable()

            // TODO шаг 2: добавьте 3 товара через dao.add(), выведите id каждого

            // TODO шаг 3: dao.findAll() и вывести все товары

            // TODO шаг 4: dao.findById(id) — найти один товар

            // TODO шаг 5: dao.update(новый Product с изменёнными данными)

            // TODO шаг 6: dao.delete(id одного товара)

            // TODO шаг 7: dao.findAll() финальный список
        }
    }
}
