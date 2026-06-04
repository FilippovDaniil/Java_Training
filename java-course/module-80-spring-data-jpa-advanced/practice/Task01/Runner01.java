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
class Runner01 implements CommandLineRunner {
    private final ProductRepository01 repo;
    Runner01(ProductRepository01 repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(
                new Product01("Ноутбук Lenovo", 80000, "Электроника"),
                new Product01("Мышь", 900, "Электроника"),
                new Product01("Java book", 1500, "Книги")));
        // TODO: выведите repo.expensiveInCategory(1000, "Электроника")
        // TODO: выведите repo.searchByName("book")
    }
}
