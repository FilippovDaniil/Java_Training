package m59_spring_core_intro.practice.task03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ============================================================
// Конфигурация (готова — изучите, не менять)
// ============================================================

@Configuration
class AppConfig {

    @Bean
    public ProductRepository productRepository() {
        return new InMemoryProductRepository();
    }

    @Bean
    public ProductService productService() {
        // Spring перехватит вызов productRepository() и вернёт singleton
        return new ProductService(productRepository());
    }
}
