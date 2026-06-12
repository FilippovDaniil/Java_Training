package m52_hibernate_relationships.practice.task06;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "posts6")
class Post6 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author6 author;

    // Комментарии к посту — LAZY по умолчанию для @OneToMany
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment6> comments = new ArrayList<>();

    public Post6() {}
    public Post6(String title) { this.title = title; }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Author6 getAuthor() { return author; }
    public void setAuthor(Author6 author) { this.author = author; }
    public List<Comment6> getComments() { return comments; }
}
