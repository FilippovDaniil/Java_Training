package m52_hibernate_relationships.practice.task03;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags3")
class Tag3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Post3> posts = new HashSet<>();

    public Tag3() {}

    public Tag3(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<Post3> getPosts() { return posts; }
    public void setPosts(Set<Post3> posts) { this.posts = posts; }

    @Override
    public String toString() {
        return "Tag3{id=" + id + ", name='" + name + "'}";
    }
}
