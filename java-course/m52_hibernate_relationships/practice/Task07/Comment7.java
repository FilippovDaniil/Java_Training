package m52_hibernate_relationships.practice.task07;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "comments7")
class Comment7 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    // TODO: @ManyToOne(fetch = FetchType.LAZY) + @JoinColumn(name = "post_id")
    private Post7 post;

    public Comment7() {}
    public Comment7(String text) { this.text = text; }

    public Long getId() { return id; }
    public String getText() { return text; }
    public Post7 getPost() { return post; }
    public void setPost(Post7 post) { this.post = post; }
}
