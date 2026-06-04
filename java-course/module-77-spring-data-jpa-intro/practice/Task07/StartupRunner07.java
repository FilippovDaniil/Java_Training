import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// ============================================================
// Сидинг при старте (каркас)
// ============================================================

// TODO: @Component
class StartupRunner07 implements CommandLineRunner {

    // TODO: внедрите CatalogService07

    @Override
    public void run(String... args) {
        // TODO: service.seed();
        // TODO: System.out.println("Товаров: " + service.productCount() + ", категорий: " + service.categoryCount());
    }
}
