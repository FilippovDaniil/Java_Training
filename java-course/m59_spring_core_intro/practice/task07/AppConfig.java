package m59_spring_core_intro.practice.task07;

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
@Configuration
class AppConfig {

    // TODO: @Bean
    @Bean
    public ProductRepository productRepository() {
        // TODO: return new InMemoryProductRepository();
        return new InMemoryProductRepository();
    }

    // TODO: @Bean
    @Bean
    public ProductService productService() {
        // TODO: return new ProductService(productRepository());
        return new ProductService(productRepository());
    }

    // TODO: @Bean
    @Bean
    public OrderService orderService() {
        // TODO: return new OrderService(productService());
        return new OrderService(productService());
    }
}
