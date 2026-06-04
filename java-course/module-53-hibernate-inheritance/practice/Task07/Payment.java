import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// ============================================================
// Базовый класс иерархии (дополните аннотациями @Entity @Inheritance)
// ============================================================

@Entity
// TODO: @Table(name = "payments") если SINGLE_TABLE; оставьте только аннотации корня
// TODO: @Inheritance(strategy = ...) — SINGLE_TABLE или JOINED
// TODO: если SINGLE_TABLE — добавьте @DiscriminatorColumn
abstract class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String currency = "RUB";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // TODO: конструктор(amount, currency, status), геттеры, toString()
}
