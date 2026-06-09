import java.util.ArrayList;
import java.util.List;

class Account {
    // TODO: поля id (String), balanceCents (long) + конструктор Account(String id)
    private final List<MoneyDeposited> events = new ArrayList<>();

    public long balance() { return 0; }   // TODO

    public void deposit(long amount) {
        // TODO: увеличить баланс И events.add(new MoneyDeposited(id, amount))
    }

    public List<MoneyDeposited> pullEvents() {
        // TODO: copy = List.copyOf(events); events.clear(); вернуть copy
        return List.of();
    }
}
