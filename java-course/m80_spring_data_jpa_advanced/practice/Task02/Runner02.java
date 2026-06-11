package m80_spring_data_jpa_advanced.practice.task02;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
class Runner02 implements CommandLineRunner {
    private final ProductRepository02 repo;
    Runner02(ProductRepository02 repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(new Product02("A", 1000), new Product02("B", 3000), new Product02("C", 500)));
        // TODO: выведите repo.nativeExpensive(800)
        // TODO: выведите repo.averagePrice()
    }
}
