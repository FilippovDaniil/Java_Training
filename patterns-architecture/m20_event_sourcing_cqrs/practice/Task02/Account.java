package m20_event_sourcing_cqrs.practice.task02;

import java.util.ArrayList;
import java.util.List;

class Account {
    private long balance = 0;
    private final List<Object> changes = new ArrayList<>();

    private void apply(Object event) {
        // TODO: Deposited → balance += amount; Withdrawn → balance -= amount
    }

    public void deposit(long amount) {
        // TODO: var e = new MoneyDeposited(amount); apply(e); changes.add(e)
    }

    public void withdraw(long amount) {
        // TODO: если средств не хватает → IllegalStateException;
        //       иначе var e = new MoneyWithdrawn(amount); apply(e); changes.add(e)
    }

    public long balance() {
        // TODO
        return balance;
    }

    public List<Object> changes() {
        // TODO: вернуть копию лога
        return List.copyOf(changes);
    }
}
