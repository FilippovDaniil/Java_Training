package m84_spring_data_jpa_migrations.practice.task04;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class ProductService04 {
    private final ProductRepository04 repo;
    ProductService04(ProductRepository04 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void priceUpdate(Long id, int newPrice) {
        // TODO: repo.findById(id).orElseThrow().setPrice(newPrice);   // dirty checking, version++
    }

    @Transactional(readOnly = true)
    public long versionOf(Long id) {
        return repo.findById(id).orElseThrow().getVersion();
    }
}
