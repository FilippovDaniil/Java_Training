import java.util.ArrayList;
import java.util.List;

// Богатый агрегат: расчёт и инварианты внутри, цена — Money.
class Order {
    private final List<OrderLine> lines = new ArrayList<>();
    private boolean confirmed = false;

    public void addLine(String sku, int qty, Money unitPrice) {
        // TODO: если confirmed → IllegalStateException; иначе добавить new OrderLine(...)
    }

    public void confirm() {
        // TODO: confirmed = true
    }

    public Money total() {
        // TODO: начать с new Money(0, "RUB"), сложить subtotal() всех строк через plus()
        return null;
    }

    public List<OrderLine> lines() {
        // TODO: вернуть копию (List.copyOf)
        return List.of();
    }
}
