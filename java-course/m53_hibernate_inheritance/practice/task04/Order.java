package m53_hibernate_inheritance.practice.task04;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// --- Order (дополните) ---

@Entity
@Table(name = "orders")
class Order extends BaseAuditEntity {

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // TODO: конструктор(totalAmount, status, customer), геттеры, toString()
}
