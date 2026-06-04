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
class Runner04 implements CommandLineRunner {
    private final ProductRepository04 repo;
    Runner04(ProductRepository04 repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(new Product04("A", 100), new Product04("B", 5000), new Product04("C", 2000)));
        // TODO: repo.summaries(1000).forEach(s -> System.out.println(s.name() + " = " + s.price()));
    }
}
