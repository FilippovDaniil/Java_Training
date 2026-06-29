package m59_spring_core_intro.practice.task04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ============================================================
// Конфигурация — дополнить (TODO)
// ============================================================

// TODO: @Configuration
@Configuration
class AppConfig {

    // TODO: @Bean
    @Bean
    public ProductRepository productRepository() {
        return new InMemoryProductRepository();
    }

    // TODO: @Bean — DI через вызов метода: productRepository()
    @Bean
    public ProductService productService() {
        // TODO: return new ProductService(productRepository());
        return new ProductService(productRepository());
    }

    // TODO: @Bean — DI через параметр метода
    @Bean
    public PriceService priceService(ProductRepository productRepository) {
        // TODO: return new PriceService(productRepository);
        return new PriceService(productRepository);
    }
}
