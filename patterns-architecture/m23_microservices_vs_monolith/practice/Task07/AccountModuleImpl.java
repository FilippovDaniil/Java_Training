package m23_microservices_vs_monolith.practice.task07;

import java.util.HashMap;
import java.util.Map;

class AccountModuleImpl implements AccountModule {
    private final Map<String, Long> balances = new HashMap<>();

    public void seed(String accountId, long balance) {
        // TODO: положить баланс
    }

    @Override
    public void debit(String accountId, long amount) {
        // TODO: если средств не хватает → IllegalStateException; иначе уменьшить баланс
    }

    @Override
    public long balance(String accountId) {
        // TODO: вернуть баланс (0, если нет)
        return 0;
    }
}
