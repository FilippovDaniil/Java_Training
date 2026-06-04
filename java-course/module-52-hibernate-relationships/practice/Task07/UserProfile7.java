import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
