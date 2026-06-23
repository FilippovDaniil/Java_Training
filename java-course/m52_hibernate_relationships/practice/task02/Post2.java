package m52_hibernate_relationships.practice.task02;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts2")
class Post2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author2 author;

    public Post2() {}

    public Post2(String title) {
        this.title = title;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Author2 getAuthor() { return author; }
    public void setAuthor(Author2 author) { this.author = author; }

    @Override
    public String toString() {
        return "Post2{id=" + id + ", title='" + title + "'}";
    }
}
