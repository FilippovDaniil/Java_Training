import jakarta.persistence.*;

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
