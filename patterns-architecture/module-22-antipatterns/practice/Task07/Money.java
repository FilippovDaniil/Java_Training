import java.util.Objects;

final class Money {
    // TODO: неизменяемые amountCents (long), currency (String) + конструктор (amount >= 0)

    public Money plus(Money other) {
        // TODO: проверить валюты; new Money(сумма, currency)
        return null;
    }

    public Money times(int qty) {
        // TODO: new Money(amountCents * qty, currency)
        return null;
    }

    @Override
    public boolean equals(Object o) {
        // TODO: по amountCents + currency
        return false;
    }

    @Override
    public int hashCode() {
        // TODO
        return 0;
    }

    @Override
    public String toString() {
        // TODO: amountCents + " " + currency
        return "";
    }
}
