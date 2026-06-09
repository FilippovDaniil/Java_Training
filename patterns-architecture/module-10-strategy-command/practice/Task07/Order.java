import java.util.ArrayList;
import java.util.List;

class Order {
    private final List<Long> items = new ArrayList<>();
    // TODO: поле DiscountStrategy discount (по умолчанию NoDiscount)

    public void addItem(long priceCents) {
        // TODO: добавить позицию
    }

    public void removeLastItem() {
        // TODO: удалить последнюю позицию (если список не пуст)
    }

    public void setDiscount(DiscountStrategy discount) {
        // TODO: сохранить стратегию
    }

    public long total() {
        // TODO: сумма позиций → discount.apply(сумма)
        return 0;
    }
}
