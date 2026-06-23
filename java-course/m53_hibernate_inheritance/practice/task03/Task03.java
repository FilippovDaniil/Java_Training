package m53_hibernate_inheritance.practice.task03;

/**
 * Задача 03 — Модуль 53: TABLE_PER_CLASS — отдельная таблица на каждый класс
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md).
 * Файл не компилируется без этих зависимостей — это ожидаемо.
 *
 * ЗАДАНИЕ:
 *   Реализуйте иерархию Product → Book / Electronics стратегией TABLE_PER_CLASS:
 *     1) Product — абстрактный @Entity с @Inheritance(TABLE_PER_CLASS).
 *        Генератор id: GenerationType.TABLE (IDENTITY не работает с TABLE_PER_CLASS!).
 *     2) Book — @Entity @Table("tpc_books"), поля: author, isbn.
 *        Таблица tpc_books содержит ВСЕ поля: id, name, price, author, isbn.
 *     3) Electronics — @Entity @Table("tpc_electronics"), поля: brand, warrantyMonths.
 *        Таблица tpc_electronics содержит: id, name, price, brand, warrantyMonths.
 *     4) Сохраните несколько объектов; выполните JPQL "FROM Product2 p"
 *        и убедитесь, что возвращаются объекты обоих типов.
 *     5) Выведите SQL, который генерирует Hibernate для полиморфного запроса
 *        (включите show_sql=true) — вы увидите UNION.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Hibernate: select ... from ( select ... from tpc_books union all select ... from tpc_electronics ) ...
 *   Всего товаров: 3
 *
 * ПОДСКАЗКА:
 *   GenerationType.TABLE требует таблицы hibernate_sequences (Hibernate создаёт автоматически
 *   при hbm2ddl.auto=create-drop).
 *   Полиморфный JPQL "FROM ProductTPC" → Hibernate строит UNION ALL по всем таблицам.
 *   При 10 подтипах это 10 подзапросов — один из минусов стратегии.
 *   Также нельзя создать FK из другой таблицы, ссылающийся на «любой Product».
 *
 *   После выполнения задания ответьте на вопросы в комментариях ниже.
 */

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.util.List;

public class Task03 {

    public static void main(String[] args) {
        // Создаем SessionFactory с show_sql=true
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:shop;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.format_sql", "true");
        config.addAnnotatedClass(ProductTPC.class);
        config.addAnnotatedClass(BookTPC.class);
        config.addAnnotatedClass(ElectronicsTPC.class);

        try (SessionFactory factory = config.buildSessionFactory()) {
            System.out.println("=== ИЕРАРХИЯ ТОВАРОВ (TABLE_PER_CLASS) ===\n");

            // ===== СОХРАНЕНИЕ =====
            System.out.println("--- СОХРАНЕНИЕ ТОВАРОВ ---");

            try (Session session = factory.openSession()) {
                session.getTransaction().begin();

                // Создаем книги
                BookTPC book1 = new BookTPC(
                        "Чистый код",
                        new BigDecimal("1200.00"),
                        "Роберт Мартин",
                        "978-5-4461-0959-9"
                );

                BookTPC book2 = new BookTPC(
                        "Эффективная Java",
                        new BigDecimal("1500.00"),
                        "Джошуа Блох",
                        "978-5-8459-1152-5"
                );

                // Создаем электронику
                ElectronicsTPC electronics = new ElectronicsTPC(
                        "Смартфон Samsung",
                        new BigDecimal("45000.00"),
                        "Samsung",
                        12
                );

                // Сохраняем
                session.persist(book1);
                session.persist(book2);
                session.persist(electronics);

                System.out.println("   ✅ Сохранена книга 1: " + book1);
                System.out.println("   ✅ Сохранена книга 2: " + book2);
                System.out.println("   ✅ Сохранена электроника: " + electronics);

                session.getTransaction().commit();
            }

            // ===== ПОЛИМОРФНЫЙ ЗАПРОС (UNION ALL) =====
            System.out.println("\n--- ПОЛИМОРФНЫЙ ЗАПРОС (FROM ProductTPC) ---");
            System.out.println("   📌 Hibernate должен сгенерировать UNION ALL");
            System.out.println("   📌 Смотрите SQL в логах выше\n");

            try (Session session = factory.openSession()) {
                // Полиморфный запрос - возвращает все подтипы
                List<ProductTPC> products = session
                        .createQuery("FROM ProductTPC", ProductTPC.class)
                        .getResultList();

                System.out.println("   📊 Всего товаров: " + products.size());
                for (ProductTPC p : products) {
                    System.out.println("   - " + p.getClass().getSimpleName() + ": " + p);
                }
            }

            // ===== ЗАГРУЗКА КОНКРЕТНЫХ ТИПОВ =====
            System.out.println("\n--- ЗАГРУЗКА КОНКРЕТНЫХ ТИПОВ ---");

            try (Session session = factory.openSession()) {
                // Загружаем книгу (id = 1)
                BookTPC loadedBook = session.get(BookTPC.class, 1L);
                if (loadedBook != null) {
                    System.out.println("   📚 Книга: " + loadedBook);
                }

                // Загружаем электронику (id = 3)
                ElectronicsTPC loadedElectronics = session.get(ElectronicsTPC.class, 3L);
                if (loadedElectronics != null) {
                    System.out.println("   💻 Электроника: " + loadedElectronics);
                }
            }

            // ===== ПРОВЕРКА СТРУКТУРЫ ТАБЛИЦ =====
            System.out.println("\n--- ПРОВЕРКА СТРУКТУРЫ ТАБЛИЦ (NATIVE SQL) ---");

            try (Session session = factory.openSession()) {
                // Проверяем таблицу книг
                Number booksCount = (Number) session
                        .createNativeQuery("SELECT COUNT(*) FROM tpc_books")
                        .getSingleResult();
                System.out.println("   📊 Строк в tpc_books: " + booksCount);

                // Проверяем таблицу электроники
                Number electronicsCount = (Number) session
                        .createNativeQuery("SELECT COUNT(*) FROM tpc_electronics")
                        .getSingleResult();
                System.out.println("   📊 Строк в tpc_electronics: " + electronicsCount);

                // Проверяем, что у книг есть все поля (name, price, author, isbn)
                System.out.println("\n   📌 В tpc_books есть ВСЕ поля: id, name, price, author, isbn");
                System.out.println("   📌 В tpc_electronics есть ВСЕ поля: id, name, price, brand, warrantyMonths");
                System.out.println("   📌 Нет таблицы products (в отличие от JOINED)");
            }

            // ===== СРАВНЕНИЕ СТРАТЕГИЙ =====
            System.out.println("\n--- СРАВНЕНИЕ СТРАТЕГИЙ НАСЛЕДОВАНИЯ ---");
            System.out.println("   📌 TABLE_PER_CLASS: отдельная таблица для каждого класса");
            System.out.println("   📌 Каждая таблица содержит ВСЕ поля (родительские + свои)");
            System.out.println("   📌 При полиморфном запросе используется UNION ALL");
            System.out.println("   📌 Преимущества: нет NULL, нет JOIN");
            System.out.println("   📌 Недостатки: дублирование полей, UNION ALL при полиморфных запросах");

            // ===== ОТВЕТЫ НА ВОПРОСЫ =====
            System.out.println("\n--- ОТВЕТЫ НА ВОПРОСЫ ---");
            System.out.println("\n   Q1: Почему GenerationType.IDENTITY не работает с TABLE_PER_CLASS?");
            System.out.println("      ID должен быть уникальным СРЕДИ ВСЕХ подтипов.");
            System.out.println("      При IDENTITY каждая таблица имеет свой счетчик,");
            System.out.println("      что может привести к конфликтам ID в разных таблицах.");
            System.out.println("      GenerationType.TABLE использует общий счетчик.");

            System.out.println("\n   Q2: Можно ли создать внешний ключ из таблицы orders на «любой Product»");
            System.out.println("      при стратегии TABLE_PER_CLASS? Почему?");
            System.out.println("      НЕТ. При TABLE_PER_CLASS нет общей таблицы products,");
            System.out.println("      поэтому FK не может ссылаться на все подтипы.");
            System.out.println("      FK может ссылаться только на одну конкретную таблицу.");

            System.out.println("\n   Q3: При каком числе подтипов UNION ALL становится заметно медленнее?");
            System.out.println("      При 10+ подтипах UNION ALL может стать проблемой.");
            System.out.println("      Чем больше подтипов, тем больше подзапросов в UNION,");
            System.out.println("      что увеличивает время выполнения и нагрузку на БД.");

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Программа завершена");
    }
}
