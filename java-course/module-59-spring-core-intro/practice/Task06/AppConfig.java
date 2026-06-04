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
class AppConfig {

    // TODO: @Bean("inMemoryRepo")
    public ProductRepository inMemoryProductRepository() {
        // TODO: return new InMemoryProductRepository();
        return null;
    }

    // TODO: @Bean("fileRepo")
    public ProductRepository fileProductRepository() {
        // TODO: return new FileProductRepository();
        return null;
    }

    // TODO: @Bean + @Qualifier("inMemoryRepo") на параметре
    public ProductService productService(ProductRepository repo) {
        // TODO: return new ProductService(repo);
        return null;
    }
}
