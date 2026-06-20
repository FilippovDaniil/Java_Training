package m49_jdbc_1.practice;

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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Task07 {

    public static void main(String[] args) throws Exception {
        String url  = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String pass = "";

        System.out.println("=== КОНСОЛЬНЫЙ CRUD-КАТАЛОГ ТОВАРОВ ===\n");

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            ProductDao dao = new ProductDao(conn);

            // ===== ШАГ 1: Создание таблицы =====
            System.out.println("--- ШАГ 1: Создание таблицы ---");
            dao.createTable();

            // ===== ШАГ 2: Добавление товаров =====
            System.out.println("\n--- ШАГ 2: Добавление товаров ---");

            Product[] products = {
                    new Product("Ноутбук", new BigDecimal("89999.00"), 5),
                    new Product("Мышь", new BigDecimal("1299.50"), 100),
                    new Product("Монитор", new BigDecimal("34500.00"), 12)
            };

            long[] ids = new long[products.length];
            for (int i = 0; i < products.length; i++) {
                long id = dao.add(products[i]);
                ids[i] = id;
                System.out.printf("   Добавлен: %s -> ID: %d%n", products[i].name(), id);
            }

            // ===== ШАГ 3: Вывод всех товаров =====
            System.out.println("\n--- ШАГ 3: Все товары (findAll) ---");
            List<Product> allProducts = dao.findAll();
            if (allProducts.isEmpty()) {
                System.out.println("   Товаров нет");
            } else {
                for (Product p : allProducts) {
                    System.out.println("   " + p);
                }
                System.out.printf("   Всего: %d товаров%n", allProducts.size());
            }

            // ===== ШАГ 4: Поиск по ID =====
            System.out.println("\n--- ШАГ 4: Поиск по ID ---");

            // Поиск существующего товара (id = ids[0])
            long searchId = ids[0];
            Optional<Product> found = dao.findById(searchId);
            if (found.isPresent()) {
                System.out.printf("   Товар с ID %d найден: %s%n", searchId, found.get());
            } else {
                System.out.printf("   Товар с ID %d не найден%n", searchId);
            }

            // Поиск несуществующего товара
            long nonExistentId = 999;
            Optional<Product> notFound = dao.findById(nonExistentId);
            if (notFound.isPresent()) {
                System.out.printf("   Товар с ID %d найден: %s%n", nonExistentId, notFound.get());
            } else {
                System.out.printf("   Товар с ID %d не найден%n", nonExistentId);
            }

            // ===== ШАГ 5: Обновление товара =====
            System.out.println("\n--- ШАГ 5: Обновление товара ---");

            long updateId = ids[1]; // ID второго товара (Мышь)
            Product updatedProduct = new Product(
                    updateId,
                    "Беспроводная мышь",  // новое имя
                    new BigDecimal("1599.00"), // новая цена
                    150 // новое количество
            );

            int updateRows = dao.update(updatedProduct);
            System.out.printf("   Обновление ID %d: изменено строк: %d%n", updateId, updateRows);

            // Проверяем обновленный товар
            Optional<Product> afterUpdate = dao.findById(updateId);
            if (afterUpdate.isPresent()) {
                System.out.println("   После обновления: " + afterUpdate.get());
            }

            // ===== ШАГ 6: Удаление товара =====
            System.out.println("\n--- ШАГ 6: Удаление товара ---");

            long deleteId = ids[2]; // ID третьего товара (Монитор)
            int deleteRows = dao.delete(deleteId);
            System.out.printf("   Удаление ID %d: удалено строк: %d%n", deleteId, deleteRows);

            // Проверяем, что товар удален
            Optional<Product> afterDelete = dao.findById(deleteId);
            if (afterDelete.isEmpty()) {
                System.out.printf("   Товар с ID %d успешно удален%n", deleteId);
            }

            // ===== ШАГ 7: Финальный список =====
            System.out.println("\n--- ШАГ 7: Финальный список товаров ---");
            List<Product> finalProducts = dao.findAll();
            if (finalProducts.isEmpty()) {
                System.out.println("   Товаров нет");
            } else {
                for (Product p : finalProducts) {
                    System.out.println("   " + p);
                }
                System.out.printf("   Всего: %d товаров%n", finalProducts.size());
            }

            // ===== ДОПОЛНИТЕЛЬНО: Статистика =====
            System.out.println("\n--- СТАТИСТИКА ---");
            System.out.println("   Всего товаров в БД: " + dao.count());

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
