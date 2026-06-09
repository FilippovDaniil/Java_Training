import java.util.HashMap;
import java.util.List;
import java.util.Map;

class InMemoryProductRepository implements ProductRepository {
    private final Map<String, Product> store = new HashMap<>();

    @Override
    public void add(Product product) {
        // TODO: положить по product.getId()
    }

    @Override
    public Product findById(String id) {
        // TODO: вернуть по id
        return null;
    }

    @Override
    public List<Product> all() {
        // TODO: вернуть List.copyOf(store.values())
        return List.of();
    }
}
