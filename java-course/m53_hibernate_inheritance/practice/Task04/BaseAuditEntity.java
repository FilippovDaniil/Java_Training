package m53_hibernate_inheritance.practice.task04;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// --- @MappedSuperclass (дополните) ---

@MappedSuperclass
abstract class BaseAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    void onUpdate() {
        // TODO: установите updatedAt = LocalDateTime.now()
    }

    // TODO: геттеры id, createdAt, updatedAt
}
