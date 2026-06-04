import jakarta.persistence.*;
import java.util.List;

// TODO: @Entity @DiscriminatorValue("CARD")
class CardPayment07 extends Payment07 {
    private String cardNumber;
    protected CardPayment07() {}
    public CardPayment07(long amount, String cardNumber) { super(amount); this.cardNumber = cardNumber; }
    // TODO: @Override public String describe() {
    // TODO:     return "Карта ****" + cardNumber.substring(cardNumber.length() - 4);
    // TODO: }
}
