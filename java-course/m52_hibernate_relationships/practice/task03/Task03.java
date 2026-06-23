package m52_hibernate_relationships.practice.task03;

/**
 * Задача 03 — Модуль 52: @ManyToMany (Post N—N Tag) через @JoinTable
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Разметьте поле tags в классе Post3:
 *      - @ManyToMany с cascade = {CascadeType.PERSIST, CascadeType.MERGE}
 *      - @JoinTable(name = "post_tags3",
 *                   joinColumns = @JoinColumn(name = "post_id"),
 *                   inverseJoinColumns = @JoinColumn(name = "tag_id"))
 *   2) В классе Tag3 разметьте поле posts аннотацией @ManyToMany(mappedBy = "tags").
 *   3) В методе main:
 *      а) Создайте 2 поста и 3 тега: "java", "hibernate", "spring".
 *      б) Присвойте тегу "java" оба поста, тегу "hibernate" — первый пост,
 *         тегу "spring" — второй пост.
 *      в) Сохраните все сущности через persist постов (каскад подхватит теги).
 *      г) Загрузите первый пост и выведите его теги.
 *      д) Загрузите тег "java" и выведите связанные с ним посты.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Теги поста "Введение в Hibernate": [java, hibernate]
 *   Посты с тегом "java": [Введение в Hibernate, Spring Boot быстрый старт]
 *
 * ПОДСКАЗКА:
 *   Синхронизируйте обе стороны:
 *     post.getTags().add(tag);
 *     tag.getPosts().add(post);
 *   Не используйте CascadeType.REMOVE или ALL для @ManyToMany —
 *   удаление поста не должно удалять общий тег.
 */

import jakarta.persistence.*;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;

public class Task03 {

    public static void main(String[] args) {
        // Создаем EntityManagerFactory
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:blogdb;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.format_sql", "true");
        config.addAnnotatedClass(Post3.class);
        config.addAnnotatedClass(Tag3.class);

        try (EntityManagerFactory emf = config.buildSessionFactory()) {
            System.out.println("=== @MANYTOMANY (POST — TAG) ===\n");

            // ===== СОЗДАНИЕ ПОСТОВ И ТЕГОВ =====
            System.out.println("--- Создание постов и тегов ---");

            // Создаем посты
            Post3 post1 = new Post3("Введение в Hibernate");
            Post3 post2 = new Post3("Spring Boot быстрый старт");

            // Создаем теги
            Tag3 tagJava = new Tag3("java");
            Tag3 tagHibernate = new Tag3("hibernate");
            Tag3 tagSpring = new Tag3("spring");

            // ===== УСТАНОВКА СВЯЗЕЙ =====
            System.out.println("\n--- Установка связей ---");

            // Тег "java" - оба поста
            post1.addTag(tagJava);
            post2.addTag(tagJava);
            System.out.println("   Тег 'java' добавлен к обоим постам");

            // Тег "hibernate" - первый пост
            post1.addTag(tagHibernate);
            System.out.println("   Тег 'hibernate' добавлен к посту 1");

            // Тег "spring" - второй пост
            post2.addTag(tagSpring);
            System.out.println("   Тег 'spring' добавлен к посту 2");

            // ===== СОХРАНЕНИЕ =====
            System.out.println("\n--- Сохранение (каскад сохранит теги) ---");

            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();

                // Сохраняем посты (каскад PERSIST сохранит теги)
                em.persist(post1);
                em.persist(post2);

                em.getTransaction().commit();
                System.out.println("   ✅ Посты и теги сохранены");
            }

            // ===== ЗАГРУЗКА ПОСТА И ЕГО ТЕГОВ =====
            System.out.println("\n--- Загрузка поста 1 и его тегов ---");

            try (EntityManager em = emf.createEntityManager()) {
                Post3 loadedPost = em.createQuery(
                                "SELECT DISTINCT p FROM Post3 p LEFT JOIN FETCH p.tags WHERE p.id = :id",
                                Post3.class)
                        .setParameter("id", 1L)
                        .getSingleResult();

                System.out.println("   Пост: " + loadedPost.getTitle());
                System.out.print("   Теги: [");
                Set<Tag3> tags = loadedPost.getTags();
                int i = 0;
                for (Tag3 tag : tags) {
                    System.out.print(tag.getName());
                    if (i < tags.size() - 1) System.out.print(", ");
                    i++;
                }
                System.out.println("]");
            }

            // ===== ЗАГРУЗКА ТЕГА И ЕГО ПОСТОВ =====
            System.out.println("\n--- Загрузка тега 'java' и его постов ---");

            try (EntityManager em = emf.createEntityManager()) {
                Tag3 loadedTag = em.createQuery(
                                "SELECT DISTINCT t FROM Tag3 t LEFT JOIN FETCH t.posts WHERE t.name = :name",
                                Tag3.class)
                        .setParameter("name", "java")
                        .getSingleResult();

                System.out.println("   Тег: " + loadedTag.getName());
                System.out.print("   Посты: [");
                Set<Post3> posts = loadedTag.getPosts();
                int i = 0;
                for (Post3 post : posts) {
                    System.out.print(post.getTitle());
                    if (i < posts.size() - 1) System.out.print(", ");
                    i++;
                }
                System.out.println("]");
            }

            // ===== СТАТИСТИКА =====
            System.out.println("\n--- Статистика ---");

            try (EntityManager em = emf.createEntityManager()) {
                Long postCount = em.createQuery("SELECT COUNT(p) FROM Post3 p", Long.class)
                        .getSingleResult();
                Long tagCount = em.createQuery("SELECT COUNT(t) FROM Tag3 t", Long.class)
                        .getSingleResult();

                System.out.println("   Всего постов: " + postCount);
                System.out.println("   Всего тегов: " + tagCount);

                // Связи между постами и тегами
                @SuppressWarnings("unchecked")
                java.util.List<Object[]> relations = em.createQuery(
                                "SELECT p.title, t.name FROM Post3 p JOIN p.tags t ORDER BY p.id, t.id")
                        .getResultList();

                System.out.println("\n   Связи:");
                for (Object[] relation : relations) {
                    System.out.println("     - " + relation[0] + " -> " + relation[1]);
                }
            }

            // ===== ДОПОЛНИТЕЛЬНАЯ ДЕМОНСТРАЦИЯ =====
            System.out.println("\n--- Дополнительно: все теги и их посты ---");

            try (EntityManager em = emf.createEntityManager()) {
                java.util.List<Tag3> allTags = em.createQuery(
                                "SELECT DISTINCT t FROM Tag3 t LEFT JOIN FETCH t.posts",
                                Tag3.class)
                        .getResultList();

                for (Tag3 tag : allTags) {
                    System.out.println("\n   Тег: " + tag.getName());
                    System.out.print("   Посты: [");
                    Set<Post3> posts = tag.getPosts();
                    int i = 0;
                    for (Post3 post : posts) {
                        System.out.print(post.getTitle());
                        if (i < posts.size() - 1) System.out.print(", ");
                        i++;
                    }
                    System.out.println("]");
                }
            }

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Программа завершена");
    }
}
