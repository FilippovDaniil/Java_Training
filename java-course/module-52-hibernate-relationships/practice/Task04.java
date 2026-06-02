/**
 * Задача 04 — Модуль 52: @OneToOne (Author — UserProfile)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Каждый автор имеет ровно один профиль (UserProfile4).
 *
 *   1) Разметьте связь на стороне Author4:
 *      - @OneToOne с cascade = CascadeType.ALL и orphanRemoval = true
 *      - @JoinColumn(name = "profile_id", unique = true)
 *      - fetch = FetchType.LAZY
 *   2) Разметьте обратную сторону на UserProfile4:
 *      - @OneToOne(mappedBy = "profile", fetch = FetchType.LAZY)
 *   3) В методе main:
 *      а) Создайте Author4 с UserProfile4 (bio, avatarUrl).
 *      б) Сохраните автора — профиль должен сохраниться каскадно.
 *      в) Загрузите профиль по id. Обратитесь к profile.getAuthor() — выведите имя.
 *      г) Удалите профиль у автора (author.setProfile(null)) и сохраните —
 *         проверьте, что orphanRemoval = true удалил запись из user_profiles.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Автор профиля: Иван Петров
 *   После удаления профиля: profile IS NULL in DB
 *
 * ПОДСКАЗКА:
 *   Для двунаправленного @OneToOne важно синхронизировать обе стороны:
 *     author.setProfile(profile);
 *     profile.setAuthor(author);
 *   Иначе profile.getAuthor() вернёт null до перезагрузки из БД.
 *
 *   Чтобы убедиться в удалении через orphanRemoval, выполните em.flush()
 *   и повторно загрузите запись через em.find(UserProfile4.class, id).
 */
import jakarta.persistence.*;

public class Task04 {

    public static void main(String[] args) {
        // TODO: создать EMF (H2 in-memory)
        // TODO: создать Author4 + UserProfile4, установить связи обеих сторон
        // TODO: persist author, commit (каскад сохранит profile)
        // TODO: загрузить profile, получить profile.getAuthor().getName()
        // TODO: author.setProfile(null), merge, flush → убедиться в orphanRemoval
    }
}

// ─── Сущности ────────────────────────────────────────────────────────────────

@Entity
@Table(name = "authors4")
class Author4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // TODO: @OneToOne + @JoinColumn (владелец связи, LAZY, cascade ALL, orphanRemoval)
    private UserProfile4 profile;

    public Author4() {}
    public Author4(String name) { this.name = name; }

    public Long getId() { return id; }
    public String getName() { return name; }
    public UserProfile4 getProfile() { return profile; }
    public void setProfile(UserProfile4 profile) { this.profile = profile; }
}

@Entity
@Table(name = "user_profiles4")
class UserProfile4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String bio;

    private String avatarUrl;

    // TODO: @OneToOne(mappedBy = "profile", fetch = FetchType.LAZY)
    private Author4 author;

    public UserProfile4() {}
    public UserProfile4(String bio, String avatarUrl) {
        this.bio = bio;
        this.avatarUrl = avatarUrl;
    }

    public Long getId() { return id; }
    public String getBio() { return bio; }
    public String getAvatarUrl() { return avatarUrl; }
    public Author4 getAuthor() { return author; }
    public void setAuthor(Author4 author) { this.author = author; }
}
