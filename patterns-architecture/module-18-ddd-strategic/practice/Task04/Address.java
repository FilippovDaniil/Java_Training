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
