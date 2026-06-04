import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
