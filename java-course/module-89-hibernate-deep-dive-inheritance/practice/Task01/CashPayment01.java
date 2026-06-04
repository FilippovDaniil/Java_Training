import jakarta.persistence.*;

@Entity
// TODO: @DiscriminatorValue("CASH")
class CashPayment01 extends Payment01 {
    private String cashier;
    protected CashPayment01() {}
    public CashPayment01(long amount, String cashier) { super(amount); this.cashier = cashier; }
}
