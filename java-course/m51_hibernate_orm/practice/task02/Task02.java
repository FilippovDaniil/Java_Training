package m51_hibernate_orm.practice.task02;

/**
 * Задача 02 — Модуль 51: Сохранение сущности (persist)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Сохраните новый пост в базу данных через Hibernate:
 *     1) Создайте SessionFactory из конфигурации (hibernate.cfg.xml или программная настройка).
 *     2) Откройте Session.
 *     3) Начните транзакцию (beginTransaction()).
 *     4) Создайте объект Post (title = "Первый пост", content = "Привет, Hibernate!").
 *     5) Вызовите session.persist(post) — объект переходит из TRANSIENT в PERSISTENT.
 *     6) Сделайте commit; Hibernate выполнит INSERT.
 *     7) Выведите id, присвоенный базой данных.
 *
 * СОСТОЯНИЯ ОБЪЕКТА:
 *   Post post = new Post(...)   →  TRANSIENT  (JPA не знает об объекте)
 *   session.persist(post)       →  PERSISTENT (объект отслеживается сессией)
 *   tx.commit()                 →  INSERT в БД; id заполнен
 *   session.close()             →  DETACHED   (сессия закрыта)
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Hibernate: insert into posts (title, content, created_at) values (?, ?, ?)
 *   Сохранён пост с id=1
 *
 * ПОДСКАЗКА:
 *   SessionFactory factory = new Configuration()
 *           .configure("hibernate.cfg.xml")
 *           .addAnnotatedClass(Post.class)
 *           .buildSessionFactory();
 *   try (Session session = factory.openSession()) {
 *       Transaction tx = session.beginTransaction();
 *       session.persist(post);
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

public class Task02 {

    public static void main(String[] args) {
        // TODO 1: создайте SessionFactory (программная конфигурация или из hibernate.cfg.xml)
        // Пример программной настройки:
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:blogdb;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.addAnnotatedClass(Post2.class);
        SessionFactory factory = config.buildSessionFactory();

        // TODO 2: создайте объект Post с заголовком "Первый пост" и контентом
        Post2 post = new Post2("Первый пост!", "Привет, hibernate!");

        // TODO 3: откройте Session, начните транзакцию, вызовите persist, сделайте commit
        try (Session session = factory.openSession()) {
        Transaction tx = session.beginTransaction();
        session.persist(post);
        tx.commit();
        Post2 found = session.get(Post2.class, 1L);
        System.out.println(found);
        System.out.println("Сохранён пост с id=" + post.getId());
        }

        // TODO 4: выведите id присвоенный базой данных


        // TODO 5: закройте factory
    }
}
