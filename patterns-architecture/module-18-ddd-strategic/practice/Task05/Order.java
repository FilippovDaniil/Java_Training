import java.util.ArrayList;
import java.util.List;

// Aggregate Root: единая точка доступа, стережёт инварианты.
class Order {
    private final List<OrderLine> lines = new ArrayList<>();
    private boolean confirmed = false;

    public void addLine(String sku, int qty, long priceCents) {
        // TODO: если confirmed → IllegalStateException; иначе добавить new OrderLine(...)
    }

    public void confirm() {
        // TODO: confirmed = true
    }

    public long total() {
        // TODO: сумма subtotal() всех строк
        return 0;
    }

    public List<OrderLine> lines() {
        // TODO: вернуть КОПИЮ списка (List.copyOf), чтобы нельзя было менять извне
        return List.of();
    }
}
