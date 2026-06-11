package m26_legacy_refactoring_capstone.practice.task07;

import java.util.HashMap;
import java.util.Map;

// Driven-адаптер порта OrderPricing.
class InMemoryPricing implements OrderPricing {
    private final Map<String, Long> prices = new HashMap<>();

    public void seed(String orderId, long price) {
        // TODO: положить цену по orderId
    }

    @Override
    public long priceOf(String orderId) {
        // TODO: вернуть цену по orderId (0, если нет)
        return 0;
    }
}
