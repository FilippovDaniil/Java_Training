import jakarta.persistence.*;
import java.util.List;

@Entity @DiscriminatorValue("CASH")
class CashPayment06 extends Payment06 {
    private String cashier;
    protected CashPayment06() {}
    public CashPayment06(long amount, String cashier) { super(amount); this.cashier = cashier; }
}
