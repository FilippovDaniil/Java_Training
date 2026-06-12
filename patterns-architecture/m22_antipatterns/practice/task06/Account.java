package m22_antipatterns.practice.task06;

// Богатый счёт: операции и инвариант внутри; баланс — Value Object Money.
class Account {
    // TODO: поля id (String), Money balance + конструктор Account(String id, Money initialBalance)

    public Money balance() {
        // TODO: вернуть balance
        return null;
    }

    public void deposit(Money amount) {
        // TODO: balance = balance.plus(amount)
    }

    public void withdraw(Money amount) {
        // TODO: если balance.isLessThan(amount) → IllegalStateException;
        //       иначе balance = balance.minus(amount)
    }
}
