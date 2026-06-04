import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// ─── Сущности — заготовки ───────────────────────────────────────────────────

@Entity
@Table(name = "authors")
class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    // TODO: добавить @OneToMany с mappedBy = "author"
    //       и cascade = CascadeType.ALL, orphanRemoval = true
    private List<Post> posts = new ArrayList<>();

    public Author() {}

    public Author(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // TODO: геттеры и сеттеры
}
