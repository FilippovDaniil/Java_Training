import jakarta.persistence.*;
import java.util.List;

// TODO: @Entity @DiscriminatorValue("CASH")
class CashPayment07 extends Payment07 {
    private String cashier;
    protected CashPayment07() {}
    public CashPayment07(long amount, String cashier) { super(amount); this.cashier = cashier; }
    // TODO: @Override public String describe() { return "Наличные, кассир " + cashier; }
}
