package m15_layered_mvc_clean.practice.task01;

import java.util.HashMap;
import java.util.Map;

class InMemoryClientRepository implements ClientRepository {
    private final Map<String, Client> store = new HashMap<>();

    public void add(Client client) {
        // TODO: положить по id (вспомогательный метод для наполнения в main)
    }

    @Override
    public Client findById(String id) {
        // TODO: вернуть по id (или null)
        return null;
    }
}
