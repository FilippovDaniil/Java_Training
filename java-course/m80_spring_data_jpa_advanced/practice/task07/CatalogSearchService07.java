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
@Service
class CatalogSearchService07 {

    private final ProductRepository07 repository;

    public CatalogSearchService07(ProductRepository07 repository) {
        this.repository = repository;
    }

    // Поиск по ключевому слову
    public List<Product07> search(String kw) {
        return repository.searchByKeyword(kw);
    }

    // DTO-проекция
    public List<ProductCard07> cards() {
        return repository.cards();
    }

    // Фильтр с Specification
    public List<Product07> filter(String cat, Long min, Long max) {
        Specification<Product07> spec = Specification
                .where(ProductSpecs07.hasCategory(cat))
                .and(ProductSpecs07.priceBetween(min, max))
                .and(ProductSpecs07.availableOnly());
        return repository.findAll(spec);
    }

    // Применить скидку
    @Transactional
    public int applyDiscount(String cat, double factor) {
        return repository.discount(factor, cat);
    }
}
