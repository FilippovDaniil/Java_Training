package m109_spring_test_integration.practice.task07;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@Service
class ProductService07 {
    private final ProductRepository07 repo;
    ProductService07(ProductRepository07 repo) { this.repo = repo; }
    void create(String name, int price) { repo.save(new Product07(name, price)); }
    long countExpensive(int min) { return repo.countByPriceGreaterThan(min); }
    Optional<Product07> findByName(String name) { return repo.findByName(name); }
}
