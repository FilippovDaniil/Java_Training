package m23_microservices_vs_monolith.practice.task04;

// Единый вход, координирующий несколько модулей (Facade на уровне сервисов).
class ApiGateway {
    // TODO: поля final AccountService accounts, final AuditService audit + конструктор

    public long viewBalance(String accountId) {
        // TODO: audit.record("просмотр баланса " + accountId); вернуть accounts.balance(accountId)
        return 0;
    }
}
