import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

// ============================================================
// Конфигурация — заполнить (TODO)
// ============================================================

// TODO: @Configuration
class AppConfig {

    // TODO: @Bean
    public ProductRepository productRepository() {
        // TODO: return new InMemoryProductRepository();
        return null;
    }

    // TODO: @Bean
    public ProductService productService() {
        // TODO: return new ProductService(productRepository());
        return null;
    }

    // TODO: @Bean
    public OrderService orderService() {
        // TODO: return new OrderService(productService());
        return null;
    }
}
