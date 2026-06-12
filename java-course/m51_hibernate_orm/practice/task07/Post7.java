package m51_hibernate_orm.practice.task07;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.cfg.Configuration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// Готовая сущность
@Entity
@Table(name = "posts")
class Post7 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Post7() {}

    public Post7(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId()        { return id; }
    public String getTitle()   { return title; }
    public String getContent() { return content; }

    public void setTitle(String title)     { this.title = title; }
    public void setContent(String content) { this.content = content; }

    @Override
    public String toString() {
        return "Post7{id=" + id + ", title='" + title + "'}";
    }
}
