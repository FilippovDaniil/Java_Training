package m17_hexagonal_ports_adapters.practice.task07;

// Доменная сущность ядра: инварианты баланса здесь.
class Account {
    // TODO: поля id (String), balanceCents (long) + конструктор Account(String id, long balanceCents)

    public String id() { return ""; }       // TODO
    public long balance() { return 0; }      // TODO

    public void deposit(long amount) {
        // TODO: проверить amount > 0, увеличить баланс
    }

    public void withdraw(long amount) {
        // TODO: проверить amount > 0 и достаточность; иначе IllegalStateException; уменьшить баланс
    }
}
