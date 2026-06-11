package m79_spring_data_jpa_repository.practice.task02;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
class QueryRunner02 implements CommandLineRunner {
    private final ProductRepository02 repo;
    QueryRunner02(ProductRepository02 repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(
                new Product02("Ноутбук", 80000, "Электроника", "SKU-1"),
                new Product02("Мышь", 900, "Электроника", "SKU-2"),
                new Product02("Java book", 1500, "Книги", "SKU-3")));
        // TODO: выведите repo.findByCategory("Электроника").size()
        // TODO: выведите дешёвые: repo.findByPriceLessThan(2000)
        // TODO: выведите repo.findBySku("SKU-1") (Optional)
    }
}
