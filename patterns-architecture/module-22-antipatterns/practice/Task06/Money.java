import java.util.Objects;

final class Money {
    // TODO: неизменяемые amountCents (long), currency (String) + конструктор (amount >= 0)

    public Money plus(Money other) {
        // TODO: проверить валюты; new Money(сумма, currency)
        return null;
    }

    public Money minus(Money other) {
        // TODO: new Money(amountCents - other.amountCents, currency) (не ниже 0)
        return null;
    }

    public boolean isLessThan(Money other) {
        // TODO: сравнить amountCents (валюты совпадают)
        return false;
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
