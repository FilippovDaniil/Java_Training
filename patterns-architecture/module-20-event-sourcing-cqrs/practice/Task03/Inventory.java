import java.util.HashMap;
import java.util.Map;

// Write-модель: принимает команды, меняет состояние, синхронизирует read-модель.
class Inventory {
    private final Map<String, Integer> stock = new HashMap<>();
    // TODO: поле final InventoryReadView readView + конструктор Inventory(InventoryReadView readView)

    public void addStock(String sku, int qty) {
        // TODO: увеличить stock[sku] на qty; readView.update(sku, новое количество)
    }

    public void removeStock(String sku, int qty) {
        // TODO: уменьшить stock[sku] на qty; readView.update(sku, новое количество)
    }
}
