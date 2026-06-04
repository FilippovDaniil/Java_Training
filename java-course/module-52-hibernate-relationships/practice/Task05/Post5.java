import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

// ─── Сущности ────────────────────────────────────────────────────────────────

@Entity
@Table(name = "posts5")
class Post5 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // TODO: @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment5> comments = new ArrayList<>();

    public Post5() {}
    public Post5(String title) { this.title = title; }

    /** Вспомогательный метод — синхронизирует обе стороны связи */
    public void addComment(Comment5 comment) {
        // TODO: comment.setPost(this); comments.add(comment);
    }

    /** Вспомогательный метод — удаляет с синхронизацией */
    public void removeComment(Comment5 comment) {
        // TODO: comment.setPost(null); comments.remove(comment);
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public List<Comment5> getComments() { return comments; }
}
