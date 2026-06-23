package m53_hibernate_inheritance.practice.task04;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// ============================================
// ORDER - исправленный toString()
// ============================================

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

    public Order() {}

    public Order(BigDecimal totalAmount, String status, Customer customer) {
        this.totalAmount = totalAmount;
        this.status = status;
        this.customer = customer;
    }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    @Override
    public String toString() {
        // ✅ НЕ вызываем customer.toString() чтобы избежать циклической ссылки
        // Используем только ID клиента
        return String.format("Order{id=%d, totalAmount=%s, status='%s', customerId=%d, createdAt=%s, updatedAt=%s}",
                getId(), totalAmount, status, customer != null ? customer.getId() : null, getCreatedAt(), getUpdatedAt());
    }
}