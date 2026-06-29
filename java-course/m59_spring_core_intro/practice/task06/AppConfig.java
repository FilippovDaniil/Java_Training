package m59_spring_core_intro.practice.task06;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

// ============================================================
// Конфигурация — заполнить (TODO)
// ============================================================

// TODO: @Configuration
@Configuration
class AppConfig {

    // TODO: @Bean("inMemoryRepo")
    @Bean("inMemoryRepo")
    public ProductRepository inMemoryProductRepository() {
        // TODO: return new InMemoryProductRepository();
        return new InMemoryProductRepository();
    }

    // TODO: @Bean("fileRepo")
    @Bean("fileRepo")
    public ProductRepository fileProductRepository() {
        // TODO: return new FileProductRepository();
        return new FileProductRepository();
    }

    // TODO: @Bean + @Qualifier("inMemoryRepo") на параметре
    @Bean
    public ProductService productService(@Qualifier("fileRepo") ProductRepository repo) {
        // TODO: return new ProductService(repo);
        return new ProductService(repo);
    }

    @Bean("BeanServiceFile")
    public ProductService productServiceFile() {
        // TODO: return new ProductService(repo);
        return new ProductService(inMemoryProductRepository());
    }
}
