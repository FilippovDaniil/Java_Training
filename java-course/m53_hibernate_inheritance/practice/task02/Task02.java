package m53_hibernate_inheritance.practice.task02;

/**
 * Задача 02 — Модуль 53: JOINED — иерархия с отдельными таблицами на подкласс
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md).
 * Файл не компилируется без этих зависимостей — это ожидаемо.
 *
 * ЗАДАНИЕ:
 *   Перепишите иерархию Product → Book / Electronics / Clothing стратегией JOINED:
 *     1) Product — @Entity @Table("products") с полями id, name, price.
 *     2) Book — @Entity @Table("books") с author, isbn.
 *        PK таблицы books одновременно FK на products.id.
 *     3) Electronics — @Entity @Table("electronics") с brand, warrantyMonths.
 *     4) Clothing — @Entity @Table("clothing") с size, material.
 *     5) В main() создайте H2 SessionFactory, сохраните по одному объекту каждого типа,
 *        проверьте через Native SQL (session.createNativeQuery), что:
 *        - в таблице products 3 строки;
 *        - в таблице books 1 строка.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Строк в products: 3
 *   Строк в books: 1
 *
 * ПОДСКАЗКА:
 *   @Inheritance(strategy = InheritanceType.JOINED) — только на корневом классе.
 *   Hibernate при INSERT в Book вставляет 2 строки:
 *     - одну в products (общие поля)
 *     - одну в books (специфичные поля)
 *   При SELECT Book делается JOIN: SELECT * FROM products p JOIN books b ON p.id = b.id.
 *   Дискриминатор при JOINED опционален, но можно добавить для ясности.
 *
 *   Для нативного запроса:
 *     Number count = (Number) session
 *         .createNativeQuery("SELECT COUNT(*) FROM products")
 *         .getSingleResult();
 */

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;

public class Task02 {

    public static void main(String[] args) {
        // Создаем SessionFactory
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:shop;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.format_sql", "true");
        config.addAnnotatedClass(Product2.class);
        config.addAnnotatedClass(Book2.class);
        config.addAnnotatedClass(Electronics2.class);
        config.addAnnotatedClass(Clothing.class);

        try (SessionFactory factory = config.buildSessionFactory()) {
            System.out.println("=== ИЕРАРХИЯ ТОВАРОВ (JOINED) ===\n");

            // ===== СОХРАНЕНИЕ =====
            System.out.println("--- СОХРАНЕНИЕ ТОВАРОВ ---");

            try (Session session = factory.openSession()) {
                session.getTransaction().begin();

                // Создаем книгу
                Book2 book = new Book2(
                        "Чистый код",
                        new BigDecimal("1200.00"),
                        "Роберт Мартин",
                        "978-5-4461-0959-9"
                );

                // Создаем электронику
                Electronics2 electronics = new Electronics2(
                        "Смартфон Samsung",
                        new BigDecimal("45000.00"),
                        "Samsung",
                        12
                );

                // Создаем одежду
                Clothing clothing = new Clothing(
                        "Худи",
                        new BigDecimal("3500.00"),
                        "M",
                        "Хлопок"
                );

                // Сохраняем
                session.persist(book);
                session.persist(electronics);
                session.persist(clothing);

                System.out.println("   ✅ Сохранена книга: " + book);
                System.out.println("   ✅ Сохранена электроника: " + electronics);
                System.out.println("   ✅ Сохранена одежда: " + clothing);

                session.getTransaction().commit();
            }

            // ===== ПРОВЕРКА ЧЕРЕЗ NATIVE SQL =====
            System.out.println("\n--- ПРОВЕРКА ЧЕРЕЗ NATIVE SQL ---");

            try (Session session = factory.openSession()) {
                // 1. Проверяем количество строк в таблице products
                Number productsCount = (Number) session
                        .createNativeQuery("SELECT COUNT(*) FROM products")
                        .getSingleResult();
                System.out.println("   📊 Строк в products: " + productsCount);

                // 2. Проверяем количество строк в таблице books
                Number booksCount = (Number) session
                        .createNativeQuery("SELECT COUNT(*) FROM books")
                        .getSingleResult();
                System.out.println("   📊 Строк в books: " + booksCount);

                // 3. Проверяем количество строк в таблице electronics
                Number electronicsCount = (Number) session
                        .createNativeQuery("SELECT COUNT(*) FROM electronics")
                        .getSingleResult();
                System.out.println("   📊 Строк в electronics: " + electronicsCount);

                // 4. Проверяем количество строк в таблице clothing
                Number clothingCount = (Number) session
                        .createNativeQuery("SELECT COUNT(*) FROM clothing")
                        .getSingleResult();
                System.out.println("   📊 Строк в clothing: " + clothingCount);
            }

            // ===== ЗАГРУЗКА И ВЫВОД =====
            System.out.println("\n--- ЗАГРУЗКА ТОВАРОВ ---");

            try (Session session = factory.openSession()) {
                // Загружаем книгу (id = 1)
                Book2 loadedBook = session.get(Book2.class, 1L);
                if (loadedBook != null) {
                    System.out.println("   📚 Книга: " + loadedBook);
                }

                // Загружаем электронику (id = 2)
                Electronics2 loadedElectronics = session.get(Electronics2.class, 2L);
                if (loadedElectronics != null) {
                    System.out.println("   💻 Электроника: " + loadedElectronics);
                }

                // Загружаем одежду (id = 3)
                Clothing loadedClothing = session.get(Clothing.class, 3L);
                if (loadedClothing != null) {
                    System.out.println("   👕 Одежда: " + loadedClothing);
                }
            }

            // ===== СРАВНЕНИЕ СТРАТЕГИЙ =====
            System.out.println("\n--- СРАВНЕНИЕ СТРАТЕГИЙ НАСЛЕДОВАНИЯ ---");
            System.out.println("   📌 JOINED: каждая сущность имеет свою таблицу");
            System.out.println("   📌 Общие поля хранятся в родительской таблице (products)");
            System.out.println("   📌 Специфичные поля — в дочерних таблицах (books, electronics, clothing)");
            System.out.println("   📌 При SELECT выполняется JOIN родительской и дочерней таблиц");
            System.out.println("   📌 Преимущества: нормализованная структура, нет NULL");
            System.out.println("   📌 Недостатки: медленнее при JOIN (особенно с большим количеством подклассов)");

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Программа завершена");
    }
}
