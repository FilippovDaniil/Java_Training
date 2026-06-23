package m52_hibernate_relationships.practice.task03;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

// ─── Сущности ────────────────────────────────────────────────────────────────

@Entity
@Table(name = "posts3")
class Post3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "post_tags3",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag3> tags = new HashSet<>();

    public Post3() {}

    public Post3(String title) {
        this.title = title;
    }

    public void addTag(Tag3 tag) {
        tags.add(tag);
        tag.getPosts().add(this);
    }

    public void removeTag(Tag3 tag) {
        tags.remove(tag);
        tag.getPosts().remove(this);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Set<Tag3> getTags() { return tags; }
    public void setTags(Set<Tag3> tags) { this.tags = tags; }

    @Override
    public String toString() {
        return "Post3{id=" + id + ", title='" + title + "'}";
    }
}