package m53_hibernate_inheritance.practice.task07;

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
// CashPayment (дополните @Entity и уникальными полями)
// ============================================================

@Entity
@DiscriminatorValue("CASH")
class CashPayment extends Payment {

    @Column(name = "courier_name")
    private String courierName;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "change_required")
    private BigDecimal changeRequired;

    public CashPayment() {}

    public CashPayment(BigDecimal amount, String deliveryAddress, BigDecimal changeRequired) {
        super(amount, "RUB", PaymentStatus.PENDING);
        this.deliveryAddress = deliveryAddress;
        this.changeRequired = changeRequired;
    }

    public String getCourierName() { return courierName; }
    public void setCourierName(String courierName) { this.courierName = courierName; }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }

    public BigDecimal getChangeRequired() { return changeRequired; }
    public void setChangeRequired(BigDecimal changeRequired) { this.changeRequired = changeRequired; }

    @Override
    public String toString() {
        return String.format("CashPayment{id=%d, amount=%s, status=%s, address='%s'}",
                getId(), getAmount(), getStatus(), deliveryAddress);
    }
}