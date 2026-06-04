import jakarta.persistence.*;

@Entity @Table(name = "cash_payments")
class CashPayment02 extends Payment02 {
    // TODO: @Column(nullable = false)
    private String cashier;
    protected CashPayment02() {}
    public CashPayment02(long amount, String cashier) { super(amount); this.cashier = cashier; }
}
