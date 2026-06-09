import java.util.Objects;

final class Email {
    // TODO: приватное final поле value (String)

    public Email(String raw) {
        // TODO: проверить непустоту и наличие '@' и '.' (иначе IllegalArgumentException("неверный e-mail"));
        //       нормализовать к нижнему регистру и сохранить
    }

    @Override
    public boolean equals(Object o) {
        // TODO: равенство по value
        return false;
    }

    @Override
    public int hashCode() {
        // TODO: Objects.hash(value)
        return 0;
    }

    @Override
    public String toString() {
        // TODO: вернуть value
        return "";
    }
}
