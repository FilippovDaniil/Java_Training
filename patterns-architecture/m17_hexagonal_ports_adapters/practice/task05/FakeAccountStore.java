package m17_hexagonal_ports_adapters.practice.task05;

import java.util.HashMap;
import java.util.Map;

// Fake-адаптер выходного порта: in-memory + учёт вызовов save (для проверки в тесте).
class FakeAccountStore implements AccountStore {
    private final Map<String, Account> store = new HashMap<>();
    private int saveCount = 0;

    // подготовка данных для теста
    public void put(Account account) {
        // TODO: положить по account.id()
    }

    @Override
    public Account load(String id) {
        // TODO: вернуть по id
        return null;
    }

    @Override
    public void save(Account account) {
        // TODO: увеличить saveCount и положить по id
    }

    public int saveCount() {
        return saveCount;
    }
}
