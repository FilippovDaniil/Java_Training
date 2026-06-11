package m15_layered_mvc_clean.practice.task05;

import java.util.HashMap;
import java.util.Map;

// Adapter (внешний слой): реализует порт. Зависимость направлена внутрь — на интерфейс.
class InMemoryAccountGateway implements AccountGateway {
    private final Map<String, Account> store = new HashMap<>();

    public void put(Account account) {
        // TODO: положить по account.id() (вспомогательный метод для наполнения)
    }

    @Override
    public Account load(String id) {
        // TODO: вернуть по id
        return null;
    }
}
