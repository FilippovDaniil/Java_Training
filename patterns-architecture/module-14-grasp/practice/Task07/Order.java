import java.util.ArrayList;
import java.util.List;

class Order {
    // TODO: поле id (String) + конструктор Order(String id)
    private final List<OrderLine> lines = new ArrayList<>();

    public String getId() {
        // TODO
        return "";
    }

    // Creator: Order создаёт свои строки
    public void addLine(String sku, int qty, long priceCents) {
        // TODO: lines.add(new OrderLine(sku, qty, priceCents))
    }

    // Information Expert: Order считает свою сумму
    public long total() {
        // TODO: сумма subtotal() всех строк
        return 0;
    }
}
