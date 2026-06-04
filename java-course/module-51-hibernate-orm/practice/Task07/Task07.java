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
        // TODO 1: создайте EntityManagerFactory через Hibernate Configuration
        //   Configuration config = new Configuration().configure("hibernate.cfg.xml");
        //   config.addAnnotatedClass(Post7.class);
        //   EntityManagerFactory emf = config.buildSessionFactory();
        //   (SessionFactory implements EntityManagerFactory в Hibernate 6)

        // TODO 2: создайте PostRepository(emf)

        // TODO 3: save — сохраните 3 поста и выведите их id

        // TODO 4: findById — найдите пост по id=1, выведите title

        // TODO 5: findAll — выведите все посты

        // TODO 6: update — измените title поста с id=1, сохраните через update()
        //         затем findById(1L) и убедитесь что title изменился

        // TODO 7: delete — удалите пост с id=2
        //         вызовите findAll() — должно остаться 2 поста

        // TODO 8: закройте emf
    }
}
