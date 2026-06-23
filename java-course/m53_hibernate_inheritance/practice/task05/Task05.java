package m53_hibernate_inheritance.practice.task05;

/**
 * Задача 05 — Модуль 53: Полиморфные JPQL-запросы и instanceof-обработка
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md).
 * Файл не компилируется без этих зависимостей — это ожидаемо.
 *
 * ЗАДАНИЕ:
 *   Используя стратегию SINGLE_TABLE (быстрые полиморфные запросы), реализуйте:
 *     1) Иерархию ProductShop (abstract @Entity, SINGLE_TABLE) → Book, Electronics, Clothing.
 *        Каждый подкласс имеет минимум 1-2 уникальных поля.
 *     2) Заполните БД: 3 книги, 2 электроники, 2 одежды.
 *     3) Полиморфный запрос: загрузите ВСЕ товары одним JPQL "FROM ProductShop".
 *     4) Обработайте список через instanceof (pattern matching Java 16+):
 *        - для Book выведите: "Книга: {name} — {author}"
 *        - для Electronics: "Электроника: {name} — {brand}, гарантия {warrantyMonths} мес."
 *        - для Clothing: "Одежда: {name} — размер {size}"
 *     5) Напишите JPQL с фильтром по типу: "FROM Book b WHERE b.author = :author"
 *        и убедитесь, что возвращаются только книги нужного автора.
 *     6) Подсчитайте количество каждого типа через JPQL:
 *        "SELECT TYPE(p), COUNT(p) FROM ProductShop p GROUP BY TYPE(p)"
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Книга: Чистый код — Роберт Мартин
 *   Электроника: MacBook Pro — Apple, гарантия 12 мес.
 *   Одежда: Джинсы — размер 32
 *   ... (все 7 товаров)
 *   По автору 'Роберт Мартин': 2 книги
 *   Тип BOOK: 3 шт., ELECTRONICS: 2 шт., CLOTHING: 2 шт.
 *
 * ПОДСКАЗКА:
 *   Полиморфный запрос:
 *     List<ProductShop> all = session
 *         .createQuery("FROM ProductShop", ProductShop.class)
 *         .getResultList();
 *
 *   Pattern matching (Java 16+):
 *     if (p instanceof Book b) { ... b.getAuthor() ... }
 *
 *   Запрос по типу:
 *     session.createQuery("FROM Book b WHERE b.author = :author", Book.class)
 *            .setParameter("author", "Роберт Мартин")
 *            .getResultList();
 *
 *   GROUP BY TYPE():
 *     session.createQuery(
 *         "SELECT TYPE(p), COUNT(p) FROM ProductShop p GROUP BY TYPE(p)",
 *         Object[].class
 *     ).getResultList();
 */

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task05 {

    public static void main(String[] args) {
        // Создаем SessionFactory
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:shop;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.format_sql", "true");
        config.addAnnotatedClass(ProductShop.class);
        config.addAnnotatedClass(BookShop.class);
        config.addAnnotatedClass(ElectronicsShop.class);
        config.addAnnotatedClass(ClothingShop.class);

        try (SessionFactory factory = config.buildSessionFactory()) {
            System.out.println("=== ПОЛИМОРФНЫЕ JPQL-ЗАПРОСЫ ===\n");

            // ============================================
            // СОХРАНЕНИЕ ДАННЫХ
            // ============================================
            System.out.println("--- СОХРАНЕНИЕ ТОВАРОВ ---");

            try (Session session = factory.openSession()) {
                session.getTransaction().begin();

                // Книги
                BookShop book1 = new BookShop(
                        "Чистый код",
                        new BigDecimal("1200.00"),
                        "Роберт Мартин",
                        "Техническая"
                );
                BookShop book2 = new BookShop(
                        "Эффективная Java",
                        new BigDecimal("1500.00"),
                        "Джошуа Блох",
                        "Техническая"
                );
                BookShop book3 = new BookShop(
                        "Война и мир",
                        new BigDecimal("800.00"),
                        "Лев Толстой",
                        "Художественная"
                );

                // Электроника
                ElectronicsShop electronics1 = new ElectronicsShop(
                        "MacBook Pro",
                        new BigDecimal("150000.00"),
                        "Apple",
                        12
                );
                ElectronicsShop electronics2 = new ElectronicsShop(
                        "Samsung Galaxy S23",
                        new BigDecimal("80000.00"),
                        "Samsung",
                        24
                );

                // Одежда
                ClothingShop clothing1 = new ClothingShop(
                        "Джинсы",
                        new BigDecimal("3500.00"),
                        "32",
                        "Хлопок"
                );
                ClothingShop clothing2 = new ClothingShop(
                        "Куртка",
                        new BigDecimal("8500.00"),
                        "M",
                        "Кожа"
                );

                session.persist(book1);
                session.persist(book2);
                session.persist(book3);
                session.persist(electronics1);
                session.persist(electronics2);
                session.persist(clothing1);
                session.persist(clothing2);

                System.out.println("   ✅ Сохранено 7 товаров:");
                System.out.println("      - 3 книги");
                System.out.println("      - 2 электроники");
                System.out.println("      - 2 одежды");

                session.getTransaction().commit();
            }

            // ============================================
            // ПОЛИМОРФНЫЙ ЗАПРОС (ВСЕ ТОВАРЫ)
            // ============================================
            System.out.println("\n--- ПОЛИМОРФНЫЙ ЗАПРОС: Все товары ---");

            try (Session session = factory.openSession()) {
                List<ProductShop> products = session
                        .createQuery("FROM ProductShop ORDER BY id", ProductShop.class)
                        .getResultList();

                System.out.println("   Всего товаров: " + products.size() + "\n");

                // Обработка через instanceof с pattern matching (Java 16+)
                for (ProductShop p : products) {
                    if (p instanceof BookShop b) {
                        System.out.printf("   📚 Книга: \"%s\" — %s (жанр: %s)%n",
                                b.getName(), b.getAuthor(), b.getGenre());
                    } else if (p instanceof ElectronicsShop e) {
                        System.out.printf("   💻 Электроника: \"%s\" — %s, гарантия %d мес.%n",
                                e.getName(), e.getBrand(), e.getWarrantyMonths());
                    } else if (p instanceof ClothingShop c) {
                        System.out.printf("   👕 Одежда: \"%s\" — размер %s (%s)%n",
                                c.getName(), c.getSize(), c.getMaterial());
                    } else {
                        System.out.println("   ❓ Неизвестный тип: " + p.getClass().getSimpleName());
                    }
                }
            }

            // ============================================
            // ЗАПРОС ПО АВТОРУ
            // ============================================
            System.out.println("\n--- ЗАПРОС: Книги автора 'Роберт Мартин' ---");

            try (Session session = factory.openSession()) {
                String author = "Роберт Мартин";
                List<BookShop> books = session
                        .createQuery("FROM BookShop b WHERE b.author = :author", BookShop.class)
                        .setParameter("author", author)
                        .getResultList();

                System.out.printf("   Найдено книг автора '%s': %d%n", author, books.size());
                for (BookShop b : books) {
                    System.out.printf("      - \"%s\" (%s)%n", b.getName(), b.getGenre());
                }
            }

            // ============================================
            // ГРУППИРОВКА ПО ТИПУ
            // ============================================
            System.out.println("\n--- ГРУППИРОВКА ПО ТИПУ (TYPE + COUNT) ---");

            try (Session session = factory.openSession()) {
                List<Object[]> results = session
                        .createQuery(
                                "SELECT TYPE(p), COUNT(p) FROM ProductShop p GROUP BY TYPE(p)",
                                Object[].class
                        )
                        .getResultList();

                System.out.println("   Статистика по типам товаров:");
                for (Object[] row : results) {
                    Class<?> type = (Class<?>) row[0];
                    Long count = (Long) row[1];
                    String typeName = type.getSimpleName();
                    System.out.printf("      %s: %d шт.%n", typeName, count);
                }
            }

            // ============================================
            // ДОПОЛНИТЕЛЬНЫЙ ЗАПРОС: ФИЛЬТРАЦИЯ ПО ТИПУ
            // ============================================
            System.out.println("\n--- ДОПОЛНИТЕЛЬНО: Фильтрация по типу ---");

            try (Session session = factory.openSession()) {
                // Все электронные товары с гарантией > 12 месяцев
                List<ElectronicsShop> electronics = session
                        .createQuery(
                                "FROM ElectronicsShop e WHERE e.warrantyMonths > :months",
                                ElectronicsShop.class
                        )
                        .setParameter("months", 12)
                        .getResultList();

                System.out.println("   Электроника с гарантией > 12 мес.:");
                for (ElectronicsShop e : electronics) {
                    System.out.printf("      - %s: %d мес.%n", e.getName(), e.getWarrantyMonths());
                }

                // Вся одежда из хлопка
                List<ClothingShop> clothes = session
                        .createQuery(
                                "FROM ClothingShop c WHERE c.material = :material",
                                ClothingShop.class
                        )
                        .setParameter("material", "Хлопок")
                        .getResultList();

                System.out.println("\n   Одежда из хлопка:");
                for (ClothingShop c : clothes) {
                    System.out.printf("      - %s: размер %s%n", c.getName(), c.getSize());
                }
            }

            // ============================================
            // СТАТИСТИКА
            // ============================================
            System.out.println("\n--- СТАТИСТИКА ---");

            try (Session session = factory.openSession()) {
                Long total = session
                        .createQuery("SELECT COUNT(p) FROM ProductShop p", Long.class)
                        .getSingleResult();

                Map<String, Long> typeCounts = session
                        .createQuery(
                                "SELECT TYPE(p), COUNT(p) FROM ProductShop p GROUP BY TYPE(p)",
                                Object[].class
                        )
                        .getResultList()
                        .stream()
                        .collect(Collectors.toMap(
                                row -> ((Class<?>) row[0]).getSimpleName(),
                                row -> (Long) row[1]
                        ));

                System.out.println("   Всего товаров: " + total);
                System.out.println("   По типам:");
                typeCounts.forEach((type, count) ->
                        System.out.printf("      %s: %d шт.%n", type, count)
                );
            }

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Программа завершена");
    }
}
