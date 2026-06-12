package m59_spring_core_intro.practice.task02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void listAll() {
        System.out.println("=== Список товаров ===");
        repository.findAll().forEach(System.out::println);
    }
}
