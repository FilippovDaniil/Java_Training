package m20_event_sourcing_cqrs.practice.task06;

// Read-модель: баланс как проекция лога событий.
class BalanceProjection {
    private long balance = 0;

    public void on(Object event) {
        // TODO: Deposited → += amount; Withdrawn → -= amount
    }

    public long balance() {
        // TODO
        return balance;
    }
}
