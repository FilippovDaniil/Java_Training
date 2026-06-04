import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
class Runner02 implements CommandLineRunner {
    private final CategoryRepository02 repo;
    private final CatalogService02 service;
    Runner02(CategoryRepository02 repo, CatalogService02 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        Category02 c = new Category02("Книги");
        c.addProduct(new Product02("Java")); c.addProduct(new Product02("SQL"));
        Long id = repo.save(c).getId();
        // TODO: service.brokenRead(id);            // ожидается "Поймано: LazyInitializationException"
        // TODO: System.out.println("fixed = " + service.fixedRead(id));  // ожидается 2
    }
}
