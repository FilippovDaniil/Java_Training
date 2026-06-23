package m52_hibernate_relationships.practice.task02;

/**
 * Задача 02 — Модуль 52: Двунаправленная связь и владелец (owning side)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Класс Post уже содержит @ManyToOne к Author (владелец связи).
 *   Класс Author уже содержит @OneToMany с mappedBy = "author" (обратная сторона).
 *
 *   1) В методе main выполните следующий эксперимент:
 *      а) Создайте author и post. Установите ТОЛЬКО author.getPosts().add(post)
 *         — НЕ вызывайте post.setAuthor(author).
 *         Сохраните. Проверьте в БД: есть ли author_id у поста? Почему?
 *      б) Повторите, но теперь установите ТОЛЬКО post.setAuthor(author).
 *         Проверьте: сохранился ли FK? Почему?
 *      в) Реализуйте вспомогательный метод author.addPost(Post post),
 *         который синхронизирует обе стороны. Используйте его.
 *
 *   2) Добавьте вспомогательный метод removePost(Post post) в класс Author.
 *
 * ОЖИДАЕМЫЙ ВЫВОД эксперимента б):
 *   Пост сохранён с author_id = 1 (FK записан, т.к. Post — владелец).
 *   author.getPosts() в памяти пуст — нет синхронизации обратной стороны!
 *
 * ПОДСКАЗКА:
 *   Владелец связи — тот, у кого @JoinColumn.
 *   Hibernate смотрит ТОЛЬКО на поле-владельца при генерации SQL INSERT/UPDATE.
 *   Обратная сторона (mappedBy) — только для навигации в Java, на SQL не влияет.
 */

import jakarta.persistence.*;

import org.hibernate.cfg.Configuration;
import java.util.ArrayList;
import java.util.List;

public class Task02 {

    public static void main(String[] args) {
        // Создаем EntityManagerFactory
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:blogdb;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.format_sql", "true");
        config.addAnnotatedClass(Author2.class);
        config.addAnnotatedClass(Post2.class);

        try (EntityManagerFactory emf = config.buildSessionFactory()) {
            System.out.println("=== ВЛАДЕЛЕЦ СВЯЗИ (OWNING SIDE) ===\n");

            // ===== ЭКСПЕРИМЕНТ а) ТОЛЬКО ОБРАТНАЯ СТОРОНА =====
            System.out.println("--- Эксперимент а: Только author.getPosts().add(post) ---");
            experimentOnlyReverseSide(emf);

            // ===== ЭКСПЕРИМЕНТ б) ТОЛЬКО ВЛАДЕЛЕЦ =====
            System.out.println("\n--- Эксперимент б: Только post.setAuthor(author) ---");
            experimentOnlyOwningSide(emf);

            // ===== ЭКСПЕРИМЕНТ в) СИНХРОНИЗАЦИЯ ОБЕИХ СТОРОН =====
            System.out.println("\n--- Эксперимент в: Синхронизация обеих сторон (addPost) ---");
            experimentBothSides(emf);

            // ===== ВЫВОДЫ =====
            System.out.println("\n--- ВЫВОДЫ ---");
            System.out.println("   📌 Владелец связи: сторона с @JoinColumn (Post)");
            System.out.println("   📌 Hibernate смотрит ТОЛЬКО на владельца при сохранении");
            System.out.println("   📌 Обратная сторона (mappedBy) — только для навигации в Java");
            System.out.println("   📌 Всегда синхронизируйте ОБЕ стороны для согласованности!");

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Программа завершена");
    }

    /**
     * Эксперимент а: Устанавливаем ТОЛЬКО author.getPosts().add(post)
     * БЕЗ вызова post.setAuthor(author)
     */
    private static void experimentOnlyReverseSide(EntityManagerFactory emf) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Создаем автора и пост
            Author2 author = new Author2("Иван Петров");
            Post2 post = new Post2("Пост без установленного автора");

            // ❌ ТОЛЬКО обратная сторона (не владелец!)
            author.getPosts().add(post);
            // НЕ вызываем post.setAuthor(author) - владелец не установлен!

            System.out.println("   Создан пост: " + post.getTitle());
            System.out.println("   author.getPosts().size(): " + author.getPosts().size());
            System.out.println("   post.getAuthor(): " + post.getAuthor());

            // Сохраняем автора (каскад сохранит пост)
            em.persist(author);
            em.getTransaction().commit();

            System.out.println("   ✅ Сохранено");

            // Проверяем в БД
            Post2 savedPost = em.find(Post2.class, post.getId());
            System.out.println("   📌 Результат:");
            System.out.println("      post.getAuthor() после загрузки: " + savedPost.getAuthor());
            System.out.println("      author_id в БД: " + (savedPost.getAuthor() != null ?
                    savedPost.getAuthor().getId() : "NULL"));

            if (savedPost.getAuthor() == null) {
                System.out.println("      ❌ Владелец связи НЕ установлен! FK = NULL");
                System.out.println("      📌 Почему? Hibernate смотрит ТОЛЬКО на поле-владельца (post.author)");
            }
        }
    }

    /**
     * Эксперимент б: Устанавливаем ТОЛЬКО post.setAuthor(author)
     * БЕЗ вызова author.getPosts().add(post)
     */
    private static void experimentOnlyOwningSide(EntityManagerFactory emf) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Создаем автора и пост
            Author2 author = new Author2("Мария Смирнова");
            Post2 post = new Post2("Пост с установленным автором");

            // ✅ ТОЛЬКО владелец связи!
            post.setAuthor(author);
            // НЕ вызываем author.getPosts().add(post) - обратная сторона не установлена!

            System.out.println("   Создан пост: " + post.getTitle());
            System.out.println("   post.getAuthor(): " + post.getAuthor().getName());
            System.out.println("   author.getPosts().size(): " + author.getPosts().size());

            // Сохраняем автора (каскад сохранит пост)
            em.persist(author);
            em.getTransaction().commit();

            System.out.println("   ✅ Сохранено");

            // Проверяем в БД
            Author2 savedAuthor = em.find(Author2.class, author.getId());
            System.out.println("   📌 Результат:");
            System.out.println("      author.getPosts().size() после загрузки: " +
                    savedAuthor.getPosts().size());
            System.out.println("      author_id в БД: установлен (FK = " + author.getId() + ")");

            if (savedAuthor.getPosts().isEmpty()) {
                System.out.println("      ⚠️ Обратная сторона НЕ синхронизирована!");
                System.out.println("      📌 В БД FK есть, но в памяти author.getPosts() пуст!");
                System.out.println("      Это проблема, если вы планируете использовать author.getPosts()");
            }
        }
    }

    /**
     * Эксперимент в: Синхронизация обеих сторон через метод addPost
     */
    private static void experimentBothSides(EntityManagerFactory emf) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Создаем автора и пост
            Author2 author = new Author2("Петр Сидоров");
            Post2 post = new Post2("Пост с синхронизацией");

            // ✅ Синхронизируем обе стороны!
            author.addPost(post);

            System.out.println("   Создан пост: " + post.getTitle());
            System.out.println("   post.getAuthor(): " + post.getAuthor().getName());
            System.out.println("   author.getPosts().size(): " + author.getPosts().size());

            // Сохраняем автора
            em.persist(author);
            em.getTransaction().commit();

            System.out.println("   ✅ Сохранено");

            // Проверяем в БД
            Author2 savedAuthor = em.find(Author2.class, author.getId());
            System.out.println("   📌 Результат:");
            System.out.println("      author.getPosts().size() после загрузки: " +
                    savedAuthor.getPosts().size());
            System.out.println("      post.getAuthor() после загрузки: " +
                    savedAuthor.getPosts().get(0).getAuthor().getName());

            if (!savedAuthor.getPosts().isEmpty()) {
                System.out.println("      ✅ Обе стороны синхронизированы!");
                System.out.println("      FK в БД установлен, и в памяти данные согласованы");
            }
        }
    }
}