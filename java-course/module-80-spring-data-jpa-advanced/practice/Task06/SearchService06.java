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

@Service
class SearchService06 {
    private final ProductRepository06 repo;
    SearchService06(ProductRepository06 repo) { this.repo = repo; }

    public List<Product06> search(String category, Long minPrice) {
        // TODO: Specification<Product06> spec = Specification
        //          .where(ProductSpecs06.hasCategory(category))
        //          .and(ProductSpecs06.priceAtLeast(minPrice));
        // TODO: return repo.findAll(spec);
        return null;
    }
}
