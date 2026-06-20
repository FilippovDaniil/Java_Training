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
import java.sql.*;

public class Task04 {

    /**
     * Вставляет товар в таблицу products с использованием PreparedStatement
     *
     * @param conn     соединение с БД
     * @param name     название товара
     * @param price    цена товара (BigDecimal для точности)
     * @param quantity количество на складе
     * @return количество вставленных строк (должно быть 1 при успехе)
     * @throws SQLException если ошибка выполнения SQL
     */
    private static int insertProduct(Connection conn, String name, BigDecimal price, int quantity) throws SQLException {
        // Подготовленный SQL-запрос с параметрами (?, ?, ?)
        String sql = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";

        // Используем try-with-resources для автоматического закрытия PreparedStatement
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            // Устанавливаем параметры (индексы с 1!)
            ps.setString(1, name);          // первый параметр: name
            ps.setBigDecimal(2, price);     // второй параметр: price
            ps.setInt(3, quantity);         // третий параметр: quantity

            // Выполняем вставку и возвращаем количество измененных строк
            return ps.executeUpdate();
        }
    }

    /**
     * Альтернативная версия метода, которая возвращает сгенерированный ID
     */
    static int insertProductWithId(Connection conn, String name, BigDecimal price, int quantity) throws SQLException {
        String sql = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";

        // Указываем, что хотим получить сгенерированные ключи
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setBigDecimal(2, price);
            ps.setInt(3, quantity);

            int rows = ps.executeUpdate();

            // Получаем сгенерированный ID
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    System.out.println("   Сгенерированный ID: " + id);
                }
            }

            return rows;
        }
    }

    public static void main(String[] args) {
        String url  = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String pass = "";

        // Используем try-with-resources для автоматического закрытия Connection и Statement
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement()) {

            System.out.println("=== ПОДГОТОВКА БАЗЫ ДАННЫХ ===\n");

            // 1. Создаем таблицу products
            String createTableSQL = """
                CREATE TABLE IF NOT EXISTS products (
                    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
                    name     VARCHAR(100) NOT NULL,
                    price    DECIMAL(10,2) NOT NULL,
                    quantity INT NOT NULL DEFAULT 0
                )
            """;
            stmt.executeUpdate(createTableSQL);
            System.out.println("✅ Таблица products создана");
            System.out.println();

            // 2. Вставляем товары через PreparedStatement
            System.out.println("=== ВСТАВКА ТОВАРОВ ===\n");

            // Товар 1: Ноутбук
            int rows1 = insertProduct(conn, "Ноутбук", new BigDecimal("89999.00"), 5);
            System.out.println("   Вставлен: Ноутбук, строк изменено: " + rows1);

            // Товар 2: Мышь
            int rows2 = insertProduct(conn, "Мышь", new BigDecimal("1500.00"), 20);
            System.out.println("   Вставлен: Мышь, строк изменено: " + rows2);

            // Товар 3: Клавиатура
            int rows3 = insertProduct(conn, "Клавиатура", new BigDecimal("2999.00"), 15);
            System.out.println("   Вставлен: Клавиатура, строк изменено: " + rows3);

            // Товар 4: Монитор (дополнительный)
            int rows4 = insertProduct(conn, "Монитор", new BigDecimal("15999.00"), 8);
            System.out.println("   Вставлен: Монитор, строк изменено: " + rows4);

            // Товар 5: Наушники (дополнительный)
            int rows5 = insertProduct(conn, "Наушники", new BigDecimal("4999.00"), 10);
            System.out.println("   Вставлен: Наушники, строк изменено: " + rows5);

            // 3. Проверка количества записей
            System.out.println("\n=== ПРОВЕРКА ДАННЫХ ===");

            // Получаем общее количество товаров
            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM products")) {
                if (rs.next()) {
                    long count = rs.getLong("total");
                    System.out.println("   Итого в таблице: " + count);
                }
            }

            // Выводим все товары
            System.out.println("\n=== ВСЕ ТОВАРЫ ===");
            System.out.println("ID | Название | Цена | Количество");
            System.out.println("---|----------|-------|-----------");

            try (ResultSet rs = stmt.executeQuery("SELECT * FROM products ORDER BY id")) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String name = rs.getString("name");
                    BigDecimal price = rs.getBigDecimal("price");
                    int quantity = rs.getInt("quantity");
                    System.out.printf("%-2d | %-8s | %7.2f | %d%n",
                            id, name, price.doubleValue(), quantity);
                }
            }

        } catch (SQLException e) {
            System.err.println("\n❌ Ошибка SQL:");
            System.err.println("   Код: " + e.getErrorCode());
            System.err.println("   Сообщение: " + e.getMessage());
            System.err.println("   SQL состояние: " + e.getSQLState());
            e.printStackTrace();
        }

        System.out.println("\n✅ Соединение закрыто");
    }
}
