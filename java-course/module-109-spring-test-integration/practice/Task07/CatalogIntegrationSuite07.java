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

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(classes = Task07.class)
// TODO: @Testcontainers
class CatalogIntegrationSuite07 {

    // TODO: @Container @ServiceConnection
    // TODO: static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    // TODO: @Autowired ProductService07 service;
    // TODO: @Autowired ProductRepository07 repo;

    @BeforeEach
    void clean() {
        // TODO: repo.deleteAll();
    }

    @Test
    void create_and_read() {
        // TODO: service.create("Кофе", 100);
        // TODO: assertThat(service.findByName("Кофе")).isPresent();
    }

    @Test
    void count_expensive() {
        // TODO: service.create("Дешёвый", 10);
        // TODO: service.create("Дорогой", 1000);
        // TODO: assertThat(service.countExpensive(100)).isEqualTo(1);
    }

    @Test
    void isolation_between_tests() {
        // TODO: assertThat(repo.count()).isEqualTo(0);
    }
}
