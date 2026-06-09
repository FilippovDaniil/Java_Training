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
