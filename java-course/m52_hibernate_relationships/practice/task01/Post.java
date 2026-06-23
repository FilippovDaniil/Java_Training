package m52_hibernate_relationships.practice.task01;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "posts")
@Getter
@Setter
@ToString
class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    // TODO: добавить
    @ManyToOne(fetch = FetchType.LAZY)
    //       и
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public Post() {}

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // TODO: геттеры и сеттеры
}
