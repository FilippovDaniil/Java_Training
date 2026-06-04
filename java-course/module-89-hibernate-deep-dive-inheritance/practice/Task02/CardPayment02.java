import jakarta.persistence.*;

@Entity @Table(name = "card_payments")
class CardPayment02 extends Payment02 {
    // TODO: @Column(nullable = false)  // в JOINED это можно
    private String cardNumber;
    protected CardPayment02() {}
    public CardPayment02(long amount, String cardNumber) { super(amount); this.cardNumber = cardNumber; }
}
