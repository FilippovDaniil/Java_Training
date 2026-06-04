import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

/** Сервис заказов: зависит от ProductService — проверяет наличие перед заказом. */
class OrderService {
    private final ProductService productService;
    private final List<OrderLine> orders = new ArrayList<>();

    public OrderService(ProductService productService) {
        this.productService = productService;
    }

    /** Геттер нужен для проверки синглтона в задании. */
    public ProductService getProductService() {
        return productService;
    }

    public void placeOrder(String product, int quantity) {
        if (!productService.isAvailable(product)) {
            System.out.println("Товар не найден в каталоге: " + product);
            return;
        }
        orders.add(new OrderLine(product, quantity));
        System.out.printf("Заказ оформлен: %s x%d%n", product, quantity);
    }

    public void printOrders() {
        System.out.println("=== Все заказы (" + orders.size() + ") ===");
        orders.forEach(System.out::println);
    }
}
