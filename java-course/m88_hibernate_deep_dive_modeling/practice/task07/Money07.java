package m88_hibernate_deep_dive_modeling.practice.task07;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.annotations.NaturalId;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// TODO: @Embeddable
@Embeddable
class Money07 {
    private long amount;
    private String currency;
    protected Money07() {}
    public Money07(long amount, String currency) { this.amount = amount; this.currency = currency; }
    public long getAmount() { return amount; }
    public String getCurrency() { return currency; }
    // TODO: equals/hashCode по значению (amount, currency)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money07 money = (Money07) o;
        return amount == money.amount && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}
