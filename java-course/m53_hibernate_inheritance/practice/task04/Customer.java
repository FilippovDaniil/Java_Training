package m53_hibernate_inheritance.practice.task04;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// --- Customer (дополните) ---

@Entity
@Table(name = "customers")
class Customer extends BaseAuditEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // TODO: конструктор(name, email), геттеры, toString()
}
