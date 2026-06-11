package m15_layered_mvc_clean.practice.task07;

// Persistence/домен: счёт с инвариантами баланса.
class Account {
    // TODO: поля id (String), balanceCents (long) + конструктор Account(String id)

    public String getId() { return ""; }          // TODO
    public long getBalanceCents() { return 0; }    // TODO

    public void deposit(long amountCents) {
        // TODO: проверить amount > 0, увеличить баланс
    }

    public void withdraw(long amountCents) {
        // TODO: проверить amount > 0 и достаточность; иначе IllegalStateException
    }
}
