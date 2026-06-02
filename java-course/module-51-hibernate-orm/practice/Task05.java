/**
 * Задача 05 — Модуль 51: Удаление сущности через remove()
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Сохраните три поста.
 *   2) Загрузите пост с id=2L через session.get().
 *   3) Вызовите session.remove(managed) — объект переходит в состояние REMOVED.
 *   4) Сделайте commit — Hibernate выполнит DELETE.
 *   5) Проверьте: загрузите все посты через JPQL "FROM Post5" и убедитесь, что остались два.
 *
 * ВАЖНО:
 *   Нельзя вызвать remove() для DETACHED-объекта напрямую.
 *   Сначала нужно загрузить объект в текущую сессию (get/find),
 *   а затем вызвать remove() — только тогда он в состоянии MANAGED.
 *
 *   ❌ Неправильно:
 *      Post5 detached = ...  // получен в другой сессии
 *      session.remove(detached);  // IllegalArgumentException / ошибка
 *
 *   ✅ Правильно:
 *      Post5 managed = session.get(Post5.class, id);
 *      session.remove(managed);
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Hibernate: delete from posts where id=?
 *   Постов после удаления: 2
 *
 * ПОДСКАЗКА:
 *   try (Session session = factory.openSession()) {
 *       Transaction tx = session.beginTransaction();
 *       Post5 toDelete = session.get(Post5.class, 2L);
 *       if (toDelete != null) {
 *           session.remove(toDelete);
 *       }
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
import java.util.List;

public class Task05 {

    public static void main(String[] args) {
        // TODO 1: создайте SessionFactory

        // TODO 2: сохраните три поста ("Пост А", "Пост Б", "Пост В")

        // TODO 3: загрузите пост с id=2L и удалите его через session.remove()
        //         не забудьте начать транзакцию и сделать commit

        // TODO 4: загрузите все посты через JPQL "FROM Post5"
        //         и выведите их количество (ожидается 2)

        // TODO 5: закройте factory
    }
}

// Готовая сущность
@Entity
@Table(name = "posts")
class Post5 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Post5() {}

    public Post5(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId()      { return id; }
    public String getTitle() { return title; }

    @Override
    public String toString() {
        return "Post5{id=" + id + ", title='" + title + "'}";
    }
}
