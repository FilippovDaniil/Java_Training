package m59_spring_core_intro.practice.task07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

/** Сервис товаров: знает каталог, делегирует репозиторию. */
class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public boolean isAvailable(String name) {
        return repository.exists(name);
    }

    public void printCatalog() {
        System.out.println("--- Каталог товаров ---");
        repository.findAll().forEach(p -> System.out.println("  • " + p));
    }
}
