import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
class Runner02 implements CommandLineRunner {
    private final ProductRepository02 repo;
    private final ProductService02 service;
    Runner02(ProductRepository02 repo, ProductService02 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        Product02 p = repo.save(new Product02("Старое имя"));
        // TODO: service.rename(p.getId(), "Новое имя");
        // TODO: System.out.println("Имя после rename: " + repo.findById(p.getId()).orElseThrow().getName());
    }
}
