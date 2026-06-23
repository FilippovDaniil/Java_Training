package m52_hibernate_relationships.practice.task07;

/**
 * Задача 07 — Модуль 52: МИНИ-ПРОЕКТ — полная доменная модель блога
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Перед вами каркасы всех сущностей блога: Author7, Post7, Comment7, Tag7, UserProfile7.
 *   Все связи помечены TODO-аннотациями. Ваша задача:
 *
 *   1) РАЗМЕТИТЬ ВСЕ СВЯЗИ — заменить TODO-комментарии на правильные аннотации JPA:
 *      - Author7 ──1:N──► Post7      (@OneToMany mappedBy + @ManyToOne @JoinColumn)
 *      - Post7   ──1:N──► Comment7   (@OneToMany mappedBy + @ManyToOne @JoinColumn)
 *      - Post7   ──N:M──► Tag7       (@ManyToMany @JoinTable / mappedBy)
 *      - Author7 ──1:1──► UserProfile7 (@OneToOne @JoinColumn / mappedBy)
 *
 *   2) НАСТРОИТЬ CASCADE и FETCH:
 *      - Author7.posts    → cascade ALL, orphanRemoval, LAZY
 *      - Post7.comments   → cascade ALL, orphanRemoval, LAZY
 *      - Post7.tags       → cascade PERSIST+MERGE (не ALL!), LAZY
 *      - Author7.profile  → cascade ALL, orphanRemoval, LAZY
 *
 *   3) РЕАЛИЗОВАТЬ BlogRepository — класс с методами:
 *      а) save(Author7 author)               — persist в транзакции
 *      б) findById(Long id)                  — em.find(Author7.class, id)
 *      в) findAuthorsWithPosts()             — JOIN FETCH a.posts (без N+1!)
 *      г) findPostsWithTagsAndComments(Long authorId)
 *                                            — JOIN FETCH p.tags, JOIN FETCH p.comments
 *
 *   4) В методе main создайте полный сценарий:
 *      - 2 автора, у каждого профиль
 *      - у первого автора 3 поста, у второго 2 поста
 *      - 4 тега, распределить по постам (некоторые теги — общие)
 *      - у каждого поста 2–3 комментария
 *      - вызвать BlogRepository.findAuthorsWithPosts(), вывести всё дерево
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   === Авторы с постами ===
 *   Анна Смирнова [anna@blog.ru]
 *     POST: "Начало работы с Hibernate"  [теги: java, hibernate]
 *       COMMENT: "Спасибо, очень понятно!"
 *       COMMENT: "Когда будет продолжение?"
 *     POST: "Spring Data JPA — просто"  [теги: java, spring]
 *       COMMENT: "Отличная статья"
 *   Иван Козлов [ivan@blog.ru]
 *     POST: "Микросервисы с нуля"  [теги: spring, architecture]
 *       COMMENT: "А как с мониторингом?"
 *
 * ПОДСКАЗКА:
 *   Для findPostsWithTagsAndComments используйте два отдельных JOIN FETCH
 *   или @EntityGraph — нельзя сделать два JOIN FETCH одновременно
 *   на разные коллекции в одном запросе (HibernateException: cannot simultaneously fetch).
 *   Решение: загрузить посты с тегами, потом отдельно с комментариями,
 *   или использовать @BatchSize для одной из коллекций.
 */

import jakarta.persistence.*;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class Task07 {

    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:blogdb;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.format_sql", "true");
        config.addAnnotatedClass(Author7.class);
        config.addAnnotatedClass(UserProfile7.class);
        config.addAnnotatedClass(Post7.class);
        config.addAnnotatedClass(Comment7.class);
        config.addAnnotatedClass(Tag7.class);

        try (EntityManagerFactory emf = config.buildSessionFactory()) {
            System.out.println("=== ПОЛНАЯ ДОМЕННАЯ МОДЕЛЬ БЛОГА ===\n");

            BlogRepository repo = new BlogRepository(emf);

            // ============================================
            // СОЗДАНИЕ ДАННЫХ
            // ============================================
            System.out.println("--- СОЗДАНИЕ АВТОРОВ, ПОСТОВ, ТЕГОВ, КОММЕНТАРИЕВ ---\n");

            // Создаём теги и сохраняем их отдельно
            Tag7 tagJava = new Tag7("java");
            Tag7 tagHibernate = new Tag7("hibernate");
            Tag7 tagSpring = new Tag7("spring");
            Tag7 tagArchitecture = new Tag7("architecture");

            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                em.persist(tagJava);
                em.persist(tagHibernate);
                em.persist(tagSpring);
                em.persist(tagArchitecture);
                em.getTransaction().commit();
                System.out.println("   ✅ Теги сохранены");
            }

            // Создаём авторов с профилями
            Author7 anna = new Author7("Анна Смирнова", "anna@blog.ru");
            UserProfile7 annaProfile = new UserProfile7(
                    "Java-разработчик с 10-летним стажем, специалист по Hibernate",
                    "https://example.com/anna.jpg"
            );
            anna.setProfile(annaProfile);

            Author7 ivan = new Author7("Иван Козлов", "ivan@blog.ru");
            UserProfile7 ivanProfile = new UserProfile7(
                    "Архитектор микросервисных систем",
                    "https://example.com/ivan.jpg"
            );
            ivan.setProfile(ivanProfile);

            // Посты для Анны
            Post7 post1 = new Post7("Начало работы с Hibernate", "Подробное руководство по Hibernate для начинающих");
            post1.addComment(new Comment7("Спасибо, очень понятно!"));
            post1.addComment(new Comment7("Когда будет продолжение?"));
            post1.addComment(new Comment7("Отличная статья!"));
            post1.addTag(tagJava);
            post1.addTag(tagHibernate);

            Post7 post2 = new Post7("Spring Data JPA — просто", "Работа с Spring Data JPA");
            post2.addComment(new Comment7("Отличная статья"));
            post2.addComment(new Comment7("А как быть с ленивой загрузкой?"));
            post2.addTag(tagJava);
            post2.addTag(tagSpring);

            Post7 post3 = new Post7("Hibernate Performance Tips", "Оптимизация производительности Hibernate");
            post3.addComment(new Comment7("Очень полезные советы!"));
            post3.addTag(tagHibernate);
            post3.addTag(tagArchitecture);

            anna.addPost(post1);
            anna.addPost(post2);
            anna.addPost(post3);

            // Посты для Ивана
            Post7 post4 = new Post7("Микросервисы с нуля", "Пошаговое руководство по микросервисной архитектуре");
            post4.addComment(new Comment7("А как с мониторингом?"));
            post4.addComment(new Comment7("Хороший материал!"));
            post4.addTag(tagSpring);
            post4.addTag(tagArchitecture);

            Post7 post5 = new Post7("Архитектурные паттерны", "Обзор архитектурных паттернов");
            post5.addComment(new Comment7("Где можно применить на практике?"));
            post5.addTag(tagArchitecture);

            ivan.addPost(post4);
            ivan.addPost(post5);

            // Сохраняем авторов (merge обработает всё дерево)
            System.out.println("   Сохранение авторов...");
            repo.save(anna);
            repo.save(ivan);
            System.out.println("   ✅ Авторы сохранены\n");

            // ============================================
            // ЗАПРОС: АВТОРЫ С ПОСТАМИ, ТЕГАМИ, КОММЕНТАРИЯМИ
            // ============================================
            System.out.println("--- ЗАПРОС: Авторы с постами (JOIN FETCH + отдельные запросы) ---\n");

            List<Author7> authors = repo.findAuthorsWithPosts();
            printAuthorsWithPosts(authors);

            // ============================================
            // ПОИСК ПОСТОВ С ТЕГАМИ И КОММЕНТАРИЯМИ ДЛЯ АВТОРА
            // ============================================
            System.out.println("\n--- ПОСТЫ АННЫ С ТЕГАМИ И КОММЕНТАРИЯМИ ---\n");

            List<Post7> posts = repo.findPostsWithTagsAndComments(anna.getId());
            printPostsWithTagsAndComments(posts);

            // ============================================
            // СТАТИСТИКА
            // ============================================
            System.out.println("\n--- СТАТИСТИКА ---");
            try (EntityManager em = emf.createEntityManager()) {
                Long authorCount = em.createQuery("SELECT COUNT(a) FROM Author7 a", Long.class)
                        .getSingleResult();
                Long postCount = em.createQuery("SELECT COUNT(p) FROM Post7 p", Long.class)
                        .getSingleResult();
                Long commentCount = em.createQuery("SELECT COUNT(c) FROM Comment7 c", Long.class)
                        .getSingleResult();
                Long tagCount = em.createQuery("SELECT COUNT(t) FROM Tag7 t", Long.class)
                        .getSingleResult();

                System.out.println("   Всего авторов: " + authorCount);
                System.out.println("   Всего постов: " + postCount);
                System.out.println("   Всего комментариев: " + commentCount);
                System.out.println("   Всего тегов: " + tagCount);
            }

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Программа завершена");
    }

    private static void printAuthorsWithPosts(List<Author7> authors) {
        for (Author7 author : authors) {
            System.out.printf("   %s [%s]%n", author.getName(), author.getEmail());
            if (author.getProfile() != null) {
                System.out.printf("      BIO: %s%n", author.getProfile().getBio());
            }
            for (Post7 post : author.getPosts()) {
                System.out.printf("      ПОСТ: \"%s\" [теги: ", post.getTitle());
                List<String> tagNames = post.getTags().stream()
                        .map(Tag7::getName)
                        .sorted()
                        .toList();
                System.out.print(String.join(", ", tagNames));
                System.out.println("]");
                for (Comment7 comment : post.getComments()) {
                    System.out.printf("         КОММЕНТАРИЙ: \"%s\"%n", comment.getText());
                }
            }
            System.out.println();
        }
    }

    private static void printPostsWithTagsAndComments(List<Post7> posts) {
        for (Post7 post : posts) {
            System.out.printf("   ПОСТ: \"%s\"%n", post.getTitle());
            System.out.print("      Теги: ");
            List<String> tagNames = post.getTags().stream()
                    .map(Tag7::getName)
                    .sorted()
                    .toList();
            System.out.println(String.join(", ", tagNames));
            System.out.println("      Комментарии:");
            for (Comment7 comment : post.getComments()) {
                System.out.printf("         - \"%s\"%n", comment.getText());
            }
        }
    }
}
