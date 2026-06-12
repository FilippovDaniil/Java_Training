package m18_ddd_strategic.practice.task07;

import java.util.Objects;

final class Money {
    // TODO: приватные final поля amountCents (long), currency (String) + конструктор (amount >= 0)

    public Money plus(Money other) {
        // TODO: проверить совпадение валют; new Money(сумма, currency)
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
        // TODO: Objects.hash(amountCents, currency)
        return 0;
    }

    @Override
    public String toString() {
        // TODO: amountCents + " " + currency
        return "";
    }
}
