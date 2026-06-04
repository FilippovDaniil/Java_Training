import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
class PriceService05 {
    private final ProductRepository05 repo;
    PriceService05(ProductRepository05 repo) { this.repo = repo; }

    // TODO: @Transactional
    public int raise(String category, double factor) {
        // TODO: return repo.raisePrices(factor, category);
        return 0;
    }
}
