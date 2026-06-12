package m15_layered_mvc_clean.practice.task07;

import java.util.HashMap;
import java.util.Map;

class InMemoryAccountRepository implements AccountRepository {
    private final Map<String, Account> store = new HashMap<>();

    @Override
    public Account findById(String id) {
        // TODO: вернуть по id
        return null;
    }

    @Override
    public void save(Account account) {
        // TODO: положить по account.getId()
    }
}
