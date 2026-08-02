package m84_spring_data_jpa_migrations.practice.task04;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ProductService04(ProductRepository04 repo) {
        this.repo = repo;
    }

    // TODO: @Transactional
    @Transactional
    public void priceUpdate(Long id, int newPrice) {
        // TODO: repo.findById(id).orElseThrow().setPrice(newPrice);   // dirty checking, version++
        repo.findById(id).orElseThrow().setPrice(newPrice);
    }

    @Transactional(readOnly = true)
    public long versionOf(Long id) {
        return repo.findById(id).orElseThrow().getVersion();
    }
}
