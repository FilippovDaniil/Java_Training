import java.util.HashMap;
import java.util.Map;

class InMemoryOrderRepository implements OrderRepository {
    private final Map<String, Order> store = new HashMap<>();

    @Override
    public void save(Order order) {
        // TODO: положить по order.getId()
    }

    @Override
    public Order findById(String id) {
        // TODO: вернуть по id
        return null;
    }
}
