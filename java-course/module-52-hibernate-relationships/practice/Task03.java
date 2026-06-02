/**
 * Задача 03 — Модуль 52: @ManyToMany (Post N—N Tag) через @JoinTable
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Разметьте поле tags в классе Post3:
 *      - @ManyToMany с cascade = {CascadeType.PERSIST, CascadeType.MERGE}
 *      - @JoinTable(name = "post_tags3",
 *                   joinColumns = @JoinColumn(name = "post_id"),
 *                   inverseJoinColumns = @JoinColumn(name = "tag_id"))
 *   2) В классе Tag3 разметьте поле posts аннотацией @ManyToMany(mappedBy = "tags").
 *   3) В методе main:
 *      а) Создайте 2 поста и 3 тега: "java", "hibernate", "spring".
 *      б) Присвойте тегу "java" оба поста, тегу "hibernate" — первый пост,
 *         тегу "spring" — второй пост.
 *      в) Сохраните все сущности через persist постов (каскад подхватит теги).
 *      г) Загрузите первый пост и выведите его теги.
 *      д) Загрузите тег "java" и выведите связанные с ним посты.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Теги поста "Введение в Hibernate": [java, hibernate]
 *   Посты с тегом "java": [Введение в Hibernate, Spring Boot быстрый старт]
 *
 * ПОДСКАЗКА:
 *   Синхронизируйте обе стороны:
 *     post.getTags().add(tag);
 *     tag.getPosts().add(post);
 *   Не используйте CascadeType.REMOVE или ALL для @ManyToMany —
 *   удаление поста не должно удалять общий тег.
 */
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class Task03 {

    public static void main(String[] args) {
        // TODO: создать EMF (H2 in-memory)
        // TODO: создать 2 Post3 и 3 Tag3
        // TODO: установить связи (обе стороны)
        // TODO: persist постов, commit
        // TODO: загрузить пост 1, вывести теги
        // TODO: загрузить тег "java", вывести посты
    }
}

// ─── Сущности ────────────────────────────────────────────────────────────────

@Entity
@Table(name = "posts3")
class Post3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // TODO: добавить @ManyToMany и @JoinTable (см. задание)
    private Set<Tag3> tags = new HashSet<>();

    public Post3() {}
    public Post3(String title) { this.title = title; }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Set<Tag3> getTags() { return tags; }
}

@Entity
@Table(name = "tags3")
class Tag3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    // TODO: добавить @ManyToMany(mappedBy = "tags")
    private Set<Post3> posts = new HashSet<>();

    public Tag3() {}
    public Tag3(String name) { this.name = name; }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Set<Post3> getPosts() { return posts; }
}
