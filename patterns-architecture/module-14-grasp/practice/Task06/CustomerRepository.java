import java.util.HashMap;
import java.util.Map;

// Pure Fabrication: искусственный класс, держащий обязанность хранения.
class CustomerRepository {
    private final Map<String, Customer> store = new HashMap<>();

    public void save(Customer customer) {
        // TODO: положить по customer.getId()
    }

    public Customer findById(String id) {
        // TODO: вернуть по id (или null)
        return null;
    }

    public int count() {
        // TODO: размер хранилища
        return 0;
    }
}
