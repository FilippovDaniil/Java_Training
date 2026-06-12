package m20_event_sourcing_cqrs.practice.task05;

// Write-сторона: проверяет команду и порождает событие в хранилище.
class AccountCommandHandler {
    // TODO: поле final EventStore store + конструктор AccountCommandHandler(EventStore store)

    public void handle(DepositCommand cmd) {
        // TODO: если cmd.amount() <= 0 → throw new IllegalArgumentException("сумма должна быть > 0");
        //       иначе store.append(new MoneyDeposited(cmd.accountId(), cmd.amount()))
    }
}
