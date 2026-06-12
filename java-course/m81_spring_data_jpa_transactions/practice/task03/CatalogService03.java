package m81_spring_data_jpa_transactions.practice.task03;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
class CatalogService03 {
    private final ProductRepository03 repo;
    CatalogService03(ProductRepository03 repo) { this.repo = repo; }

    // TODO: @Transactional(readOnly = true)
    public List<Product03> findAll() {
        return repo.findAll();
    }

    // TODO: @Transactional(readOnly = true)
    public long countAll() {
        return repo.count();
    }

    // TODO: @Transactional
    public Product03 add(String name) {
        return repo.save(new Product03(name));
    }
}
