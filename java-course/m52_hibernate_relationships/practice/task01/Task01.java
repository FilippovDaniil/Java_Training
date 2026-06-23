package m52_hibernate_relationships.practice.task01;

/**
 * Задача 01 — Модуль 52: @ManyToOne и @OneToMany (Author — Post)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Разметьте класс Post аннотацией @ManyToOne, указав FetchType.LAZY.
 *      Добавьте @JoinColumn(name = "author_id") — это внешний ключ в таблице posts.
 *   2) Разметьте класс Author аннотацией @OneToMany.
 *      Укажите mappedBy = "author" — Hibernate узнает о владельце связи.
 *   3) В методе main создайте EntityManagerFactory (H2 in-memory),
 *      сохраните одного автора с двумя постами и снова загрузите из БД.
 *      Выведите: имя автора и заголовки его постов.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Автор: Иван Петров
 *   Посты:
 *     - Введение в Java
 *     - Hibernate за 30 минут
 *
 * ПОДСКАЗКА:
 *   При сохранении обязательно устанавливайте обе стороны связи:
 *     post.setAuthor(author);
 *     author.getPosts().add(post);
 *   Использовать em.persist(author) достаточно, если cascade = CascadeType.ALL.
 */

import jakarta.persistence.*;
import m51_hibernate_orm.practice.task07.PostRepository;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Task01 {

    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:blogdb;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.format_sql", "true");
        config.addAnnotatedClass(Author.class);
        config.addAnnotatedClass(Post.class);

        try (EntityManagerFactory emf = config.buildSessionFactory()) {
            System.out.println("=== РАСШИРЕННАЯ ДЕМОНСТРАЦИЯ СВЯЗЕЙ ===\n");

            // ===== 1. СОХРАНЕНИЕ АВТОРА С ПОСТАМИ =====
            System.out.println("--- 1. Сохранение автора с постами ---");

            Author author = new Author("Иван Петров", "ivan@example.com");
            author.addPost(new Post("Введение в Java", "Основы языка Java"));
            author.addPost(new Post("Hibernate за 30 минут", "Быстрый старт с Hibernate"));

            saveAuthor(emf, author);

            // ===== 2. ЗАГРУЗКА С ПОСТАМИ =====
            System.out.println("\n--- 2. Загрузка автора с постами ---");
            Author loaded = loadAuthorWithPosts(emf, 1L);
            printAuthorInfo(loaded);

            // ===== 3. ДОБАВЛЕНИЕ ПОСТА К СУЩЕСТВУЮЩЕМУ АВТОРУ =====
            System.out.println("\n--- 3. Добавление поста к существующему автору ---");
            addPostToAuthor(emf, 1L, "Spring Data JPA", "Работа с JPA");

            // ===== 4. УДАЛЕНИЕ ПОСТА =====
            System.out.println("\n--- 4. Удаление поста ---");
            removePost(emf, 1L, 2L);

            // ===== 5. ОБНОВЛЕНИЕ ПОСТА =====
            System.out.println("\n--- 5. Обновление поста ---");
            updatePost(emf, 1L, "Hibernate за 60 минут", "Расширенный курс Hibernate");

            // ===== 6. ФИНАЛЬНАЯ ПРОВЕРКА =====
            System.out.println("\n--- 6. Финальная проверка ---");
            Author finalAuthor = loadAuthorWithPosts(emf, 1L);
            printAuthorInfo(finalAuthor);

            // ===== 7. ВСЕ АВТОРЫ =====
            System.out.println("\n--- 7. Все авторы и их посты ---");
            List<Author> allAuthors = loadAllAuthorsWithPosts(emf);
            for (Author a : allAuthors) {
                printAuthorInfo(a);
            }

            // ===== 8. УДАЛЕНИЕ АВТОРА (каскадное удаление) =====
            System.out.println("\n--- 8. Удаление автора (каскадное удаление) ---");
            deleteAuthor(emf, 1L);

            // ===== 9. ПРОВЕРКА ПОСЛЕ УДАЛЕНИЯ =====
            System.out.println("\n--- 9. Проверка после удаления ---");
            List<Author> remaining = loadAllAuthorsWithPosts(emf);
            System.out.println("   Осталось авторов: " + remaining.size());

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ===== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ =====

    private static void saveAuthor(EntityManagerFactory emf, Author author) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(author);
            em.getTransaction().commit();
            System.out.println("   ✅ Сохранен автор: " + author.getName());
        }
    }

    private static Author loadAuthorWithPosts(EntityManagerFactory emf, Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery(
                            "SELECT a FROM Author a LEFT JOIN FETCH a.posts WHERE a.id = :id",
                            Author.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    private static void printAuthorInfo(Author author) {
        System.out.println("   Автор: " + author.getName() + " (" + author.getEmail() + ")");
        System.out.println("   Постов: " + author.getPosts().size());
        for (Post post : author.getPosts()) {
            System.out.println("     - " + post.getTitle());
        }
    }

    private static void addPostToAuthor(EntityManagerFactory emf, Long authorId, String title, String content) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Author author = em.find(Author.class, authorId);
            if (author != null) {
                Post newPost = new Post(title, content);
                author.addPost(newPost);
                em.merge(author);
                System.out.println("   ✅ Добавлен пост: " + title);
            }

            em.getTransaction().commit();
        }
    }

    private static void removePost(EntityManagerFactory emf, Long authorId, Long postId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Author author = em.find(Author.class, authorId);
            if (author != null) {
                Post postToRemove = author.getPosts().stream()
                        .filter(p -> p.getId().equals(postId))
                        .findFirst()
                        .orElse(null);

                if (postToRemove != null) {
                    author.removePost(postToRemove);
                    em.merge(author);
                    System.out.println("   ✅ Удален пост с id=" + postId);
                } else {
                    System.out.println("   ⚠️ Пост с id=" + postId + " не найден");
                }
            }

            em.getTransaction().commit();
        }
    }

    private static void updatePost(EntityManagerFactory emf, Long postId, String newTitle, String newContent) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Post post = em.find(Post.class, postId);
            if (post != null) {
                post.setTitle(newTitle);
                post.setContent(newContent);
                em.merge(post);
                System.out.println("   ✅ Обновлен пост: " + newTitle);
            }

            em.getTransaction().commit();
        }
    }

    private static List<Author> loadAllAuthorsWithPosts(EntityManagerFactory emf) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery(
                            "SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.posts",
                            Author.class)
                    .getResultList();
        }
    }

    private static void deleteAuthor(EntityManagerFactory emf, Long authorId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Author author = em.find(Author.class, authorId);
            if (author != null) {
                em.remove(author);
                System.out.println("   ✅ Удален автор: " + author.getName());
                System.out.println("   📌 Посты удалены каскадно (CascadeType.ALL)");
            }

            em.getTransaction().commit();
        }
    }
}
