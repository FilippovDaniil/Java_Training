package m22_antipatterns.practice.task03;

import java.util.Objects;

final class Money {
    // TODO: неизменяемые amountCents (long), currency (String) + конструктор (amount >= 0, currency не пустой)

    public Money plus(Money other) {
        // TODO: проверить валюты; new Money(сумма, currency)
        return null;
    }

    @Override
    public boolean equals(Object o) {
        // TODO: по amountCents + currency
        return false;
    }

    @Override
    public int hashCode() {
        // TODO: Objects.hash(...)
        return 0;
    }

    @Override
    public String toString() {
        // TODO: amountCents + " " + currency
        return "";
    }
}
