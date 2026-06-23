package m52_hibernate_relationships.practice.task05;

/**
 * Задача 05 — Модуль 52: cascade и orphanRemoval (Post — Comment)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) В классе Post5 настройте @OneToMany к Comment5:
 *      - cascade = CascadeType.ALL
 *      - orphanRemoval = true
 *      - mappedBy = "post"
 *   2) В классе Comment5 настройте @ManyToOne к Post5:
 *      - FetchType.LAZY, @JoinColumn(name = "post_id")
 *   3) В методе main выполните три сценария:
 *
 *   Сценарий А — каскадное сохранение:
 *      Создайте Post5 с 3 комментариями. Сохраните ТОЛЬКО пост (em.persist(post)).
 *      Убедитесь, что все 3 комментария появились в БД.
 *
 *   Сценарий Б — каскадное удаление:
 *      Удалите пост (em.remove(post)). Убедитесь, что комментарии тоже удалены.
 *
 *   Сценарий В — orphanRemoval:
 *      Создайте новый пост с 2 комментариями. Сохраните.
 *      Удалите один комментарий из post.getComments(), сделайте em.merge(post).
 *      Убедитесь, что комментарий удалён из БД (без явного em.remove).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   [А] Комментариев в БД: 3
 *   [Б] Комментариев после удаления поста: 0
 *   [В] Комментариев после orphan removal: 1
 *
 * ПОДСКАЗКА:
 *   Для проверки выполните COUNT-запрос через JPQL:
 *     em.createQuery("SELECT COUNT(c) FROM Comment5 c", Long.class).getSingleResult()
 *   Каскадное удаление — следствие CascadeType.REMOVE (входит в ALL).
 *   orphanRemoval удаляет объект, исключённый из коллекции, даже без REMOVE-каскада.
 */

import jakarta.persistence.*;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

class Task05 {

    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:blogdb;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.addAnnotatedClass(Post5.class);
        config.addAnnotatedClass(Comment5.class);

        try (EntityManagerFactory emf = config.buildSessionFactory()) {
            System.out.println("=== РАСШИРЕННЫЕ ТЕСТЫ CASCADE ===\n");

            // ===== ТЕСТ 1: CASCADE PERSIST =====
            System.out.println("--- ТЕСТ 1: Cascade PERSIST ---");
            testCascadePersist(emf);

            // ===== ТЕСТ 2: CASCADE REMOVE =====
            System.out.println("\n--- ТЕСТ 2: Cascade REMOVE ---");
            testCascadeRemove(emf);

            // ===== ТЕСТ 3: ORPHAN REMOVAL =====
            System.out.println("\n--- ТЕСТ 3: Orphan Removal ---");
            testOrphanRemoval(emf);

            // ===== ТЕСТ 4: Cascade MERGE =====
            System.out.println("\n--- ТЕСТ 4: Cascade MERGE ---");
            testCascadeMerge(emf);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testCascadePersist(EntityManagerFactory emf) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Post5 post = new Post5("Пост с комментариями");
            post.addComment(new Comment5("Комментарий 1"));
            post.addComment(new Comment5("Комментарий 2"));
            post.addComment(new Comment5("Комментарий 3"));

            // Сохраняем только пост
            em.persist(post);

            em.getTransaction().commit();

            // Проверяем
            Long count = em.createQuery("SELECT COUNT(c) FROM Comment5 c", Long.class)
                    .getSingleResult();
            System.out.println("   ✅ Комментариев сохранено: " + count);
        }
    }

    private static void testCascadeRemove(EntityManagerFactory emf) {
        try (EntityManager em = emf.createEntityManager()) {
            // Сначала создаем пост с комментариями
            em.getTransaction().begin();

            Post5 post = new Post5("Пост для удаления");
            post.addComment(new Comment5("Комментарий для удаления 1"));
            post.addComment(new Comment5("Комментарий для удаления 2"));

            em.persist(post);
            Long postId = post.getId();
            em.getTransaction().commit();

            // Теперь удаляем
            em.getTransaction().begin();
            Post5 toDelete = em.find(Post5.class, postId);
            em.remove(toDelete);
            em.getTransaction().commit();

            // Проверяем
            Long count = em.createQuery("SELECT COUNT(c) FROM Comment5 c", Long.class)
                    .getSingleResult();
            System.out.println("   ✅ Комментариев после удаления: " + count + " (ожидается 0)");
        }
    }

    private static void testOrphanRemoval(EntityManagerFactory emf) {
        try (EntityManager em = emf.createEntityManager()) {
            // Создаем пост с комментариями
            em.getTransaction().begin();

            Post5 post = new Post5("Пост для orphan");
            post.addComment(new Comment5("Первый"));
            post.addComment(new Comment5("Второй"));
            post.addComment(new Comment5("Третий"));

            em.persist(post);
            Long postId = post.getId();
            em.getTransaction().commit();

            // Удаляем один комментарий из коллекции
            em.getTransaction().begin();
            Post5 loaded = em.find(Post5.class, postId);
            Comment5 toRemove = loaded.getComments().get(1); // Второй комментарий
            loaded.removeComment(toRemove);
            em.merge(loaded);
            em.getTransaction().commit();

            // Проверяем
            Long count = em.createQuery("SELECT COUNT(c) FROM Comment5 c", Long.class)
                    .getSingleResult();
            System.out.println("   ✅ Комментариев после orphan removal: " + count + " (ожидается 2)");
        }
    }

    private static void testCascadeMerge(EntityManagerFactory emf) {
        try (EntityManager em = emf.createEntityManager()) {
            // Создаем пост с комментариями
            Long postId;
            em.getTransaction().begin();

            Post5 post = new Post5("Пост для merge");
            post.addComment(new Comment5("Комментарий для merge"));

            em.persist(post);
            postId = post.getId();
            em.getTransaction().commit();

            // Закрываем EM, объект становится DETACHED
            em.clear();

            // Изменяем detached объект
            Post5 detached = em.find(Post5.class, postId);
            detached.setTitle("Измененный заголовок");
            detached.getComments().get(0).setText("Измененный комментарий");

            // merge должен обновить и пост, и комментарий
            em.getTransaction().begin();
            em.merge(detached);
            em.getTransaction().commit();

            // Проверяем
            Post5 updated = em.find(Post5.class, postId);
            System.out.println("   ✅ Заголовок обновлен: " + updated.getTitle());
            System.out.println("   ✅ Комментарий обновлен: " + updated.getComments().get(0).getText());
        }
    }
}
