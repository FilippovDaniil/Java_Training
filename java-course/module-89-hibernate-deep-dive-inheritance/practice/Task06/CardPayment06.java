import jakarta.persistence.*;
import java.util.List;

@Entity @DiscriminatorValue("CARD")
class CardPayment06 extends Payment06 {
    private String cardNumber;
    protected CardPayment06() {}
    public CardPayment06(long amount, String cardNumber) { super(amount); this.cardNumber = cardNumber; }
}
