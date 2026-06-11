package m88_hibernate_deep_dive_modeling.practice.task06;

import jakarta.persistence.*;
import java.util.UUID;

@Entity @Table(name = "identity_entities")
class IdentityEntity06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    protected IdentityEntity06() {}
    public IdentityEntity06(String name) { this.name = name; }
    public Long getId() { return id; }
}
