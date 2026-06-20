package m49_jdbc_1.practice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDao {
    private final Connection conn;

    ProductDao(Connection conn) {
        this.conn = conn;
    }

    /** Создаёт таблицу products если её ещё нет */
    public void createTable() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS products (
                    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
                    name     VARCHAR(100) NOT NULL,
                    price    DECIMAL(10,2) NOT NULL,
                    quantity INT NOT NULL DEFAULT 0
                )
            """;
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("✅ Таблица products создана (или уже существует)");
        }
    }

    /** Вставляет товар; возвращает id, присвоенный базой */
    public long add(Product p) throws SQLException {
        String sql = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.name());
            ps.setBigDecimal(2, p.price());
            ps.setInt(3, p.quantity());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Вставка товара не удалась, ни одна строка не затронута");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    return id;
                } else {
                    throw new SQLException("Вставка товара не удалась, ID не получен");
                }
            }
        }
    }

    /** Возвращает все товары, упорядоченные по id */
    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY id";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getInt("quantity")
                );
                products.add(product);
            }
        }
        return products;
    }

    /** Ищет товар по id */
    Optional<Product> findById(long id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Product product = new Product(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getBigDecimal("price"),
                            rs.getInt("quantity")
                    );
                    return Optional.of(product);
                }
            }
        }
        return Optional.empty();
    }

    /** Обновляет имя, цену и количество товара; возвращает число затронутых строк */
    int update(Product p) throws SQLException {
        String sql = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.name());
            ps.setBigDecimal(2, p.price());
            ps.setInt(3, p.quantity());
            ps.setLong(4, p.id());

            return ps.executeUpdate();
        }
    }

    /** Удаляет товар по id; возвращает число затронутых строк */
    int delete(long id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate();
        }
    }

    /** Проверяет существование товара по id */
    boolean exists(long id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM products WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    /** Возвращает общее количество товаров */
    long count() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM products";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getLong("total");
            }
        }
        return 0;
    }
}
