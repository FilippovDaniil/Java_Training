package m51_hibernate_orm.practice.task04;

/**
 * Задача 04 — Модуль 51: Обновление detached-объекта через merge()
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Сохраните пост (первая сессия — объект в состоянии PERSISTENT).
 *   2) Закройте первую сессию → объект становится DETACHED.
 *   3) Измените title у detached-объекта (без сессии).
 *   4) Откройте вторую сессию, вызовите session.merge(detachedPost) → объект снова PERSISTENT.
 *   5) Сделайте commit — Hibernate выполнит UPDATE.
 *   6) Откройте третью сессию, загрузите пост по id и убедитесь, что title обновился.
 *
 * СОСТОЯНИЯ:
 *   PERSISTENT  — изменения объекта отслеживаются сессией (dirty checking)
 *   DETACHED    — сессия закрыта; изменения объекта НЕ попадут в БД без merge()
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Hibernate: insert into posts …
 *   Hibernate: update posts set title=? … where id=?
 *   Обновлённый title: "Изменённый заголовок"
 *
 * ПОДСКАЗКА:
 *   // После закрытия первой сессии:
 *   post.setTitle("Изменённый заголовок");  // меняем detached
 *   try (Session session2 = factory.openSession()) {
 *       Transaction tx = session2.beginTransaction();
 *       Post4 managed = session2.merge(post);  // merge возвращает managed-копию
 *       tx.commit();
 *   }
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

public class Task04 {
    public static void main(String[] args) {
        // Создаем SessionFactory с правильной конфигурацией
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:blogdb;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");  // ВАЖНО: добавляем это свойство!
        config.setProperty("hibernate.show_sql", "true");
        config.addAnnotatedClass(Post4.class);

        try (SessionFactory factory = config.buildSessionFactory()) {
            System.out.println("=== ДЕМОНСТРАЦИЯ MERGE (DETACHED -> PERSISTENT) ===\n");

            // ===== ШАГ 1: СОХРАНЕНИЕ ПОСТА =====
            System.out.println("--- ШАГ 1: Сохранение поста ---");

            Post4 post = null;
            Long savedId;

            try (Session session1 = factory.openSession()) {
                Transaction tx = session1.beginTransaction();

                post = new Post4("Оригинальный заголовок", "Оригинальное содержание");
                session1.persist(post);

                System.out.println("   Сохранен пост: " + post);
                System.out.println("   ID: " + post.getId());

                savedId = post.getId();
                tx.commit();
                System.out.println("   ✅ Пост сохранен");
            }

            System.out.println("\n   📌 Сессия закрыта. Объект теперь в состоянии DETACHED");

            // ===== ШАГ 2: ИЗМЕНЕНИЕ DETACHED-ОБЪЕКТА =====
            System.out.println("\n--- ШАГ 2: Изменение detached-объекта ---");

            String oldTitle = post.getTitle();
            post.setTitle("Изменённый заголовок через merge");
            post.setContent("Изменённое содержание через merge");

            System.out.println("   Изменен title: '" + oldTitle + "' -> '" + post.getTitle() + "'");
            System.out.println("   📌 Объект изменен, БД еще не обновлена");

            // ===== ШАГ 3: MERGE =====
            System.out.println("\n--- ШАГ 3: Вызов merge ---");

            try (Session session2 = factory.openSession()) {
                Transaction tx = session2.beginTransaction();

                Post4 managedPost = session2.merge(post);

                System.out.println("   merge() вернул: " + managedPost);
                System.out.println("   📌 Объект теперь в состоянии PERSISTENT");

                tx.commit();
                System.out.println("   ✅ COMMIT выполнен (Hibernate выполнил UPDATE)");

                post = managedPost;
            }

            // ===== ШАГ 4: ПРОВЕРКА =====
            System.out.println("\n--- ШАГ 4: Проверка обновления ---");

            try (Session session3 = factory.openSession()) {
                Transaction tx = session3.beginTransaction();

                Post4 loadedPost = session3.get(Post4.class, savedId);

                System.out.println("   Загружен пост: " + loadedPost);
                System.out.println("   Title: " + loadedPost.getTitle());
                System.out.println("   Content: " + loadedPost.getContent());

                if ("Изменённый заголовок через merge".equals(loadedPost.getTitle())) {
                    System.out.println("\n   ✅ Успешно! Заголовок обновлен через merge");
                }

                tx.commit();
            }

            // ===== ШАГ 5: ДЕМОНСТРАЦИЯ DIRTY CHECKING =====
            System.out.println("\n--- ШАГ 5: Dirty checking (автоматическое обновление) ---");

            try (Session session4 = factory.openSession()) {
                Transaction tx = session4.beginTransaction();

                // Загружаем существующий пост
                Post4 existingPost = session4.get(Post4.class, savedId);
                System.out.println("   Загружен пост: " + existingPost);

                // Меняем объект в сессии
                existingPost.setTitle("Обновлен через dirty checking");
                System.out.println("   Изменен title на: 'Обновлен через dirty checking'");
                System.out.println("   📌 Изменения будут сохранены автоматически при commit");

                tx.commit();
                System.out.println("   ✅ Изменения сохранены (dirty checking)");
            }

            // ===== ШАГ 6: ФИНАЛЬНАЯ ПРОВЕРКА =====
            System.out.println("\n--- ШАГ 6: Финальная проверка ---");

            try (Session session5 = factory.openSession()) {
                Transaction tx = session5.beginTransaction();

                Post4 finalPost = session5.get(Post4.class, savedId);
                System.out.println("   Финальное состояние: " + finalPost);
                System.out.println("   Title: " + finalPost.getTitle());

                tx.commit();
            }

            // ===== СРАВНЕНИЕ МЕТОДОВ =====
            System.out.println("\n--- СРАВНЕНИЕ persist(), merge() и dirty checking ---");
            System.out.println("\n   persist():");
            System.out.println("   - Используется для НОВЫХ объектов (TRANSIENT)");
            System.out.println("   - Сохраняет объект в БД");
            System.out.println("   - Объект становится PERSISTENT");

            System.out.println("\n   merge():");
            System.out.println("   - Используется для DETACHED объектов");
            System.out.println("   - Возвращает новый PERSISTENT объект");
            System.out.println("   - Обновляет существующую запись в БД");

            System.out.println("\n   Dirty checking (автоматически):");
            System.out.println("   - Работает для PERSISTENT объектов");
            System.out.println("   - Изменения автоматически сохраняются при commit");
            System.out.println("   - Не нужно вызывать update() или merge()");

            System.out.println("\n✅ Программа завершена");

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
