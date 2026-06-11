package m89_hibernate_deep_dive_inheritance.practice.task03;

import jakarta.persistence.*;

@Entity @Table(name = "card_payments")
class CardPayment03 extends Payment03 {
    private String cardNumber;
    protected CardPayment03() {}
    public CardPayment03(long amount, String cardNumber) { super(amount); this.cardNumber = cardNumber; }
}
