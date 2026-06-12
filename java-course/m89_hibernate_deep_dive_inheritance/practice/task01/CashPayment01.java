package m89_hibernate_deep_dive_inheritance.practice.task01;

import jakarta.persistence.*;

@Entity
// TODO: @DiscriminatorValue("CASH")
class CashPayment01 extends Payment01 {
    private String cashier;
    protected CashPayment01() {}
    public CashPayment01(long amount, String cashier) { super(amount); this.cashier = cashier; }
}
