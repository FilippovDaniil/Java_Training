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

    public Author2(String name) {
        this.name = name;
    }

    /**
     * Синхронизирует обе стороны связи:
     * 1) post.setAuthor(this)
     * 2) this.posts.add(post)
     */
    public void addPost(Post2 post) {
        if (post != null) {
            post.setAuthor(this);
            this.posts.add(post);
        }
    }

    /**
     * Синхронизирует обе стороны связи при удалении:
     * 1) post.setAuthor(null)
     * 2) this.posts.remove(post)
     */
    public void removePost(Post2 post) {
        if (post != null) {
            post.setAuthor(null);
            this.posts.remove(post);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Post2> getPosts() { return posts; }
    public void setPosts(List<Post2> posts) { this.posts = posts; }

    @Override
    public String toString() {
        return "Author2{id=" + id + ", name='" + name + "'}";
    }
}