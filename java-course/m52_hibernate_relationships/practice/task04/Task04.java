package m52_hibernate_relationships.practice.task04;

/**
 * Задача 04 — Модуль 52: @OneToOne (Author — UserProfile)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Каждый автор имеет ровно один профиль (UserProfile4).
 *
 *   1) Разметьте связь на стороне Author4:
 *      - @OneToOne с cascade = CascadeType.ALL и orphanRemoval = true
 *      - @JoinColumn(name = "profile_id", unique = true)
 *      - fetch = FetchType.LAZY
 *   2) Разметьте обратную сторону на UserProfile4:
 *      - @OneToOne(mappedBy = "profile", fetch = FetchType.LAZY)
 *   3) В методе main:
 *      а) Создайте Author4 с UserProfile4 (bio, avatarUrl).
 *      б) Сохраните автора — профиль должен сохраниться каскадно.
 *      в) Загрузите профиль по id. Обратитесь к profile.getAuthor() — выведите имя.
 *      г) Удалите профиль у автора (author.setProfile(null)) и сохраните —
 *         проверьте, что orphanRemoval = true удалил запись из user_profiles.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Автор профиля: Иван Петров
 *   После удаления профиля: profile IS NULL in DB
 *
 * ПОДСКАЗКА:
 *   Для двунаправленного @OneToOne важно синхронизировать обе стороны:
 *     author.setProfile(profile);
 *     profile.setAuthor(author);
 *   Иначе profile.getAuthor() вернёт null до перезагрузки из БД.
 *
 *   Чтобы убедиться в удалении через orphanRemoval, выполните em.flush()
 *   и повторно загрузите запись через em.find(UserProfile4.class, id).
 */

import jakarta.persistence.*;
import org.hibernate.cfg.Configuration;

public class Task04 {

    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:blogdb;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.format_sql", "true");
        config.addAnnotatedClass(Author4.class);
        config.addAnnotatedClass(UserProfile4.class);

        try (EntityManagerFactory emf = config.buildSessionFactory()) {
            System.out.println("=== @ONETOONE (AUTHOR — USERPROFILE) ===\n");

            // ===== 1. СОЗДАНИЕ АВТОРА С ПРОФИЛЕМ =====
            System.out.println("--- 1. Создание автора с профилем ---");

            Long authorId = createAuthorWithProfile(emf);

            // ===== 2. ЗАГРУЗКА ПРОФИЛЯ И ПОЛУЧЕНИЕ АВТОРА =====
            System.out.println("\n--- 2. Загрузка профиля и получение автора ---");
            loadProfileAndGetAuthor(emf);

            // ===== 3. УДАЛЕНИЕ ПРОФИЛЯ ЧЕРЕЗ ORPHAN REMOVAL =====
            System.out.println("\n--- 3. Удаление профиля (orphanRemoval) ---");
            deleteProfileAndVerify(emf, authorId);

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Программа завершена");
    }

    private static Long createAuthorWithProfile(EntityManagerFactory emf) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Author4 author = new Author4("Иван Петров");
            UserProfile4 profile = new UserProfile4(
                    "Опытный разработчик с 10-летним стажем",
                    "https://example.com/avatar/ivan.jpg"
            );

            // ✅ Устанавливаем связь вручную (без рекурсии)
            author.setProfile(profile);
            profile.setAuthor(author);

            em.persist(author);
            em.getTransaction().commit();

            System.out.println("   ✅ Сохранен автор: " + author.getName());
            System.out.println("   ✅ Сохранен профиль: bio=" + profile.getBio());

            return author.getId();
        }
    }

    private static void loadProfileAndGetAuthor(EntityManagerFactory emf) {
        try (EntityManager em = emf.createEntityManager()) {
            UserProfile4 profile = em.createQuery(
                            "SELECT p FROM UserProfile4 p WHERE p.id = 1",
                            UserProfile4.class)
                    .getSingleResult();

            System.out.println("   Профиль найден:");
            System.out.println("   - bio: " + profile.getBio());
            System.out.println("   - avatarUrl: " + profile.getAvatarUrl());

            Author4 author = profile.getAuthor();
            if (author != null) {
                System.out.println("   👤 Автор профиля: " + author.getName());
            }
        }
    }

    private static void deleteProfileAndVerify(EntityManagerFactory emf, Long authorId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Author4 author = em.find(Author4.class, authorId);
            System.out.println("   Автор до удаления профиля: " + author.getName());

            Long profileId = author.getProfile() != null ? author.getProfile().getId() : null;

            // ✅ Удаляем профиль
            author.setProfile(null);

            em.merge(author);
            em.getTransaction().commit();
            System.out.println("   ✅ Профиль удален через orphanRemoval");

            if (profileId != null) {
                UserProfile4 deletedProfile = em.find(UserProfile4.class, profileId);
                if (deletedProfile == null) {
                    System.out.println("   ✅ Профиль с id=" + profileId + " удален из БД");
                }
            }

            Author4 reloaded = em.find(Author4.class, authorId);
            System.out.println("   Автор после удаления профиля: " + reloaded.getName());
            System.out.println("   profile: " + (reloaded.getProfile() == null ? "NULL ✅" : "есть ❌"));
        }
    }
}