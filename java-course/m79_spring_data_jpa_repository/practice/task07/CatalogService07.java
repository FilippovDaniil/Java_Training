package m79_spring_data_jpa_repository.practice.task07;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

// TODO: @Service
@Service
class CatalogService07 {

    // TODO: внедрите ProductRepository07
    private final ProductRepository07 repo;

    @Autowired
    public CatalogService07(ProductRepository07 repo) {
        this.repo = repo;
    }

    public List<Product07> inStock(String category) {
        return repo.findByCategory(category);
    }       // TODO

    public List<Product07> search(String part) {
        return repo.findByNameContainingIgnoreCase(part);
    }            // TODO

    public List<Product07> priceRange(long min, long max) {
        return repo.findByPriceBetween(min, max);
    } // TODO

    public List<Product07> topExpensive() {
        return repo.findTop5ByOrderByPriceDesc();
    }                 // TODO

    public Page<Product07> firstPage(int size) {
        return repo.findByAvailableTrue(PageRequest.of(0, size));
    }            // TODO: findByAvailableTrue(PageRequest.of(0, size))

}
