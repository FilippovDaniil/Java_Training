import jakarta.persistence.*;

@Entity @Table(name = "cash_payments")
class CashPayment03 extends Payment03 {
    private String cashier;
    protected CashPayment03() {}
    public CashPayment03(long amount, String cashier) { super(amount); this.cashier = cashier; }
}
