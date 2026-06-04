import jakarta.persistence.*;

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
