package m01_oop_principles.practice.task06;

abstract class Account {
    // TODO: приватное поле balanceCents (long)

    // TODO: конструктор Account(long initialBalanceCents)

    public void deposit(long amountCents) {
        // TODO: проверить amountCents > 0, увеличить баланс
    }

    public void withdraw(long amountCents) {
        // TODO: проверить amountCents > 0 и достаточность средств, уменьшить баланс
    }

    public long getBalanceCents() {
        // TODO: вернуть баланс
        return 0;
    }

    // месячное начисление/списание — у каждого типа счёта своё
    public abstract void applyMonthly();

    // вспомогательный protected-метод, чтобы подтипы могли менять баланс
    // через контролируемую точку (не делая поле доступным напрямую)
    protected void adjustBalance(long deltaCents) {
        // TODO: изменить balanceCents на delta (может быть + или -)
    }
}
