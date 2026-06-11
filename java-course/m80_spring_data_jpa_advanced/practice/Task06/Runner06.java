package m80_spring_data_jpa_advanced.practice.task06;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

@Component
class Runner06 implements CommandLineRunner {
    private final ProductRepository06 repo;
    private final SearchService06 service;
    Runner06(ProductRepository06 repo, SearchService06 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(
                new Product06("Ноутбук", 80000, "Электроника"),
                new Product06("Мышь", 900, "Электроника"),
                new Product06("Java book", 1500, "Книги")));
        // TODO: выведите размеры результатов для search("Электроника", 1000L),
        //       search("Книги", null), search(null, 1000L), search(null, null)
    }
}
