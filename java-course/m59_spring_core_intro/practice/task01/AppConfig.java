package m59_spring_core_intro.practice.task01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ProductRepository productRepository(){
        return new DatabaseProductRepository();
    }

    public ProductService productService(){
        return new ProductService(productRepository());
    }
}
