package m52_hibernate_relationships.practice.task07;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// ─── Сущности — каркасы ──────────────────────────────────────────────────────

@Entity
@Table(name = "authors7")
class Author7 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", unique = true)
    private UserProfile7 profile;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post7> posts = new ArrayList<>();

    public Author7() {}

    public Author7(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addPost(Post7 post) {
        post.setAuthor(this);
        posts.add(post);
    }

    public void removePost(Post7 post) {
        posts.remove(post);
        post.setAuthor(null);
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public UserProfile7 getProfile() { return profile; }
    public void setProfile(UserProfile7 profile) { this.profile = profile; }
    public List<Post7> getPosts() { return posts; }
}
