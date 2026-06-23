package m52_hibernate_relationships.practice.task06;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// ===== ENTITY COMMENT =====
@Entity
@Table(name = "comments6")
class Comment6 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    // Измените на FetchType.EAGER для демонстрации
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post6 post;

    public Comment6() {}

    public Comment6(String text) {
        this.text = text;
    }

    public Long getId() { return id; }
    public String getText() { return text; }
    public Post6 getPost() { return post; }
    public void setPost(Post6 post) { this.post = post; }

    @Override
    public String toString() {
        return "Comment6{id=" + id + ", text='" + text + "'}";
    }
}
