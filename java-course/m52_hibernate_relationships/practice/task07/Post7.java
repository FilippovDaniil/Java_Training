package m52_hibernate_relationships.practice.task07;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author7 author;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment7> comments = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "post7_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag7> tags = new HashSet<>();

    public Post7() {}

    public Post7(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addComment(Comment7 comment) {
        comment.setPost(this);
        comments.add(comment);
    }

    public void removeComment(Comment7 comment) {
        comments.remove(comment);
        comment.setPost(null);
    }

    public void addTag(Tag7 tag) {
        tags.add(tag);
        tag.getPosts().add(this);
    }

    public void removeTag(Tag7 tag) {
        tags.remove(tag);
        tag.getPosts().remove(this);
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Author7 getAuthor() { return author; }
    public void setAuthor(Author7 author) { this.author = author; }
    public List<Comment7> getComments() { return comments; }
    public Set<Tag7> getTags() { return tags; }
}

