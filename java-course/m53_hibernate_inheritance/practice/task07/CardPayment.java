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
@DiscriminatorValue("CARD")
class CardPayment extends Payment {

    @Column(name = "masked_card_number")
    private String maskedCardNumber;

    @Column(name = "card_holder_name")
    private String cardHolderName;

    @Column(name = "expiry_date")
    private String expiryDate;

    @Column(name = "authorization_code")
    private String authorizationCode;

    public CardPayment() {}

    public CardPayment(BigDecimal amount, String maskedCardNumber, String cardHolderName, String expiryDate) {
        super(amount, "RUB", PaymentStatus.PENDING);
        this.maskedCardNumber = maskedCardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
    }

    public String getMaskedCardNumber() { return maskedCardNumber; }
    public void setMaskedCardNumber(String maskedCardNumber) { this.maskedCardNumber = maskedCardNumber; }

    public String getCardHolderName() { return cardHolderName; }
    public void setCardHolderName(String cardHolderName) { this.cardHolderName = cardHolderName; }

    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

    public String getAuthorizationCode() { return authorizationCode; }
    public void setAuthorizationCode(String authorizationCode) { this.authorizationCode = authorizationCode; }

    @Override
    public String toString() {
        return String.format("CardPayment{id=%d, amount=%s, status=%s, card='%s'}",
                getId(), getAmount(), getStatus(), maskedCardNumber);
    }
}

