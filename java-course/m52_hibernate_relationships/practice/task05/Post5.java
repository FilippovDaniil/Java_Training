package m52_hibernate_relationships.practice.task05;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

// ─── Сущности ────────────────────────────────────────────────────────────────

// ===== ENTITY POST =====
@Entity
@Table(name = "posts5")
class Post5 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment5> comments = new ArrayList<>();

    public Post5() {}

    public Post5(String title) {
        this.title = title;
    }

    /** Вспомогательный метод — синхронизирует обе стороны связи */
    public void addComment(Comment5 comment) {
        comment.setPost(this);
        comments.add(comment);
    }

    /** Вспомогательный метод — удаляет с синхронизацией */
    public void removeComment(Comment5 comment) {
        comments.remove(comment);
        comment.setPost(null);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<Comment5> getComments() { return comments; }
    public void setComments(List<Comment5> comments) { this.comments = comments; }

    @Override
    public String toString() {
        return "Post5{id=" + id + ", title='" + title + "', comments=" + comments.size() + "}";
    }
}