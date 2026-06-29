package m59_spring_core_intro.practice.task02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ============================================================
// Конфигурация — заполнить (TODO)
// ============================================================

// TODO: @Configuration
@Configuration
class AppConfig {

    // TODO: @Bean
    @Bean
    public ProductRepository productRepository() {
        // TODO: вернуть new InMemoryProductRepository()
        return new InMemoryProductRepository();
    }

    // TODO: @Bean
    @Bean
    public ProductService productService() {
        // TODO: вернуть new ProductService(productRepository())
        return new ProductService(productRepository());
    }
}
