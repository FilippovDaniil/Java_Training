package m89_hibernate_deep_dive_inheritance.practice.task01;

import jakarta.persistence.*;

@Entity
// TODO: @DiscriminatorValue("CARD")
class CardPayment01 extends Payment01 {
    private String cardNumber;
    protected CardPayment01() {}
    public CardPayment01(long amount, String cardNumber) { super(amount); this.cardNumber = cardNumber; }
}
