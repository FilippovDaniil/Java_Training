import java.util.ArrayList;
import java.util.List;

// Агрегат: инварианты + запись доменных событий.
class Account {
    // TODO: поля id (String), balanceCents (long) + конструктор Account(String id, long balanceCents)
    private final List<Object> events = new ArrayList<>();

    public String getId() { return ""; }   // TODO
    public long balance() { return 0; }     // TODO

    public void deposit(long amount) {
        // TODO: amount > 0 → увеличить баланс; events.add(new MoneyDeposited(id, amount))
    }

    public void withdraw(long amount) {
        // TODO: amount > 0 и хватает средств (иначе IllegalStateException);
        //       уменьшить баланс; events.add(new MoneyWithdrawn(id, amount))
    }

    public List<Object> pullEvents() {
        // TODO: copy = List.copyOf(events); events.clear(); вернуть copy
        return List.of();
    }
}
