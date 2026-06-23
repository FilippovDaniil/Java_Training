package m52_hibernate_relationships.practice.task01;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// ─── Сущности — заготовки ───────────────────────────────────────────────────

@Entity
@Table(name = "authors")
@Getter
@Setter
@ToString
class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    // TODO: добавить @OneToMany с mappedBy = "author"
    //       и cascade = CascadeType.ALL, orphanRemoval = true
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    public Author() {}

    public Author(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addPost(Post post) {
        posts.add(post);
        post.setAuthor(this);
    }

    public void removePost(Post post) {
        posts.remove(post);
        post.setAuthor(null);
    }

    // TODO: геттеры и сеттеры
}
