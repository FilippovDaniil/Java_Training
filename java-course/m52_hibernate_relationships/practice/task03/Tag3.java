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

    // TODO: добавить @ManyToMany(mappedBy = "tags")
    private Set<Post3> posts = new HashSet<>();

    public Tag3() {}
    public Tag3(String name) { this.name = name; }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Set<Post3> getPosts() { return posts; }
}
