import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
class Runner05 implements CommandLineRunner {
    private final ProductRepository05 repo;
    private final PriceService05 service;
    Runner05(ProductRepository05 repo, PriceService05 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(
                new Product05("Мышь", 1000, "Электроника"),
                new Product05("Клавиатура", 2000, "Электроника"),
                new Product05("Книга", 500, "Книги")));
        // TODO: int changed = service.raise("Электроника", 1.1); System.out.println("Изменено: " + changed);
        // TODO: repo.findAll().forEach(p -> System.out.println(p.getName() + " = " + p.getPrice()));
    }
}
