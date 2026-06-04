import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ============================================================
// Конфигурация — дополнить (TODO)
// ============================================================

// TODO: @Configuration
class AppConfig {

    // TODO: @Bean
    public ProductRepository productRepository() {
        return new InMemoryProductRepository();
    }

    // TODO: @Bean — DI через вызов метода: productRepository()
    public ProductService productService() {
        // TODO: return new ProductService(productRepository());
        return null;
    }

    // TODO: @Bean — DI через параметр метода
    public PriceService priceService(ProductRepository productRepository) {
        // TODO: return new PriceService(productRepository);
        return null;
    }
}
