import java.util.HashMap;
import java.util.Map;

class InMemoryStockRepository implements StockRepository {
    private final Map<String, Stock> store = new HashMap<>();

    @Override
    public Stock findById(String id) {
        // TODO: вернуть по id (можно computeIfAbsent → new Stock(id, 0))
        return null;
    }

    @Override
    public void save(Stock stock) {
        // TODO: положить по id
    }
}
