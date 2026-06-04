import jakarta.persistence.*;
import java.util.UUID;

@Entity @Table(name = "sequence_entities")
class SequenceEntity06 {
    @Id
    // TODO: @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq06")
    // TODO: @SequenceGenerator(name = "seq06", sequenceName = "entity_seq", allocationSize = 1)
    private Long id;
    private String name;
    protected SequenceEntity06() {}
    public SequenceEntity06(String name) { this.name = name; }
    public Long getId() { return id; }
}
