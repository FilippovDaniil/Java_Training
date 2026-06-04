import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ============================================================
// Конфигурация — заполнить (TODO)
// ============================================================

// TODO: @Configuration
class AppConfig {

    // TODO: @Bean
    public ProductRepository productRepository() {
        // TODO: вернуть new InMemoryProductRepository()
        return null;
    }

    // TODO: @Bean
    public ProductService productService() {
        // TODO: вернуть new ProductService(productRepository())
        return null;
    }
}
