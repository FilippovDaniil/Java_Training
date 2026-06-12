package m52_hibernate_relationships.practice.task02;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

// ─── Сущности ────────────────────────────────────────────────────────────────

@Entity
@Table(name = "authors2")
class Author2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post2> posts = new ArrayList<>();

    public Author2() {}
    public Author2(String name) { this.name = name; }

    /**
     * TODO: реализовать метод addPost — синхронизировать обе стороны:
     *   1) post.setAuthor(this)
     *   2) this.posts.add(post)
     */
    public void addPost(Post2 post) {
        // TODO
    }

    /**
     * TODO: реализовать метод removePost — синхронизировать обе стороны:
     *   1) post.setAuthor(null)
     *   2) this.posts.remove(post)
     */
    public void removePost(Post2 post) {
        // TODO
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Post2> getPosts() { return posts; }
}
