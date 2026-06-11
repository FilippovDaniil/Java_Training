package m82_spring_data_jpa_lazy_loading.practice.task01;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
class Runner01 implements CommandLineRunner {
    private final CategoryRepository01 categories;
    private final ProductRepository01 products;
    Runner01(CategoryRepository01 c, ProductRepository01 p) { this.categories = c; this.products = p; }

    @Override
    @Transactional
    public void run(String... args) {
        Category01 electronics = categories.save(new Category01("Электроника"));
        // TODO: products.save(new Product01("Ноутбук", electronics));
        // TODO: products.save(new Product01("Телефон", electronics));
        // TODO: внутри транзакции выведите categories.findById(electronics.getId()).get().getProducts().size()
    }
}
