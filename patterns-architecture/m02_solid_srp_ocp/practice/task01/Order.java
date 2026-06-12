package m02_solid_srp_ocp.practice.task01;

import java.util.List;

/**
 * Класс-данные (готов, менять не нужно). Это «глупая» структура: только
 * данные заказа и геттеры, без бизнес-правил, форматирования и хранения.
 */
class Order {
    private final String id;
    private final List<Long> itemPricesCents;

    Order(String id, List<Long> itemPricesCents) {
        this.id = id;
        this.itemPricesCents = List.copyOf(itemPricesCents);
    }

    String getId() { return id; }
    List<Long> getItemPricesCents() { return itemPricesCents; }
}
