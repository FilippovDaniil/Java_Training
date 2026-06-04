import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

// TODO: @Component
class SearchRunner07 implements CommandLineRunner {

    // TODO: внедрите ProductRepository07 (сидинг) и CatalogSearchService07

    @Override
    public void run(String... args) {
        // TODO: засейте ~6 товаров (разные категории/цены/наличие)
        // TODO: вызовите search/cards/filter/applyDiscount и выведите результаты
    }
}
