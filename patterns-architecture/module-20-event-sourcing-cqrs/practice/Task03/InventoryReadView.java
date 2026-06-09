import java.util.HashMap;
import java.util.Map;

// Read-модель: только запросы; обновляется write-стороной.
class InventoryReadView {
    private final Map<String, Integer> qty = new HashMap<>();

    public void update(String sku, int quantity) {
        // TODO: записать актуальное количество по sku
    }

    public int qty(String sku) {
        // TODO: вернуть количество (0, если нет)
        return 0;
    }
}
