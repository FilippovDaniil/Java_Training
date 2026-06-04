import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

/** Строка заказа: товар + количество. */
class OrderLine {
    final String product;
    final int quantity;

    OrderLine(String product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("  %-15s x%d", product, quantity);
    }
}
