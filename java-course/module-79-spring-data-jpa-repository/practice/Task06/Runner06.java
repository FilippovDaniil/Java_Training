import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.IntStream;

@Component
class Runner06 implements CommandLineRunner {
    private final ProductRepository06 repo;
    Runner06(ProductRepository06 repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        List<Product06> items = IntStream.rangeClosed(1, 7)
                .mapToObj(i -> new Product06("Товар " + i, i * 1000L, "Электроника"))
                .toList();
        repo.saveAll(items);
        // TODO: Pageable pr = PageRequest.of(0, 3, Sort.by("price").descending());
        // TODO: Page<Product06> page = repo.findByCategory("Электроника", pr);
        // TODO: выведите size/totalElements/totalPages/hasNext
    }
}
