package m23_microservices_vs_monolith.practice.task07;

// Фасад координирует модули; зависит только от их интерфейсов.
class BankingFacade {
    // TODO: поля final AccountModule accounts, final NotificationModule notifications + конструктор

    public void withdraw(String accountId, long amount) {
        // TODO: accounts.debit(accountId, amount);
        //       notifications.notify("списано " + amount + ", остаток " + accounts.balance(accountId))
    }
}
