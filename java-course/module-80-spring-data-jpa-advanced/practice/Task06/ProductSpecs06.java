import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

class ProductSpecs06 {
    public static Specification<Product06> hasCategory(String category) {
        // TODO: return (root, query, cb) -> category == null ? null : cb.equal(root.get("category"), category);
        return null;
    }
    public static Specification<Product06> priceAtLeast(Long min) {
        // TODO: return (root, query, cb) -> min == null ? null : cb.greaterThanOrEqualTo(root.get("price"), min);
        return null;
    }
}
