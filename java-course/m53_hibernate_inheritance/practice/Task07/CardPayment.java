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
// CardPayment (дополните @Entity и уникальными полями)
// ============================================================

@Entity
// TODO: @Table(name = "card_payments") — только если JOINED
// TODO: @DiscriminatorValue("CARD") — только если SINGLE_TABLE
class CardPayment extends Payment {
    @Column(name = "masked_card_number")
    private String maskedCardNumber;

    @Column(name = "card_holder_name")
    private String cardHolderName;

    @Column(name = "expiry_date")
    private String expiryDate;

    @Column(name = "authorization_code")
    private String authorizationCode; // заполняется при успехе

    // TODO: конструктор(amount, maskedCardNumber, cardHolderName, expiryDate), геттеры, toString()
}
