import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;

interface ProductRepository03 extends JpaRepository<Product03, Long> {
    // TODO: List<Product03> findByCategoryAndPriceGreaterThan(String category, long price);
    // TODO: List<Product03> findByPriceBetween(long min, long max);
    // TODO: List<Product03> findByNameContainingIgnoreCase(String part);
    // TODO: List<Product03> findByCategoryOrderByPriceDesc(String category);
}
