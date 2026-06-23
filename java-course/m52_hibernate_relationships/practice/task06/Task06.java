package m52_hibernate_relationships.practice.task06;

/**
 * Задача 06 — Модуль 52: FetchType LAZY vs EAGER и проблема N+1
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Включите логирование SQL. В Hibernate это делается через свойство
 *      "hibernate.show_sql" = "true" при создании SessionFactory.
 *      Добавьте также "hibernate.format_sql" = "true".
 *
 *   2) Сценарий N+1 (воспроизвести и посчитать запросы):
 *      а) Сохраните 5 авторов, у каждого по 3 поста.
 *      б) Выполните JPQL: "SELECT a FROM Author6 a"  — загрузит 5 авторов.
 *      в) В цикле для каждого автора вызовите author.getPosts().size().
 *         Посчитайте SQL-запросы в консоли. Сколько их?
 *
 *   3) Решение через JOIN FETCH:
 *      а) Замените запрос на:
 *           "SELECT DISTINCT a FROM Author6 a JOIN FETCH a.posts"
 *         Снова посчитайте SQL-запросы.
 *      б) Объясните в комментарии разницу: сколько запросов без JOIN FETCH
 *         и сколько с JOIN FETCH при 5 авторах?
 *
 *   4) Сценарий EAGER (показать проблему загрузки):
 *      Временно измените @ManyToOne на авторе комментария на FetchType.EAGER.
 *      Загрузите все комментарии: "SELECT c FROM Comment6 c".
 *      Обратите внимание на количество JOIN в одном SELECT.
 *      Объясните в комментарии: чем EAGER опасен при большом графе объектов?
 *
 * ОЖИДАЕМЫЙ ПОДСЧЁТ (в комментарии):
 *   Без JOIN FETCH:  1 + 5 = 6 SQL-запросов (N+1)
 *   С JOIN FETCH:    1 SQL-запрос
 *
 * ПОДСКАЗКА:
 *   DISTINCT в JPQL нужен, чтобы убрать дублирование авторов (из-за JOIN).
 *   Hibernate 6 добавляет DISTINCT автоматически при JOIN FETCH в большинстве случаев,
 *   но явный DISTINCT — лучшая практика.
 */

import jakarta.persistence.*;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Task06 {

    public static void main(String[] args) {
        // Создаем EMF с включенным логированием SQL
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:blogdb;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.format_sql", "true");
        config.addAnnotatedClass(Author6.class);
        config.addAnnotatedClass(Post6.class);
        config.addAnnotatedClass(Comment6.class);

        try (EntityManagerFactory emf = config.buildSessionFactory()) {
            System.out.println("=== FETCHTYPE: LAZY vs EAGER и проблема N+1 ===\n");

            // ============================================
            // ПОДГОТОВКА: 5 авторов, у каждого по 3 поста
            // ============================================
            System.out.println("--- ПОДГОТОВКА: Сохранение 5 авторов с постами ---");

            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();

                for (int i = 1; i <= 5; i++) {
                    Author6 author = new Author6("Автор " + i);
                    for (int j = 1; j <= 3; j++) {
                        Post6 post = new Post6("Пост " + i + "-" + j);
                        author.addPost(post);

                        // Добавляем комментарии к посту для сценария EAGER
                        post.getComments().add(new Comment6("Комментарий к посту " + i + "-" + j));
                    }
                    em.persist(author);
                }

                em.getTransaction().commit();
                System.out.println("   ✅ Сохранено 5 авторов, у каждого по 3 поста\n");
            }

            // ============================================
            // СЦЕНАРИЙ N+1 (LAZY)
            // ============================================
            System.out.println("--- СЦЕНАРИЙ 1: N+1 проблема (LAZY) ---");
            System.out.println("   Запрос: SELECT a FROM Author6 a");
            System.out.println("   Затем для каждого автора вызываем getPosts().size()");
            System.out.println("   Ожидаемое количество SQL-запросов: 1 + 5 = 6");

            System.out.println("\n   📊 Подсчет SQL-запросов в логах выше:");

            try (EntityManager em = emf.createEntityManager()) {
                // 1 запрос на всех авторов
                List<Author6> authors = em.createQuery("SELECT a FROM Author6 a", Author6.class)
                        .getResultList();

                // + 5 запросов на загрузку постов каждого автора (N+1)
                System.out.println("\n   Кол-во авторов: " + authors.size());
                for (Author6 author : authors) {
                    int postCount = author.getPosts().size();
                    System.out.println("   Автор: " + author.getName() + ", постов: " + postCount);
                }
            }

            // ============================================
            // РЕШЕНИЕ: JOIN FETCH
            // ============================================
            System.out.println("\n--- СЦЕНАРИЙ 2: Решение через JOIN FETCH ---");
            System.out.println("   Запрос: SELECT DISTINCT a FROM Author6 a JOIN FETCH a.posts");
            System.out.println("   Ожидаемое количество SQL-запросов: 1");

            try (EntityManager em = emf.createEntityManager()) {
                // 1 запрос на всех авторов с постами
                List<Author6> authors = em.createQuery(
                                "SELECT DISTINCT a FROM Author6 a JOIN FETCH a.posts",
                                Author6.class)
                        .getResultList();

                System.out.println("\n   Кол-во авторов: " + authors.size());
                for (Author6 author : authors) {
                    int postCount = author.getPosts().size();
                    System.out.println("   Автор: " + author.getName() + ", постов: " + postCount);
                }
            }

            // ============================================
            // СЦЕНАРИЙ EAGER
            // ============================================
            System.out.println("\n--- СЦЕНАРИЙ 3: Проблема EAGER ---");
            System.out.println("   Временно измените @ManyToOne на Comment6.post");
            System.out.println("   на FetchType.EAGER и раскомментируйте код ниже");
            System.out.println("   Опасность: при большом графе объектов EAGER");
            System.out.println("   порождает сложные JOIN-запросы");

            /*
            // Раскомментировать после изменения FetchType на EAGER
            try (EntityManager em = emf.createEntityManager()) {
                System.out.println("\n   Запрос: SELECT c FROM Comment6 c");
                List<Comment6> comments = em.createQuery("SELECT c FROM Comment6 c", Comment6.class)
                        .getResultList();
                System.out.println("   Кол-во комментариев: " + comments.size());
                System.out.println("\n   Обратите внимание на SQL в логах:");
                System.out.println("   - EAGER создает JOIN с таблицами post и author");
                System.out.println("   - При большом количестве связей - сложный запрос");
            }
            */

            // ============================================
            // ОБЪЯСНЕНИЕ
            // ============================================
            System.out.println("\n--- ОБЪЯСНЕНИЕ ---");
            System.out.println("\n   📌 Проблема N+1:");
            System.out.println("   - Без JOIN FETCH: 1 запрос на авторов + 5 запросов на посты");
            System.out.println("   - При 5 авторах: 6 запросов");
            System.out.println("   - При N авторах: 1 + N запросов");

            System.out.println("\n   📌 Решение с JOIN FETCH:");
            System.out.println("   - Загружаем авторов с постами одним запросом");
            System.out.println("   - При любом количестве авторов: 1 запрос");
            System.out.println("   - DISTINCT убирает дублирование из-за JOIN");

            System.out.println("\n   📌 Опасность EAGER:");
            System.out.println("   - Загружает все связанные сущности сразу");
            System.out.println("   - При большой вложенности порождает сложные запросы");
            System.out.println("   - Может загружать данные, которые не нужны");
            System.out.println("   - Рекомендуется использовать LAZY + JOIN FETCH");

            // ============================================
            // ДЕМОНСТРАЦИЯ: EXPLAIN PLAN
            // ============================================
            System.out.println("\n--- СРАВНЕНИЕ ЗАПРОСОВ ---");
            System.out.println("\n   LAZY (N+1):");
            System.out.println("   SELECT * FROM authors");
            System.out.println("   SELECT * FROM posts WHERE author_id = 1");
            System.out.println("   SELECT * FROM posts WHERE author_id = 2");
            System.out.println("   SELECT * FROM posts WHERE author_id = 3");
            System.out.println("   SELECT * FROM posts WHERE author_id = 4");
            System.out.println("   SELECT * FROM posts WHERE author_id = 5");
            System.out.println("   Всего: 6 запросов ❌");

            System.out.println("\n   JOIN FETCH:");
            System.out.println("   SELECT DISTINCT a.*, p.* FROM authors a");
            System.out.println("   LEFT JOIN posts p ON a.id = p.author_id");
            System.out.println("   Всего: 1 запрос ✅");

            System.out.println("\n   💡 Рекомендации:");
            System.out.println("   - Используйте LAZY по умолчанию");
            System.out.println("   - Используйте JOIN FETCH для загрузки связанных данных");
            System.out.println("   - Добавляйте DISTINCT при JOIN FETCH для уникальности");
            System.out.println("   - EAGER используйте только если точно нужно");

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Программа завершена");
    }
}
