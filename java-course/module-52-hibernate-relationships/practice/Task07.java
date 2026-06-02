/**
 * Задача 07 — Модуль 52: МИНИ-ПРОЕКТ — полная доменная модель блога
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Перед вами каркасы всех сущностей блога: Author7, Post7, Comment7, Tag7, UserProfile7.
 *   Все связи помечены TODO-аннотациями. Ваша задача:
 *
 *   1) РАЗМЕТИТЬ ВСЕ СВЯЗИ — заменить TODO-комментарии на правильные аннотации JPA:
 *      - Author7 ──1:N──► Post7      (@OneToMany mappedBy + @ManyToOne @JoinColumn)
 *      - Post7   ──1:N──► Comment7   (@OneToMany mappedBy + @ManyToOne @JoinColumn)
 *      - Post7   ──N:M──► Tag7       (@ManyToMany @JoinTable / mappedBy)
 *      - Author7 ──1:1──► UserProfile7 (@OneToOne @JoinColumn / mappedBy)
 *
 *   2) НАСТРОИТЬ CASCADE и FETCH:
 *      - Author7.posts    → cascade ALL, orphanRemoval, LAZY
 *      - Post7.comments   → cascade ALL, orphanRemoval, LAZY
 *      - Post7.tags       → cascade PERSIST+MERGE (не ALL!), LAZY
 *      - Author7.profile  → cascade ALL, orphanRemoval, LAZY
 *
 *   3) РЕАЛИЗОВАТЬ BlogRepository — класс с методами:
 *      а) save(Author7 author)               — persist в транзакции
 *      б) findById(Long id)                  — em.find(Author7.class, id)
 *      в) findAuthorsWithPosts()             — JOIN FETCH a.posts (без N+1!)
 *      г) findPostsWithTagsAndComments(Long authorId)
 *                                            — JOIN FETCH p.tags, JOIN FETCH p.comments
 *
 *   4) В методе main создайте полный сценарий:
 *      - 2 автора, у каждого профиль
 *      - у первого автора 3 поста, у второго 2 поста
 *      - 4 тега, распределить по постам (некоторые теги — общие)
 *      - у каждого поста 2–3 комментария
 *      - вызвать BlogRepository.findAuthorsWithPosts(), вывести всё дерево
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   === Авторы с постами ===
 *   Анна Смирнова [anna@blog.ru]
 *     POST: "Начало работы с Hibernate"  [теги: java, hibernate]
 *       COMMENT: "Спасибо, очень понятно!"
 *       COMMENT: "Когда будет продолжение?"
 *     POST: "Spring Data JPA — просто"  [теги: java, spring]
 *       COMMENT: "Отличная статья"
 *   Иван Козлов [ivan@blog.ru]
 *     POST: "Микросервисы с нуля"  [теги: spring, architecture]
 *       COMMENT: "А как с мониторингом?"
 *
 * ПОДСКАЗКА:
 *   Для findPostsWithTagsAndComments используйте два отдельных JOIN FETCH
 *   или @EntityGraph — нельзя сделать два JOIN FETCH одновременно
 *   на разные коллекции в одном запросе (HibernateException: cannot simultaneously fetch).
 *   Решение: загрузить посты с тегами, потом отдельно с комментариями,
 *   или использовать @BatchSize для одной из коллекций.
 */
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task07 {

    public static void main(String[] args) {
        // TODO: создать EMF (H2 in-memory)

        // TODO: создать авторов с профилями
        // Author7 anna = new Author7("Анна Смирнова", "anna@blog.ru");
        // UserProfile7 annaProfile = new UserProfile7("Java-разработчик ...", "https://...");
        // anna.setProfile(annaProfile);

        // TODO: создать посты, теги, комментарии
        // TODO: связать всё через вспомогательные методы

        // TODO: BlogRepository repo = new BlogRepository(emf);
        // TODO: repo.save(anna); repo.save(ivan);

        // TODO: List<Author7> authors = repo.findAuthorsWithPosts();
        // TODO: вывести дерево автор → посты → теги → комментарии
    }
}

// ─── Сущности — каркасы ──────────────────────────────────────────────────────

@Entity
@Table(name = "authors7")
class Author7 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // TODO: @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // TODO: @JoinColumn(name = "profile_id", unique = true)
    private UserProfile7 profile;

    // TODO: @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post7> posts = new ArrayList<>();

    public Author7() {}
    public Author7(String name, String email) { this.name = name; this.email = email; }

    /** TODO: реализовать addPost — синхронизировать post.setAuthor + posts.add */
    public void addPost(Post7 post) { /* TODO */ }

    /** TODO: реализовать removePost */
    public void removePost(Post7 post) { /* TODO */ }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public UserProfile7 getProfile() { return profile; }
    public void setProfile(UserProfile7 profile) { this.profile = profile; }
    public List<Post7> getPosts() { return posts; }
}

@Entity
@Table(name = "posts7")
class Post7 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    // TODO: @ManyToOne(fetch = FetchType.LAZY) + @JoinColumn(name = "author_id")
    private Author7 author;

    // TODO: @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment7> comments = new ArrayList<>();

    // TODO: @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    // TODO: @JoinTable(name = "post7_tags", joinColumns = ..., inverseJoinColumns = ...)
    private Set<Tag7> tags = new HashSet<>();

    public Post7() {}
    public Post7(String title, String content) { this.title = title; this.content = content; }

    /** TODO: addComment — синхронизация comment.setPost + comments.add */
    public void addComment(Comment7 comment) { /* TODO */ }

    /** TODO: addTag — синхронизация tag.getPosts().add(this) + tags.add(tag) */
    public void addTag(Tag7 tag) { /* TODO */ }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Author7 getAuthor() { return author; }
    public void setAuthor(Author7 author) { this.author = author; }
    public List<Comment7> getComments() { return comments; }
    public Set<Tag7> getTags() { return tags; }
}

@Entity
@Table(name = "comments7")
class Comment7 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    // TODO: @ManyToOne(fetch = FetchType.LAZY) + @JoinColumn(name = "post_id")
    private Post7 post;

    public Comment7() {}
    public Comment7(String text) { this.text = text; }

    public Long getId() { return id; }
    public String getText() { return text; }
    public Post7 getPost() { return post; }
    public void setPost(Post7 post) { this.post = post; }
}

@Entity
@Table(name = "tags7")
class Tag7 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    // TODO: @ManyToMany(mappedBy = "tags")
    private Set<Post7> posts = new HashSet<>();

    public Tag7() {}
    public Tag7(String name) { this.name = name; }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Set<Post7> getPosts() { return posts; }
}

@Entity
@Table(name = "user_profiles7")
class UserProfile7 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String bio;

    private String avatarUrl;

    // TODO: @OneToOne(mappedBy = "profile", fetch = FetchType.LAZY)
    private Author7 author;

    public UserProfile7() {}
    public UserProfile7(String bio, String avatarUrl) { this.bio = bio; this.avatarUrl = avatarUrl; }

    public Long getId() { return id; }
    public String getBio() { return bio; }
    public String getAvatarUrl() { return avatarUrl; }
    public Author7 getAuthor() { return author; }
    public void setAuthor(Author7 author) { this.author = author; }
}

// ─── Репозиторий — каркас ────────────────────────────────────────────────────

class BlogRepository {

    private final EntityManagerFactory emf;

    public BlogRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /** Сохранить автора в транзакции (каскад сохранит всё дерево) */
    public void save(Author7 author) {
        // TODO: em.getTransaction().begin(); em.persist(author); em.getTransaction().commit();
    }

    /** Найти автора по id (простой em.find) */
    public Author7 findById(Long id) {
        // TODO: return em.find(Author7.class, id);
        return null;
    }

    /**
     * Загрузить всех авторов с постами одним запросом (решение N+1).
     * JPQL: "SELECT DISTINCT a FROM Author7 a JOIN FETCH a.posts"
     */
    public List<Author7> findAuthorsWithPosts() {
        // TODO: createQuery + JOIN FETCH
        return List.of();
    }

    /**
     * Загрузить посты автора с тегами и комментариями.
     * ПОДСКАЗКА: нельзя делать два JOIN FETCH на разные коллекции в одном запросе.
     * Выполните два отдельных запроса или используйте @BatchSize на одной коллекции.
     */
    public List<Post7> findPostsWithTagsAndComments(Long authorId) {
        // TODO
        return List.of();
    }
}
