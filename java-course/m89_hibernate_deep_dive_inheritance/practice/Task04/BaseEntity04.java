package m89_hibernate_deep_dive_inheritance.practice.task04;

import jakarta.persistence.*;
import java.time.Instant;

@MappedSuperclass
abstract class BaseEntity04 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_at")
    private Instant createdAt = Instant.now();
    public Long getId() { return id; }
}
