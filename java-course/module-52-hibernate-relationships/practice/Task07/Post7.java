import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "posts7")
class Post7 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    // TODO: @ManyToOne(fetch = FetchType.LAZY) + @JoinColumn(name = "author_id")
    private Author7 author;

    // TODO: @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment7> comments = new ArrayList<>();

    // TODO: @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    // TODO: @JoinTable(name = "post7_tags", joinColumns = ..., inverseJoinColumns = ...)
    private Set<Tag7> tags = new HashSet<>();

    public Post7() {}
    public Post7(String title, String content) { this.title = title; this.content = content; }

    /** TODO: addComment — синхронизация comment.setPost + comments.add */
    public void addComment(Comment7 comment) { /* TODO */ }

    /** TODO: addTag — синхронизация tag.getPosts().add(this) + tags.add(tag) */
    public void addTag(Tag7 tag) { /* TODO */ }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Author7 getAuthor() { return author; }
    public void setAuthor(Author7 author) { this.author = author; }
    public List<Comment7> getComments() { return comments; }
    public Set<Tag7> getTags() { return tags; }
}
