import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
class Runner04 implements CommandLineRunner {
    private final ProductRepository04 repo;
    private final DeleteService04 service;
    Runner04(ProductRepository04 repo, DeleteService04 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(
                new Product04("Мышь", "Электроника", "SKU-1"),
                new Product04("Клавиатура", "Электроника", "SKU-2"),
                new Product04("Java book", "Книги", "SKU-3")));
        // TODO: выведите countByCategory("Электроника") и existsBySku("SKU-1")
        // TODO: вызовите service.purge("Электроника"), затем выведите repo.count()
    }
}
