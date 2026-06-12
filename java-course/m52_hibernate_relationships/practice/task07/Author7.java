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

    // TODO: @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // TODO: @JoinColumn(name = "profile_id", unique = true)
    private UserProfile7 profile;

    // TODO: @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post7> posts = new ArrayList<>();

    public Author7() {}
    public Author7(String name, String email) { this.name = name; this.email = email; }

    /** TODO: реализовать addPost — синхронизировать post.setAuthor + posts.add */
    public void addPost(Post7 post) { /* TODO */ }

    /** TODO: реализовать removePost */
    public void removePost(Post7 post) { /* TODO */ }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public UserProfile7 getProfile() { return profile; }
    public void setProfile(UserProfile7 profile) { this.profile = profile; }
    public List<Post7> getPosts() { return posts; }
}
