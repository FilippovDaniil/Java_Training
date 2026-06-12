package m23_microservices_vs_monolith.practice.task02;

// Модуль «Платежи»: зависит от модуля «Счета» только через его интерфейс.
class PaymentModule {
    // TODO: поле final AccountModule accounts + конструктор PaymentModule(AccountModule accounts)

    public boolean canPay(String accountId, long amount) {
        // TODO: accounts.balance(accountId) >= amount
        return false;
    }
}
