package m80_spring_data_jpa_advanced.practice.task07;

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

// TODO: @Service
class CatalogSearchService07 {

    // TODO: внедрите ProductRepository07

    public List<Product07> search(String kw) { return null; }                  // TODO
    public List<ProductCard07> cards() { return null; }                        // TODO
    public List<Product07> filter(String cat, Long min, Long max) { return null; } // TODO: Specification
    // TODO: @Transactional
    public int applyDiscount(String cat, double factor) { return 0; }          // TODO
}
