package m19_ddd_tactical.practice.task03;

class Account {
    // TODO: поля id (String), balanceCents (long) + конструктор Account(String id, long balanceCents)

    public String getId() { return ""; }   // TODO
    public long balance() { return 0; }     // TODO

    public void deposit(long amount) {
        // TODO: amount > 0 → увеличить баланс
    }

    public void withdraw(long amount) {
        // TODO: amount > 0 и хватает средств, иначе IllegalStateException; уменьшить баланс
    }
}
