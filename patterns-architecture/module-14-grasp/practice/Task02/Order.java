import java.util.ArrayList;
import java.util.List;

class Order {
    private final List<OrderLine> lines = new ArrayList<>();

    // Creator: Order сам создаёт OrderLine (он их агрегирует).
    public void addLine(String sku, int qty, long priceCents) {
        // TODO: lines.add(new OrderLine(sku, qty, priceCents))
    }

    public int size() {
        // TODO: количество позиций
        return 0;
    }

    public long total() {
        // TODO: сумма subtotal() всех строк
        return 0;
    }
}
