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
// CUSTOMER - исправленный toString()
// ============================================

@Entity
@Table(name = "customers")
class Customer extends BaseAuditEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    public Customer() {}

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Order> getOrders() { return orders; }
    public void setOrders(List<Order> orders) { this.orders = orders; }

    @Override
    public String toString() {
        // ✅ НЕ вызываем orders.toString() чтобы избежать циклической ссылки
        return String.format("Customer{id=%d, name='%s', email='%s', createdAt=%s, updatedAt=%s}",
                getId(), name, email, getCreatedAt(), getUpdatedAt());
    }
}