import java.util.Objects;

// Entity: идентичность по id; имя может меняться, личность та же.
class Customer {
    // TODO: final поле id (String) и изменяемое поле name (String) + конструктор

    public void rename(String newName) {
        // TODO: сменить имя (id не трогаем)
    }

    public String getId() { return ""; }    // TODO
    public String getName() { return ""; }   // TODO

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
