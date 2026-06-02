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
import java.util.ArrayList;
import java.util.List;

public class Task02 {

    public static void main(String[] args) {
        // TODO: создать EMF (H2 in-memory)
        // TODO: эксперимент а) — только обратная сторона, проверить результат
        // TODO: эксперимент б) — только владелец, проверить результат
        // TODO: эксперимент в) — использовать author.addPost(post)
    }
}

// ─── Сущности ────────────────────────────────────────────────────────────────

@Entity
@Table(name = "authors2")
class Author2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post2> posts = new ArrayList<>();

    public Author2() {}
    public Author2(String name) { this.name = name; }

    /**
     * TODO: реализовать метод addPost — синхронизировать обе стороны:
     *   1) post.setAuthor(this)
     *   2) this.posts.add(post)
     */
    public void addPost(Post2 post) {
        // TODO
    }

    /**
     * TODO: реализовать метод removePost — синхронизировать обе стороны:
     *   1) post.setAuthor(null)
     *   2) this.posts.remove(post)
     */
    public void removePost(Post2 post) {
        // TODO
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Post2> getPosts() { return posts; }
}

@Entity
@Table(name = "posts2")
class Post2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author2 author;

    public Post2() {}
    public Post2(String title) { this.title = title; }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Author2 getAuthor() { return author; }
    public void setAuthor(Author2 author) { this.author = author; }
}
