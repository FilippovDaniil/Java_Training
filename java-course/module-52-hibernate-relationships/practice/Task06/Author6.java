import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// ─── Сущности ────────────────────────────────────────────────────────────────

@Entity
@Table(name = "authors6")
class Author6 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // LAZY — загружаем посты только при обращении
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Post6> posts = new ArrayList<>();

    public Author6() {}
    public Author6(String name) { this.name = name; }

    public void addPost(Post6 post) {
        post.setAuthor(this);
        posts.add(post);
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Post6> getPosts() { return posts; }
}
