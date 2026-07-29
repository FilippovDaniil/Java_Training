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

// ============ SPECIFICATIONS ============
class ProductSpecs07 {

    public static Specification<Product07> hasCategory(String cat) {
        if (cat == null || cat.isBlank()) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get("category"), cat);
    }

    public static Specification<Product07> priceBetween(Long min, Long max) {
        return (root, query, cb) -> {
            if (min == null && max == null) {
                return null;
            }
            if (min == null) {
                return cb.lessThanOrEqualTo(root.get("price"), max);
            }
            if (max == null) {
                return cb.greaterThanOrEqualTo(root.get("price"), min);
            }
            return cb.between(root.get("price"), min, max);
        };
    }

    public static Specification<Product07> availableOnly() {
        return (root, query, cb) -> cb.isTrue(root.get("available"));
    }
}
