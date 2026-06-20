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
import java.sql.*;

public class Task05 {

    // ===== БАЗОВЫЕ ОПЕРАЦИИ =====

    static int updatePrice(Connection conn, long id, BigDecimal newPrice) throws SQLException {
        String sql = "UPDATE products SET price = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, newPrice);
            ps.setLong(2, id);
            return ps.executeUpdate();
        }
    }

    static int deleteProduct(Connection conn, long id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate();
        }
    }

    // ===== ДОПОЛНИТЕЛЬНЫЕ ОПЕРАЦИИ =====

    /**
     * Обновление нескольких полей одновременно
     */
    static int updateProduct(Connection conn, long id, String newName, BigDecimal newPrice, int newQuantity)
            throws SQLException {
        String sql = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setBigDecimal(2, newPrice);
            ps.setInt(3, newQuantity);
            ps.setLong(4, id);
            return ps.executeUpdate();
        }
    }

    /**
     * Массовое обновление цены по проценту
     */
    static int updatePriceByPercent(Connection conn, double percent) throws SQLException {
        String sql = "UPDATE products SET price = price * ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, BigDecimal.valueOf(1 + percent / 100));
            return ps.executeUpdate();
        }
    }

    /**
     * Массовое удаление товаров с количеством меньше заданного
     */
    static int deleteProductsWithLowQuantity(Connection conn, int minQuantity) throws SQLException {
        String sql = "DELETE FROM products WHERE quantity < ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, minQuantity);
            return ps.executeUpdate();
        }
    }

    /**
     * Обновление с условием
     */
    static int updatePriceWhereQuantityLessThan(Connection conn, int quantity, BigDecimal newPrice)
            throws SQLException {
        String sql = "UPDATE products SET price = ? WHERE quantity < ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, newPrice);
            ps.setInt(2, quantity);
            return ps.executeUpdate();
        }
    }

    public static void main(String[] args) {
        String url  = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String pass = "";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement()) {

            // Создание таблицы
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS products (
                    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
                    name     VARCHAR(100) NOT NULL,
                    price    DECIMAL(10,2) NOT NULL,
                    quantity INT NOT NULL DEFAULT 0
                )
            """);
            System.out.println("✅ Таблица products создана");

            // Вставка товаров
            System.out.println("\n--- ВСТАВКА ТОВАРОВ ---");
            String[][] products = {
                    {"Ноутбук", "89999.00", "5"},
                    {"Мышь", "1299.00", "100"},
                    {"Монитор", "34500.00", "12"},
                    {"Клавиатура", "2999.00", "25"},
                    {"Наушники", "4999.00", "3"},
                    {"Колонки", "2999.00", "8"}
            };

            String insertSQL = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertSQL)) {
                for (String[] p : products) {
                    ps.setString(1, p[0]);
                    ps.setBigDecimal(2, new BigDecimal(p[1]));
                    ps.setInt(3, Integer.parseInt(p[2]));
                    ps.executeUpdate();
                }
            }
            System.out.println("   Вставлено " + products.length + " товаров");

            // Начальные данные
            System.out.println("\n--- НАЧАЛЬНЫЕ ДАННЫЕ ---");
            printProducts(stmt);

            // 1. Обновление цены конкретного товара
            System.out.println("\n--- 1. ОБНОВЛЕНИЕ ЦЕНЫ (id=2) ---");
            int rows = updatePrice(conn, 2, new BigDecimal("1500.00"));
            System.out.println("   Обновлено строк: " + rows);

            // 2. Полное обновление товара (id=4)
            System.out.println("\n--- 2. ПОЛНОЕ ОБНОВЛЕНИЕ (id=4) ---");
            rows = updateProduct(conn, 4, "Механическая клавиатура", new BigDecimal("5000.00"), 30);
            System.out.println("   Обновлено строк: " + rows);

            // 3. Обновление цены по проценту
            System.out.println("\n--- 3. ПОВЫШЕНИЕ ЦЕНЫ НА 10% ---");
            rows = updatePriceByPercent(conn, 10.0);
            System.out.println("   Обновлено строк: " + rows);

            // 4. Удаление товара с малым количеством (< 5)
            System.out.println("\n--- 4. УДАЛЕНИЕ ТОВАРОВ С quantity < 5 ---");
            rows = deleteProductsWithLowQuantity(conn, 5);
            System.out.println("   Удалено строк: " + rows);

            // 5. Попытка удалить несуществующий товар
            System.out.println("\n--- 5. УДАЛЕНИЕ НЕСУЩЕСТВУЮЩЕГО ТОВАРА (id=99) ---");
            rows = deleteProduct(conn, 99);
            System.out.println("   Удалено строк: " + rows);

            // 6. Обновление цены для товаров с количеством < 10
            System.out.println("\n--- 6. ОБНОВЛЕНИЕ ЦЕНЫ ДЛЯ quantity < 10 ---");
            rows = updatePriceWhereQuantityLessThan(conn, 10, new BigDecimal("999.00"));
            System.out.println("   Обновлено строк: " + rows);

            // Финальные данные
            System.out.println("\n=== ФИНАЛЬНЫЕ ДАННЫЕ ===");
            printProducts(stmt);

            // Статистика
            System.out.println("\n--- СТАТИСТИКА ---");
            try (ResultSet rs = stmt.executeQuery("""
                SELECT 
                    COUNT(*) AS total,
                    ROUND(AVG(price), 2) AS avg_price,
                    MIN(price) AS min_price,
                    MAX(price) AS max_price,
                    SUM(quantity) AS total_quantity
                FROM products
            """)) {
                if (rs.next()) {
                    System.out.println("   Всего товаров: " + rs.getInt("total"));
                    System.out.println("   Средняя цена: " + rs.getBigDecimal("avg_price"));
                    System.out.println("   Минимальная цена: " + rs.getBigDecimal("min_price"));
                    System.out.println("   Максимальная цена: " + rs.getBigDecimal("max_price"));
                    System.out.println("   Всего единиц: " + rs.getInt("total_quantity"));
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Соединение закрыто");
    }

    private static void printProducts(Statement stmt) throws SQLException {
        try (ResultSet rs = stmt.executeQuery("SELECT * FROM products ORDER BY id")) {
            System.out.println("ID | Название | Цена | Количество");
            System.out.println("---|----------|-------|-----------");
            while (rs.next()) {
                System.out.printf("%-2d | %-10s | %7.2f | %d%n",
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"));
            }
        }
    }
}
