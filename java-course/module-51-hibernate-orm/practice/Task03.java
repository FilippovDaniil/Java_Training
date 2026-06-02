/**
 * Задача 03 — Модуль 51: Поиск сущности по id (find / get)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Создайте SessionFactory и сохраните несколько постов (id присвоятся автоматически).
 *   2) Загрузите пост по известному id с помощью session.get(Post3.class, id).
 *   3) Выведите title и content найденного поста.
 *   4) Попробуйте найти пост с несуществующим id (например, 999L).
 *      Убедитесь, что метод get() возвращает null (а не бросает исключение).
 *
 * РАЗНИЦА get() vs load():
 *   session.get(Post3.class, id)   — возвращает null если нет записи
 *   session.load(Post3.class, id)  — возвращает прокси; ObjectNotFoundException при доступе к полям
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Hibernate: insert into posts …
 *   Найден: id=1, title="Первый пост"
 *   По id=999: null
 *
 * ПОДСКАЗКА:
 *   Post3 found = session.get(Post3.class, 1L);
 *   if (found != null) {
 *       System.out.println("Найден: " + found.getTitle());
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

public class Task03 {

    public static void main(String[] args) {
        // TODO 1: создайте SessionFactory (аналогично Task02)

        // TODO 2: в отдельной транзакции сохраните 2–3 поста через session.persist()

        // TODO 3: откройте новую Session и найдите пост с id=1L через session.get()
        //         выведите title и content

        // TODO 4: попробуйте session.get(Post3.class, 999L) и выведите результат (null)

        // TODO 5: закройте factory
    }
}

// Готовая сущность
@Entity
@Table(name = "posts")
class Post3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Post3() {}

    public Post3(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId()        { return id; }
    public String getTitle()   { return title; }
    public String getContent() { return content; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return "Post3{id=" + id + ", title='" + title + "'}";
    }
}
