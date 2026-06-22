package m51_hibernate_orm.practice.task07;

/**
 * Задача 07 — Модуль 51: МИНИ-ПРОЕКТ — PostRepository (CRUD-обёртка)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Реализуйте класс PostRepository — обёртку над EntityManager,
 *   которая инкапсулирует все CRUD-операции для сущности Post7.
 *
 *   Методы для реализации (каркасы уже есть ниже):
 *     save(Post7 post)         — persist + commit; возвращает сохранённый объект с id
 *     findById(Long id)        — find по id; возвращает Optional<Post7>
 *     findAll()                — JPQL "FROM Post7"; возвращает List<Post7>
 *     update(Post7 post)       — merge detached-объекта; возвращает managed-копию
 *     delete(Long id)          — найти по id и удалить через remove()
 *
 *   В main() продемонстрируйте работу всех методов:
 *     1) save — сохраните 3 поста
 *     2) findById — найдите по id
 *     3) findAll — выведите все
 *     4) update — измените заголовок одного поста
 *     5) delete — удалите один пост; убедитесь что findAll() вернёт 2 записи
 *
 * КОНФИГУРАЦИЯ hibernate.cfg.xml (положите в src/main/resources/):
 * ---------------------------------------------------------------
 * <?xml version="1.0"?>
 * <!DOCTYPE hibernate-configuration PUBLIC
 *         "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
 *         "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
 * <hibernate-configuration>
 *     <session-factory>
 *         <property name="hibernate.connection.driver_class">org.h2.Driver</property>
 *         <property name="hibernate.connection.url">jdbc:h2:mem:blogdb;DB_CLOSE_DELAY=-1</property>
 *         <property name="hibernate.connection.username">sa</property>
 *         <property name="hibernate.connection.password"></property>
 *         <property name="hibernate.dialect">org.hibernate.dialect.H2Dialect</property>
 *         <property name="hibernate.hbm2ddl.auto">create-drop</property>
 *         <property name="hibernate.show_sql">true</property>
 *         <property name="hibernate.format_sql">true</property>
 *         <mapping class="Post7"/>
 *     </session-factory>
 * </hibernate-configuration>
 * ---------------------------------------------------------------
 *
 * ПОДСКАЗКА (структура репозитория):
 *   class PostRepository {
 *       private final EntityManagerFactory emf;
 *       PostRepository(EntityManagerFactory emf) { this.emf = emf; }
 *
 *       public Post7 save(Post7 post) {
 *           EntityManager em = emf.createEntityManager();
 *           em.getTransaction().begin();
 *           em.persist(post);
 *           em.getTransaction().commit();
 *           em.close();
 *           return post;
 *       }
 *       // ... остальные методы
 *   }
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.cfg.Configuration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Task07 {

    public static void main(String[] args) {
        // Создаем EntityManagerFactory
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:blogdb;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.format_sql", "true");
        config.addAnnotatedClass(Post7.class);

        // SessionFactory implements EntityManagerFactory в Hibernate 6
        try (EntityManagerFactory emf = config.buildSessionFactory()) {
            System.out.println("=== POST REPOSITORY (CRUD-ОБЁРТКА) ===\n");

            // Создаем репозиторий
            PostRepository repository = new PostRepository(emf);

            // ===== 1. SAVE - СОХРАНЕНИЕ ПОСТОВ =====
            System.out.println("--- 1. SAVE: Сохранение 3 постов ---");

            Post7 post1 = new Post7("Введение в Java", "Основы языка Java");
            Post7 post2 = new Post7("Spring Boot", "Настройка Spring Boot");
            Post7 post3 = new Post7("Hibernate ORM", "Работа с Hibernate");

            Post7 saved1 = repository.save(post1);
            Post7 saved2 = repository.save(post2);
            Post7 saved3 = repository.save(post3);

            System.out.println("   Сохранены посты:");
            System.out.println("   - " + saved1);
            System.out.println("   - " + saved2);
            System.out.println("   - " + saved3);

            // ===== 2. FIND_BY_ID - ПОИСК ПО ID =====
            System.out.println("\n--- 2. FIND_BY_ID: Поиск по ID ---");

            Optional<Post7> found = repository.findById(1L);
            found.ifPresentOrElse(
                    post -> System.out.println("   Найден пост: " + post),
                    () -> System.out.println("   ❌ Пост с id=1 не найден")
            );

            // Поиск несуществующего поста
            Optional<Post7> notFound = repository.findById(999L);
            System.out.println("   Поиск id=999: " + (notFound.isEmpty() ? "не найден ✅" : "найден ❌"));

            // ===== 3. FIND_ALL - ВСЕ ПОСТЫ =====
            System.out.println("\n--- 3. FIND_ALL: Все посты ---");

            List<Post7> allPosts = repository.findAll();
            System.out.println("   Всего постов: " + allPosts.size());
            for (Post7 post : allPosts) {
                System.out.println("   - " + post);
            }

            // ===== 4. UPDATE - ОБНОВЛЕНИЕ =====
            System.out.println("\n--- 4. UPDATE: Обновление поста ---");

            // Изменяем detached-объект
            saved1.setTitle("Введение в Java (обновлено)");
            saved1.setContent("Обновленное содержание");

            System.out.println("   До обновления: " + saved1);
            Post7 updated = repository.update(saved1);
            System.out.println("   После обновления: " + updated);

            // Проверяем, что обновление сохранилось
            Optional<Post7> checkUpdated = repository.findById(1L);
            checkUpdated.ifPresent(post ->
                    System.out.println("   Проверка в БД: " + post)
            );

            // ===== 5. DELETE - УДАЛЕНИЕ =====
            System.out.println("\n--- 5. DELETE: Удаление поста ---");

            System.out.println("   До удаления: " + repository.findAll().size() + " постов");
            repository.delete(2L);
            System.out.println("   После удаления id=2: " + repository.findAll().size() + " постов");

            // Попытка удалить несуществующий пост
            System.out.println("   Попытка удалить id=999 (не существует)");
            repository.delete(999L);
            System.out.println("   Постов осталось: " + repository.findAll().size());

            // ===== 6. ФИНАЛЬНАЯ ПРОВЕРКА =====
            System.out.println("\n--- 6. ФИНАЛЬНЫЙ СПИСОК ПОСТОВ ---");

            List<Post7> finalPosts = repository.findAll();
            System.out.println("   Осталось постов: " + finalPosts.size());
            for (Post7 post : finalPosts) {
                System.out.println("   - " + post);
            }

            // ===== 7. СТАТИСТИКА =====
            System.out.println("\n--- СТАТИСТИКА ---");
            System.out.println("   Всего постов: " + repository.count());

            // Дополнительная демонстрация: сохранение еще одного поста
            System.out.println("\n   Добавление еще одного поста...");
            Post7 post4 = new Post7("Java Stream API", "Работа со Stream");
            repository.save(post4);
            System.out.println("   Всего постов: " + repository.count());

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Программа завершена");
    }
}
