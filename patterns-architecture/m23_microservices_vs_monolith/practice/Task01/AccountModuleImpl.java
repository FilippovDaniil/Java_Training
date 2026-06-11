package m23_microservices_vs_monolith.practice.task01;

import java.util.HashMap;
import java.util.Map;

// Реализация модуля: внутреннее хранилище скрыто от других модулей.
class AccountModuleImpl implements AccountModule {
    private final Map<String, Long> balances = new HashMap<>();

    public void seed(String accountId, long balance) {
        // TODO: положить баланс по id (наполнение для примера)
    }

    @Override
    public long balance(String accountId) {
        // TODO: вернуть баланс по id (0, если нет)
        return 0;
    }
}
