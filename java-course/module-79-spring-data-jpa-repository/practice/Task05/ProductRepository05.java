import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

interface ProductRepository05 extends JpaRepository<Product05, Long> {
    // TODO: List<Product05> findTop3ByOrderByPriceDesc();
    // TODO: Optional<Product05> findFirstByOrderByPriceAsc();
}
