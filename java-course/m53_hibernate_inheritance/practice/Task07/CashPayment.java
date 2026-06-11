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
// TODO: @Table(name = "cash_payments") — только если JOINED
// TODO: @DiscriminatorValue("CASH") — только если SINGLE_TABLE
class CashPayment extends Payment {
    @Column(name = "courier_name")
    private String courierName;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "change_required")
    private BigDecimal changeRequired;

    // TODO: конструктор(amount, deliveryAddress, changeRequired), геттеры, toString()
}
