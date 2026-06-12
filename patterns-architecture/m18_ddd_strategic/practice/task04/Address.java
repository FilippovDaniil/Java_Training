package m18_ddd_strategic.practice.task04;

import java.util.Objects;

// Value Object: равенство по значению.
final class Address {
    // TODO: неизменяемые поля street, city + конструктор

    @Override
    public boolean equals(Object o) {
        // TODO: по street + city
        return false;
    }

    @Override
    public int hashCode() {
        // TODO: Objects.hash(street, city)
        return 0;
    }
}
