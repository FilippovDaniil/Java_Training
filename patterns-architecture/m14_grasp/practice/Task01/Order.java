package m14_grasp.practice.task01;

import java.util.ArrayList;
import java.util.List;

class Order {
    private final List<OrderLine> lines = new ArrayList<>();

    public void addLine(OrderLine line) {
        // TODO: добавить строку
    }

    // заказ — эксперт по своей сумме
    public long total() {
        // TODO: просуммировать subtotal() всех строк
        return 0;
    }
}
