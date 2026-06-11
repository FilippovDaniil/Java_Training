package m80_spring_data_jpa_advanced.practice.task03;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
class Runner03 implements CommandLineRunner {
    private final ProductRepository03 repo;
    Runner03(ProductRepository03 repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(
                new Product03("Ноутбук", 80000, "Электроника"),
                new Product03("Мышь", 900, "Электроника")));
        // TODO: repo.findByCategory("Электроника").forEach(v -> System.out.println(v.getName() + " = " + v.getPrice()));
    }
}
