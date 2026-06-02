/**
 * Задача 06 — Модуль 51: JPQL-запросы (выборка, фильтрация, сортировка)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Сохраните 5 постов с разными заголовками и датами.
 *   2) Выполните JPQL-запрос "FROM Post6 ORDER BY createdAt DESC" —
 *      выведите все посты, начиная с самого нового.
 *   3) Выполните параметризованный JPQL-запрос:
 *      найдите все посты, у которых title содержит слово "Java" (LIKE :pattern).
 *   4) Выполните агрегирующий запрос:
 *      подсчитайте количество постов через "SELECT COUNT(p) FROM Post6 p".
 *
 * JPQL vs SQL:
 *   SQL:  SELECT * FROM posts ORDER BY created_at DESC
 *   JPQL: FROM Post6 ORDER BY createdAt DESC   (имя КЛАССА и ПОЛЯ, не таблицы!)
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Все посты (от новых к старым):
 *     Post6{id=5, title='Kotlin vs Java'}
 *     Post6{id=4, title='Spring Boot'}
 *     …
 *   Посты с 'Java': 2
 *   Всего постов: 5
 *
 * ПОДСКАЗКА:
 *   List<Post6> all = session
 *           .createQuery("FROM Post6 ORDER BY createdAt DESC", Post6.class)
 *           .getResultList();
 *
 *   List<Post6> filtered = session
 *           .createQuery("FROM Post6 p WHERE p.title LIKE :pattern", Post6.class)
 *           .setParameter("pattern", "%Java%")
 *           .getResultList();
 *
 *   Long count = session
 *           .createQuery("SELECT COUNT(p) FROM Post6 p", Long.class)
 *           .getSingleResult();
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

public class Task06 {

    public static void main(String[] args) throws InterruptedException {
        // TODO 1: создайте SessionFactory

        // TODO 2: сохраните 5 постов, например:
        //   "Введение в Java", "Spring Boot основы", "Hibernate ORM",
        //   "Kotlin vs Java", "Паттерны проектирования"
        //   Используйте небольшую задержку между сохранениями (Thread.sleep(10))
        //   чтобы createdAt различались, если создаётся с точностью до миллисекунд

        // TODO 3: откройте сессию (без транзакции — чтение)
        //         запросите все посты через JPQL с сортировкой по createdAt DESC
        //         выведите их в цикле

        // TODO 4: выполните параметризованный запрос: LIKE "%Java%"
        //         выведите количество найденных постов

        // TODO 5: выполните COUNT запрос и выведите результат

        // TODO 6: закройте factory
    }
}

// Готовая сущность
@Entity
@Table(name = "posts")
class Post6 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Post6() {}

    public Post6(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId()        { return id; }
    public String getTitle()   { return title; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return "Post6{id=" + id + ", title='" + title + "', createdAt=" + createdAt + "}";
    }
}
