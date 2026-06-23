package m53_hibernate_inheritance.practice.task01;

/**
 * Задача 01 — Модуль 53: SINGLE_TABLE — иерархия товаров
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md).
 * Файл не компилируется без этих зависимостей — это ожидаемо.
 *
 * ЗАДАНИЕ:
 *   Реализуйте иерархию товаров магазина стратегией SINGLE_TABLE:
 *     1) Корневой @Entity Product: поля id (Long, @GeneratedValue IDENTITY),
 *        name (String), price (BigDecimal).
 *     2) Подкласс Book с полями author (String), isbn (String).
 *        @DiscriminatorValue("BOOK").
 *     3) Подкласс Electronics с полями brand (String), warrantyMonths (int).
 *        @DiscriminatorValue("ELECTRONICS").
 *     4) В main() создайте Hibernate SessionFactory (H2 in-memory),
 *        сохраните по одному экземпляру Book и Electronics,
 *        затем загрузите оба через session.get() и выведите.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Book: Чистый код, Роберт Мартин, 978-5-4461-0959-9
 *   Electronics: Ноутбук Dell, Dell, 12 мес.
 *
 * ПОДСКАЗКА:
 *   @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
 *   @DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
 *   Hibernate автоматически создаёт таблицу products с колонкой product_type.
 *   Для SessionFactory используйте Configuration().configure() или programmatic config.
 */

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;

public class Task01 {

    public static void main(String[] args) {
        // Создаем SessionFactory
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:shop;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.format_sql", "true");
        config.addAnnotatedClass(Product.class);
        config.addAnnotatedClass(Book.class);
        config.addAnnotatedClass(Electronics.class);

        try (SessionFactory factory = config.buildSessionFactory()) {
            System.out.println("=== ИЕРАРХИЯ ТОВАРОВ (SINGLE_TABLE) ===\n");

            // ===== СОХРАНЕНИЕ =====
            System.out.println("--- СОХРАНЕНИЕ ТОВАРОВ ---");

            try (Session session = factory.openSession()) {
                session.getTransaction().begin();

                // Создаем книгу
                Book book = new Book(
                        "Чистый код",
                        new BigDecimal("1200.00"),
                        "Роберт Мартин",
                        "978-5-4461-0959-9"
                );

                // Создаем электронику
                Electronics electronics = new Electronics(
                        "Ноутбук Dell",
                        new BigDecimal("85000.00"),
                        "Dell",
                        12
                );

                // Сохраняем
                session.persist(book);
                session.persist(electronics);

                System.out.println("   ✅ Сохранена книга: " + book);
                System.out.println("   ✅ Сохранена электроника: " + electronics);

                session.getTransaction().commit();
            }

            // ===== ЗАГРУЗКА =====
            System.out.println("\n--- ЗАГРУЗКА ТОВАРОВ ---");

            try (Session session = factory.openSession()) {
                // Загружаем книгу (id = 1)
                Book loadedBook = session.get(Book.class, 1L);
                if (loadedBook != null) {
                    System.out.println("   📚 Книга: " + loadedBook);
                } else {
                    System.out.println("   ❌ Книга не найдена");
                }

                // Загружаем электронику (id = 2)
                Electronics loadedElectronics = session.get(Electronics.class, 2L);
                if (loadedElectronics != null) {
                    System.out.println("   💻 Электроника: " + loadedElectronics);
                } else {
                    System.out.println("   ❌ Электроника не найдена");
                }

                // Загружаем все товары как Product (полиморфный запрос)
                System.out.println("\n--- ВСЕ ТОВАРЫ (ПОЛИМОРФНЫЙ ЗАПРОС) ---");
                java.util.List<Product> products = session
                        .createQuery("FROM Product", Product.class)
                        .getResultList();

                System.out.println("   Всего товаров: " + products.size());
                for (Product p : products) {
                    System.out.println("   - " + p.getClass().getSimpleName() + ": " + p);
                }
            }

            // ===== ПОКАЗ СТРУКТУРЫ ТАБЛИЦЫ =====
            System.out.println("\n--- СТРУКТУРА ТАБЛИЦЫ ---");
            System.out.println("   📌 SINGLE_TABLE создает одну таблицу products");
            System.out.println("   📌 Добавляется колонка product_type для различения типов");
            System.out.println("   📌 Колонки подклассов могут быть NULL для других типов");
            System.out.println("   📌 Преимущества: быстрые запросы, нет JOIN");
            System.out.println("   📌 Недостатки: NULL-значения, ограничения NOT NULL невозможны");

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Программа завершена");
    }
}
