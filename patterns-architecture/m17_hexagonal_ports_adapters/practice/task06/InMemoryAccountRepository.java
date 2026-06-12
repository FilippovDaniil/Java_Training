package m17_hexagonal_ports_adapters.practice.task06;

import java.util.HashMap;
import java.util.Map;

class InMemoryAccountRepository implements AccountRepository {
    private final Map<String, Account> store = new HashMap<>();

    @Override
    public Account load(String id) {
        // TODO: вернуть по id
        return null;
    }

    @Override
    public void save(Account account) {
        // TODO: положить по account.id()
    }
}
