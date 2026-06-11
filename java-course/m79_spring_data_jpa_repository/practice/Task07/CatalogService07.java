package m79_spring_data_jpa_repository.practice.task07;

import jakarta.persistence.*;
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
class CatalogService07 {

    // TODO: внедрите ProductRepository07

    public List<Product07> inStock(String category) { return null; }       // TODO
    public List<Product07> search(String part) { return null; }            // TODO
    public List<Product07> priceRange(long min, long max) { return null; } // TODO
    public List<Product07> topExpensive() { return null; }                 // TODO
    public Page<Product07> firstPage(int size) { return null; }            // TODO: findByAvailableTrue(PageRequest.of(0, size))
}
