import jakarta.persistence.*;
import java.util.Objects;

// TODO: @Embeddable
class Money02 {
    private long amount;
    private String currency;
    protected Money02() {}
    public Money02(long amount, String currency) { this.amount = amount; this.currency = currency; }
    public long getAmount() { return amount; }
    public String getCurrency() { return currency; }
    // TODO: equals/hashCode ПО ЗНАЧЕНИЮ:
    // TODO: @Override public boolean equals(Object o) {
    // TODO:     return o instanceof Money02 m && amount == m.amount && Objects.equals(currency, m.currency);
    // TODO: }
    // TODO: @Override public int hashCode() { return Objects.hash(amount, currency); }
}
