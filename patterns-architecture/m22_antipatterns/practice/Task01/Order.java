package m22_antipatterns.practice.task01;

import java.util.ArrayList;
import java.util.List;

// Богатая модель: поведение (total) живёт здесь, а не во внешнем сервисе.
class Order {
    private final List<OrderLine> lines = new ArrayList<>();

    public void addLine(OrderLine line) {
        // TODO: добавить строку
    }

    public long total() {
        // TODO: сумма subtotal() всех строк
        return 0;
    }
}
