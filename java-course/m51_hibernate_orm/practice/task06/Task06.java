package m51_hibernate_orm.practice.task06;

/**
 * Задача 06 — Модуль 51: JPQL-запросы (выборка, фильтрация, сортировка)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Сохраните 5 постов с разными заголовками и датами.
 *   2) Выполните JPQL-запрос "FROM Post6 ORDER BY createdAt DESC" —
 *      выведите все посты, начиная с самого нового.
 *   3) Выполните параметризованный JPQL-запрос:
 *      найдите все посты, у которых title содержит слово "Java" (LIKE :pattern).
 *   4) Выполните агрегирующий запрос:
 *      подсчитайте количество постов через "SELECT COUNT(p) FROM Post6 p".
 *
 * JPQL vs SQL:
 *   SQL:  SELECT * FROM posts ORDER BY created_at DESC
 *   JPQL: FROM Post6 ORDER BY createdAt DESC   (имя КЛАССА и ПОЛЯ, не таблицы!)
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Все посты (от новых к старым):
 *     Post6{id=5, title='Kotlin vs Java'}
 *     Post6{id=4, title='Spring Boot'}
 *     …
 *   Посты с 'Java': 2
 *   Всего постов: 5
 *
 * ПОДСКАЗКА:
 *   List<Post6> all = session
 *           .createQuery("FROM Post6 ORDER BY createdAt DESC", Post6.class)
 *           .getResultList();
 *
 *   List<Post6> filtered = session
 *           .createQuery("FROM Post6 p WHERE p.title LIKE :pattern", Post6.class)
 *           .setParameter("pattern", "%Java%")
 *           .getResultList();
 *
 *   Long count = session
 *           .createQuery("SELECT COUNT(p) FROM Post6 p", Long.class)
 *           .getSingleResult();
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.time.LocalDateTime;
import java.util.List;

public class Task06 {

    public static void main(String[] args) throws InterruptedException {
        // Создаем SessionFactory
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:blogdb;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.addAnnotatedClass(Post6.class);

        try (SessionFactory factory = config.buildSessionFactory()) {
            System.out.println("=== JPQL-ЗАПРОСЫ ===\n");

            // ===== ШАГ 1: СОХРАНЕНИЕ 5 ПОСТОВ =====
            System.out.println("--- ШАГ 1: Сохранение 5 постов ---");

            try (Session session = factory.openSession()) {
                Transaction tx = session.beginTransaction();

                // Сохраняем посты с небольшой задержкой между ними
                Post6 post1 = new Post6("Введение в Java", "Основы языка Java");
                session.persist(post1);
                System.out.println("   Сохранен: " + post1.getTitle());
                Thread.sleep(100); // Задержка для разных дат

                Post6 post2 = new Post6("Spring Boot основы", "Настройка Spring Boot");
                session.persist(post2);
                System.out.println("   Сохранен: " + post2.getTitle());
                Thread.sleep(100);

                Post6 post3 = new Post6("Hibernate ORM", "Работа с Hibernate");
                session.persist(post3);
                System.out.println("   Сохранен: " + post3.getTitle());
                Thread.sleep(100);

                Post6 post4 = new Post6("Kotlin vs Java", "Сравнение языков");
                session.persist(post4);
                System.out.println("   Сохранен: " + post4.getTitle());
                Thread.sleep(100);

                Post6 post5 = new Post6("Паттерны проектирования", "Основные паттерны");
                session.persist(post5);
                System.out.println("   Сохранен: " + post5.getTitle());

                tx.commit();
                System.out.println("   ✅ Все посты сохранены");
            }

            // ===== ШАГ 2: ВСЕ ПОСТЫ (СОРТИРОВКА) =====
            System.out.println("\n--- ШАГ 2: Все посты (от новых к старым) ---");

            try (Session session = factory.openSession()) {
                // JPQL запрос с сортировкой по дате создания (от новых к старым)
                List<Post6> posts = session
                        .createQuery("FROM Post6 ORDER BY createdAt DESC", Post6.class)
                        .getResultList();

                System.out.println("   Найдено постов: " + posts.size());
                System.out.println();
                for (Post6 post : posts) {
                    System.out.printf("   ID: %d | Заголовок: %-25s | Создан: %s%n",
                            post.getId(),
                            post.getTitle(),
                            post.getCreatedAt().toString().substring(0, 19));
                }
            }

            // ===== ШАГ 3: ПОИСК ПО ШАБЛОНУ =====
            System.out.println("\n--- ШАГ 3: Поиск по шаблону (содержит 'Java') ---");

            try (Session session = factory.openSession()) {
                // Параметризованный JPQL запрос с LIKE
                String pattern = "%Java%";
                List<Post6> javaPosts = session
                        .createQuery("FROM Post6 p WHERE p.title LIKE :pattern", Post6.class)
                        .setParameter("pattern", pattern)
                        .getResultList();

                System.out.println("   Найдено постов с 'Java': " + javaPosts.size());
                for (Post6 post : javaPosts) {
                    System.out.println("   - " + post.getTitle());
                }
            }

            // ===== ШАГ 4: АГРЕГАЦИЯ (COUNT) =====
            System.out.println("\n--- ШАГ 4: Общее количество постов (COUNT) ---");

            try (Session session = factory.openSession()) {
                // JPQL запрос с COUNT
                Long count = session
                        .createQuery("SELECT COUNT(p) FROM Post6 p", Long.class)
                        .getSingleResult();

                System.out.println("   Всего постов в БД: " + count);
            }

            // ===== ШАГ 5: ДОПОЛНИТЕЛЬНЫЕ ЗАПРОСЫ =====
            System.out.println("\n--- ШАГ 5: Дополнительные JPQL-запросы ---");

            try (Session session = factory.openSession()) {
                // 5.1: Поиск по ID
                System.out.println("\n   5.1: Поиск по ID (id=3):");
                Post6 postById = session
                        .createQuery("FROM Post6 p WHERE p.id = :id", Post6.class)
                        .setParameter("id", 3L)
                        .getSingleResult();
                System.out.println("   Найден: " + postById.getTitle());

                // 5.2: Сортировка по заголовку (ASC)
                System.out.println("\n   5.2: Посты по алфавиту:");
                List<Post6> sortedByTitle = session
                        .createQuery("FROM Post6 ORDER BY title ASC", Post6.class)
                        .getResultList();
                for (Post6 post : sortedByTitle) {
                    System.out.println("   - " + post.getTitle());
                }

                // 5.3: Ограничение количества результатов (LIMIT)
                System.out.println("\n   5.3: Первые 3 поста (по дате):");
                List<Post6> limited = session
                        .createQuery("FROM Post6 ORDER BY createdAt DESC", Post6.class)
                        .setMaxResults(3)
                        .getResultList();
                for (Post6 post : limited) {
                    System.out.println("   - " + post.getTitle());
                }

                // 5.4: Поиск с несколькими условиями
                System.out.println("\n   5.4: Поиск с несколькими условиями:");
                List<Post6> filtered = session
                        .createQuery("FROM Post6 p WHERE p.title LIKE :pattern AND p.id > :minId", Post6.class)
                        .setParameter("pattern", "%Java%")
                        .setParameter("minId", 1L)
                        .getResultList();
                System.out.println("   Найдено постов: " + filtered.size());
                for (Post6 post : filtered) {
                    System.out.println("   - " + post.getTitle());
                }
            }

            // ===== ШАГ 6: СРАВНЕНИЕ SQL И JPQL =====
            System.out.println("\n--- СРАВНЕНИЕ SQL И JPQL ---");
            System.out.println("\n   SQL запрос:");
            System.out.println("   SELECT * FROM posts ORDER BY created_at DESC");
            System.out.println("   - Работает с таблицами и столбцами");
            System.out.println("   - Зависит от структуры БД");

            System.out.println("\n   JPQL запрос:");
            System.out.println("   FROM Post6 ORDER BY createdAt DESC");
            System.out.println("   - Работает с классами и полями (объектно-ориентированный)");
            System.out.println("   - Независим от структуры БД");
            System.out.println("   - Поддерживает полиморфизм");
            System.out.println("   - Лучшая переносимость между СУБД");

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Программа завершена");
    }
}
