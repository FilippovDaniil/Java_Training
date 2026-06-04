import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class ProductService02 {
    private final ProductRepository02 repo;
    ProductService02(ProductRepository02 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void rename(Long id, String newName) {
        // TODO: Product02 p = repo.findById(id).orElseThrow();
        // TODO: p.setName(newName);   // БЕЗ save() — сработает dirty checking
    }
}
