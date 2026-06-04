import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

interface ProductRepository02 extends JpaRepository<Product02, Long> {
    // TODO: List<Product02> findByCategory(String category);
    // TODO: List<Product02> findByPriceLessThan(long price);
    // TODO: Optional<Product02> findBySku(String sku);
}
