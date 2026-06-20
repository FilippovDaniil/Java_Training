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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Task06 {

    public static void main(String[] args) {
        String url  = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String pass = "";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement()) {

            System.out.println("=== ПОДГОТОВКА БАЗЫ ДАННЫХ ===\n");

            // 1. Создание таблицы
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS products (
                    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
                    name     VARCHAR(100) NOT NULL,
                    price    DECIMAL(10,2) NOT NULL,
                    quantity INT NOT NULL DEFAULT 0
                )
            """);
            System.out.println("✅ Таблица products создана");

            // 2. Вставка товаров
            System.out.println("\n--- ВСТАВКА ТОВАРОВ ---");

            String[] names = {"Ноутбук", "Мышь", "Монитор", "Клавиатура"};
            double[] prices = {89999.00, 1299.50, 34500.00, 2999.00};
            int[] quantities = {5, 100, 12, 25};

            String insertSQL = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertSQL)) {
                for (int i = 0; i < names.length; i++) {
                    ps.setString(1, names[i]);
                    ps.setBigDecimal(2, BigDecimal.valueOf(prices[i]));
                    ps.setInt(3, quantities[i]);
                    int rows = ps.executeUpdate();
                    System.out.printf("   Вставлен: %s, строк изменено: %d%n", names[i], rows);
                }
            }

            // ============================================
            // ЧАСТЬ A: МЕТАДАННЫЕ СТОЛБЦОВ
            // ============================================
            System.out.println("\n" + "=" .repeat(60));
            System.out.println("ЧАСТЬ A: МЕТАДАННЫЕ СТОЛБЦОВ");
            System.out.println("=" .repeat(60));

            try (ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {
                // Получаем метаданные
                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();

                System.out.println("\nКоличество столбцов: " + columnCount);
                System.out.println("\nДетальная информация о столбцах:");
                System.out.println("№ | Имя столбца | Тип SQL | Класс Java | Макс. длина");
                System.out.println("---|------------|---------|------------|-----------");

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = meta.getColumnName(i);
                    String columnTypeName = meta.getColumnTypeName(i);
                    String columnClassName = meta.getColumnClassName(i);
                    int columnDisplaySize = meta.getColumnDisplaySize(i);

                    System.out.printf("%-2d | %-10s | %-7s | %-10s | %d%n",
                            i, columnName, columnTypeName,
                            columnClassName.substring(columnClassName.lastIndexOf('.') + 1),
                            columnDisplaySize
                    );

                    // Дополнительная информация о столбце
                    System.out.printf("   - является автоинкрементом: %b%n", meta.isAutoIncrement(i));
                    System.out.printf("   - допускает NULL: %b%n", meta.isNullable(i) == ResultSetMetaData.columnNullable);
                    System.out.printf("   - точность: %d, масштаб: %d%n",
                            meta.getPrecision(i), meta.getScale(i));
                }

                // Краткий вывод для задания
                System.out.println("\n--- КРАТКИЙ ВЫВОД (как в задании) ---");
                for (int i = 1; i <= columnCount; i++) {
                    System.out.printf("Столбец %d: имя=%s  тип=%s%n",
                            i, meta.getColumnName(i), meta.getColumnTypeName(i));
                }
            }

            // ============================================
            // ЧАСТЬ B: МАППИНГ В RECORD
            // ============================================
            System.out.println("\n" + "=" .repeat(60));
            System.out.println("ЧАСТЬ B: МАППИНГ В RECORD");
            System.out.println("=" .repeat(60));

            // Маппинг результатов в список Product
            List<Product> products = new ArrayList<>();

            try (ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String name = rs.getString("name");
                    BigDecimal price = rs.getBigDecimal("price");
                    int quantity = rs.getInt("quantity");

                    // Создаем объект Product и добавляем в список
                    Product product = new Product(id, name, price, quantity);
                    products.add(product);
                }
            }

            // Вывод списка
            System.out.println("\nСписок товаров (List<Product>):");
            System.out.println("Всего: " + products.size() + " товаров\n");

            for (Product product : products) {
                System.out.println("   " + product);
            }

            // ============================================
            // ДОПОЛНИТЕЛЬНАЯ ИНФОРМАЦИЯ
            // ============================================
            System.out.println("\n" + "=" .repeat(60));
            System.out.println("ДОПОЛНИТЕЛЬНАЯ ИНФОРМАЦИЯ");
            System.out.println("=" .repeat(60));

            // Использование метаданных для динамической обработки
            System.out.println("\nДинамическое построение строки из ResultSet:");
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM products LIMIT 1")) {
                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();

                if (rs.next()) {
                    StringBuilder row = new StringBuilder("Строка: ");
                    for (int i = 1; i <= columnCount; i++) {
                        if (i > 1) row.append(", ");
                        row.append(meta.getColumnName(i))
                                .append("=")
                                .append(rs.getString(i));
                    }
                    System.out.println("   " + row);
                }
            }

            // Суммарная статистика через агрегатные функции
            System.out.println("\nСтатистика:");
            try (ResultSet rs = stmt.executeQuery("""
                SELECT 
                    COUNT(*) AS total_products,
                    ROUND(AVG(price), 2) AS avg_price,
                    MIN(price) AS min_price,
                    MAX(price) AS max_price,
                    SUM(quantity) AS total_quantity
                FROM products
            """)) {
                if (rs.next()) {
                    System.out.println("   Всего товаров: " + rs.getLong("total_products"));
                    System.out.println("   Средняя цена: " + rs.getBigDecimal("avg_price"));
                    System.out.println("   Минимальная цена: " + rs.getBigDecimal("min_price"));
                    System.out.println("   Максимальная цена: " + rs.getBigDecimal("max_price"));
                    System.out.println("   Общее количество: " + rs.getInt("total_quantity"));
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
