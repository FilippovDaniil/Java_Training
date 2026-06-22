package m51_hibernate_orm.practice.task05;

/**
 * Задача 05 — Модуль 51: Удаление сущности через remove()
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Сохраните три поста.
 *   2) Загрузите пост с id=2L через session.get().
 *   3) Вызовите session.remove(managed) — объект переходит в состояние REMOVED.
 *   4) Сделайте commit — Hibernate выполнит DELETE.
 *   5) Проверьте: загрузите все посты через JPQL "FROM Post5" и убедитесь, что остались два.
 *
 * ВАЖНО:
 *   Нельзя вызвать remove() для DETACHED-объекта напрямую.
 *   Сначала нужно загрузить объект в текущую сессию (get/find),
 *   а затем вызвать remove() — только тогда он в состоянии MANAGED.
 *
 *   ❌ Неправильно:
 *      Post5 detached = ...  // получен в другой сессии
 *      session.remove(detached);  // IllegalArgumentException / ошибка
 *
 *   ✅ Правильно:
 *      Post5 managed = session.get(Post5.class, id);
 *      session.remove(managed);
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Hibernate: delete from posts where id=?
 *   Постов после удаления: 2
 *
 * ПОДСКАЗКА:
 *   try (Session session = factory.openSession()) {
 *       Transaction tx = session.beginTransaction();
 *       Post5 toDelete = session.get(Post5.class, 2L);
 *       if (toDelete != null) {
 *           session.remove(toDelete);
 *       }
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
import java.util.List;

public class Task05 {

    public static void main(String[] args) {
        // Создаем SessionFactory
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:blogdb;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.addAnnotatedClass(Post5.class);

        try (SessionFactory factory = config.buildSessionFactory()) {
            System.out.println("=== УДАЛЕНИЕ СУЩНОСТИ ЧЕРЕЗ remove() ===\n");

            // ===== ШАГ 1: СОХРАНЕНИЕ ТРЕХ ПОСТОВ =====
            System.out.println("--- ШАГ 1: Сохранение трех постов ---");

            try (Session session = factory.openSession()) {
                Transaction tx = session.beginTransaction();

                Post5 post1 = new Post5("Пост А", "Содержание поста А");
                Post5 post2 = new Post5("Пост Б", "Содержание поста Б");
                Post5 post3 = new Post5("Пост В", "Содержание поста В");

                session.persist(post1);
                session.persist(post2);
                session.persist(post3);

                System.out.println("   Сохранены посты:");
                System.out.println("   - " + post1);
                System.out.println("   - " + post2);
                System.out.println("   - " + post3);

                tx.commit();
                System.out.println("   ✅ Все посты сохранены");
            }

            // ===== ШАГ 2: ПРОВЕРКА ПЕРЕД УДАЛЕНИЕМ =====
            System.out.println("\n--- ШАГ 2: Проверка перед удалением ---");
            printAllPosts(factory, "   Всего постов до удаления: ");

            // ===== ШАГ 3: УДАЛЕНИЕ ПОСТА С ID=2 =====
            System.out.println("\n--- ШАГ 3: Удаление поста с id=2 ---");

            try (Session session = factory.openSession()) {
                Transaction tx = session.beginTransaction();

                // Загружаем пост для удаления (MANAGED состояние)
                Post5 toDelete = session.get(Post5.class, 2L);

                if (toDelete != null) {
                    System.out.println("   Найден пост для удаления: " + toDelete);
                    System.out.println("   Состояние: MANAGED (отслеживается сессией)");

                    // Удаляем объект
                    session.remove(toDelete);
                    System.out.println("   Вызван session.remove()");
                    System.out.println("   Состояние: REMOVED (будет удален при commit)");

                    tx.commit();
                    System.out.println("   ✅ COMMIT выполнен (Hibernate выполнил DELETE)");
                } else {
                    System.out.println("   ❌ Пост с id=2 не найден");
                }
            }

            // ===== ШАГ 4: ПРОВЕРКА ПОСЛЕ УДАЛЕНИЯ =====
            System.out.println("\n--- ШАГ 4: Проверка после удаления ---");
            printAllPosts(factory, "   Всего постов после удаления: ");

            // ===== ШАГ 5: ДЕМОНСТРАЦИЯ НЕПРАВИЛЬНОГО УДАЛЕНИЯ =====
            System.out.println("\n--- ШАГ 5: Демонстрация правильного удаления ---");
            demonstrateCorrectRemove(factory);

            // ===== ШАГ 6: УДАЛЕНИЕ ЧЕРЕЗ JPQL (bulk delete) =====
            System.out.println("\n--- ШАГ 6: Удаление через JPQL (bulk delete) ---");

            // Добавляем временный пост
            try (Session session = factory.openSession()) {
                Transaction tx = session.beginTransaction();
                Post5 extra = new Post5("Временный пост", "Будет удален через JPQL");
                session.persist(extra);
                tx.commit();
                System.out.println("   Добавлен временный пост");
            }

            printAllPosts(factory, "   До JPQL удаления: ");

            try (Session session = factory.openSession()) {
                Transaction tx = session.beginTransaction();

                // ✅ Правильный способ: не указывать класс результата для DELETE
                int deletedCount = session.createMutationQuery(
                        "DELETE FROM Post5 p WHERE p.title LIKE '%временный%'"
                ).executeUpdate();

                System.out.println("   Выполнен JPQL DELETE (bulk delete)");
                System.out.println("   Удалено постов: " + deletedCount);

                tx.commit();
            }

            printAllPosts(factory, "   После JPQL удаления: ");

            // ===== ШАГ 7: СРАВНЕНИЕ МЕТОДОВ УДАЛЕНИЯ =====
            System.out.println("\n--- СРАВНЕНИЕ МЕТОДОВ УДАЛЕНИЯ ---");
            System.out.println("\n   session.remove(managed):");
            System.out.println("   - Удаляет один объект");
            System.out.println("   - Объект должен быть MANAGED (загружен в сессии)");
            System.out.println("   - Выполняется DELETE при commit");
            System.out.println("   - Можно использовать каскадное удаление");

            System.out.println("\n   JPQL DELETE (bulk):");
            System.out.println("   - Удаляет множество объектов по условию");
            System.out.println("   - Не загружает объекты в память");
            System.out.println("   - Выполняется сразу (без dirty checking)");
            System.out.println("   - Не активирует каскадные операции");

            System.out.println("\n   createMutationQuery() vs createQuery():");
            System.out.println("   - createMutationQuery() - для UPDATE/DELETE");
            System.out.println("   - createQuery() - для SELECT (возвращает данные)");

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Программа завершена");
    }

    /**
     * Вспомогательный метод для вывода всех постов
     */
    private static void printAllPosts(SessionFactory factory, String prefix) {
        try (Session session = factory.openSession()) {
            List<Post5> posts = session.createQuery("FROM Post5 ORDER BY id", Post5.class).list();
            System.out.println(prefix + posts.size());
            for (Post5 post : posts) {
                System.out.println("   - " + post);
            }
        }
    }

    /**
     * Демонстрация правильного удаления DETACHED-объекта
     */
    private static void demonstrateCorrectRemove(SessionFactory factory) {
        System.out.println("   ✅ Правильный способ удаления:");

        Post5 managedPost = null;

        // Загружаем объект в сессии
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            managedPost = session.get(Post5.class, 1L);
            System.out.println("   1. Загружен объект в сессии: " + managedPost);
            System.out.println("      Состояние: MANAGED");

            // Удаляем объект
            session.remove(managedPost);
            System.out.println("   2. Вызван session.remove()");
            System.out.println("      Состояние: REMOVED");

            tx.commit();
            System.out.println("   3. После commit: объект удален из БД");
        }

        // Проверяем, что объект удален
        try (Session session = factory.openSession()) {
            Post5 check = session.get(Post5.class, 1L);
            if (check == null) {
                System.out.println("   4. Проверка: объект с id=1 не найден ✅");
            }
        }
    }
}


