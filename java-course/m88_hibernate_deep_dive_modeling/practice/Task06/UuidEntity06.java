package m88_hibernate_deep_dive_modeling.practice.task06;

import jakarta.persistence.*;
import java.util.UUID;

@Entity @Table(name = "uuid_entities")
class UuidEntity06 {
    @Id
    // TODO: @GeneratedValue
    private UUID id;
    private String name;
    protected UuidEntity06() {}
    public UuidEntity06(String name) { this.name = name; }
    public UUID getId() { return id; }
}
