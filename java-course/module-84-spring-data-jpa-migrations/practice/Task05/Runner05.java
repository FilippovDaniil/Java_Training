import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
class Runner05 implements CommandLineRunner {
    private final ProductRepository05 repo;
    private final ProductService05 service;
    Runner05(ProductRepository05 repo, ProductService05 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        Long id = repo.save(new Product05("Монитор", 15000)).getId();
        try {
            service.staleUpdate(id);
            System.out.println("Конфликт НЕ возник (неожиданно)");
        } catch (Exception e) {
            // TODO: System.out.println("Конфликт версий: " + e.getClass().getSimpleName());
        }
    }
}
