import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

// Класс под тестом (готов, менять не нужно)
class ShoppingCart {
    private final java.util.Map<String, Integer> items = new java.util.HashMap<>();
    private final java.util.Map<String, Double> prices = new java.util.HashMap<>();

    void addItem(String name, double price, int qty) {
        if (qty <= 0) throw new IllegalArgumentException("Количество должно быть > 0");
        items.merge(name, qty, Integer::sum);
        prices.put(name, price);
    }

    void removeItem(String name) {
        items.remove(name);
        prices.remove(name);
    }

    int itemCount() { return items.size(); }

    boolean isEmpty() { return items.isEmpty(); }

    double total() {
        double sum = 0;
        for (var e : items.entrySet()) {
            sum += prices.get(e.getKey()) * e.getValue();
        }
        return sum;
    }

    double applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Скидка должна быть в диапазоне 0..100");
        }
        return total() * (1 - percent / 100);
    }
}
