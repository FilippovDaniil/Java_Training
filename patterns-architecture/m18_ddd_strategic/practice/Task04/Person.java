package m18_ddd_strategic.practice.task04;

import java.util.Objects;

// Entity: равенство по id.
class Person {
    // TODO: final id (String); изменяемые name (String) и address (Address) + конструктор

    public void moveTo(Address address) {
        // TODO: сменить адрес (id не меняется)
    }

    @Override
    public boolean equals(Object o) {
        // TODO: равенство ТОЛЬКО по id
        return false;
    }

    @Override
    public int hashCode() {
        // TODO: Objects.hash(id)
        return 0;
    }
}
