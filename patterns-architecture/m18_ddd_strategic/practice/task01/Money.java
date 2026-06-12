package m18_ddd_strategic.practice.task01;

import java.util.Objects;

// Value Object: неизменяем, равенство по значению.
final class Money {
    // TODO: приватные final поля amountCents (long), currency (String)

    public Money(long amountCents, String currency) {
        // TODO: проверить amountCents >= 0 и currency не пустой; задать поля
    }

    public Money plus(Money other) {
        // TODO: проверить совпадение валют; вернуть new Money(сумма, currency)
        return null;
    }

    @Override
    public boolean equals(Object o) {
        // TODO: равенство по amountCents + currency
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
