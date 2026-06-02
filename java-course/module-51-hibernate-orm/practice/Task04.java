/**
 * Задача 04 — Модуль 51: Обновление detached-объекта через merge()
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Сохраните пост (первая сессия — объект в состоянии PERSISTENT).
 *   2) Закройте первую сессию → объект становится DETACHED.
 *   3) Измените title у detached-объекта (без сессии).
 *   4) Откройте вторую сессию, вызовите session.merge(detachedPost) → объект снова PERSISTENT.
 *   5) Сделайте commit — Hibernate выполнит UPDATE.
 *   6) Откройте третью сессию, загрузите пост по id и убедитесь, что title обновился.
 *
 * СОСТОЯНИЯ:
 *   PERSISTENT  — изменения объекта отслеживаются сессией (dirty checking)
 *   DETACHED    — сессия закрыта; изменения объекта НЕ попадут в БД без merge()
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Hibernate: insert into posts …
 *   Hibernate: update posts set title=? … where id=?
 *   Обновлённый title: "Изменённый заголовок"
 *
 * ПОДСКАЗКА:
 *   // После закрытия первой сессии:
 *   post.setTitle("Изменённый заголовок");  // меняем detached
 *   try (Session session2 = factory.openSession()) {
 *       Transaction tx = session2.beginTransaction();
 *       Post4 managed = session2.merge(post);  // merge возвращает managed-копию
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

public class Task04 {

    public static void main(String[] args) {
        // TODO 1: создайте SessionFactory

        Post4 post = null;

        // TODO 2: первая сессия — сохраните пост, запомните объект post
        //         после закрытия сессии post становится DETACHED

        // TODO 3: измените post.setTitle("Изменённый заголовок")
        //         (пока сессия закрыта — это detached-операция, в БД не попадёт)

        // TODO 4: вторая сессия — вызовите session.merge(post)
        //         обратите внимание: merge() возвращает новый managed-экземпляр

        // TODO 5: третья сессия — загрузите пост по id и проверьте title

        // TODO 6: закройте factory
    }
}

// Готовая сущность
@Entity
@Table(name = "posts")
class Post4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Post4() {}

    public Post4(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId()        { return id; }
    public String getTitle()   { return title; }
    public String getContent() { return content; }

    public void setTitle(String title)     { this.title = title; }
    public void setContent(String content) { this.content = content; }
}
