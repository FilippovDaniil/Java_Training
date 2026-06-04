import jakarta.persistence.*;

@Entity
// TODO: @DiscriminatorValue("CARD")
class CardPayment01 extends Payment01 {
    private String cardNumber;
    protected CardPayment01() {}
    public CardPayment01(long amount, String cardNumber) { super(amount); this.cardNumber = cardNumber; }
}
