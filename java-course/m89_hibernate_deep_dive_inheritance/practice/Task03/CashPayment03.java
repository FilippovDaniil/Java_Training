package m89_hibernate_deep_dive_inheritance.practice.task03;

import jakarta.persistence.*;

@Entity @Table(name = "cash_payments")
class CashPayment03 extends Payment03 {
    private String cashier;
    protected CashPayment03() {}
    public CashPayment03(long amount, String cashier) { super(amount); this.cashier = cashier; }
}
