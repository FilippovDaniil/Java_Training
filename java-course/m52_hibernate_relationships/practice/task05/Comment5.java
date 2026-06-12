package m52_hibernate_relationships.practice.task05;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comments5")
class Comment5 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    // TODO: @ManyToOne(fetch = FetchType.LAZY) + @JoinColumn(name = "post_id")
    private Post5 post;

    public Comment5() {}
    public Comment5(String text) { this.text = text; }

    public Long getId() { return id; }
    public String getText() { return text; }
    public Post5 getPost() { return post; }
    public void setPost(Post5 post) { this.post = post; }
}
