package m77_spring_data_jpa_intro.practice.task07;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
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
@Component
class StartupRunner07 implements CommandLineRunner {

    // TODO: внедрите CatalogService07
    private final CatalogService07 service;

    @Autowired
    public StartupRunner07(CatalogService07 service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        // TODO: service.seed();
        service.seed();
        // TODO: System.out.println("Товаров: " + service.productCount() + ", категорий: " + service.categoryCount());
        System.out.println("Товаров: " +
                service.productCount() +
                ", категорий: " +
                service.categoryCount()
        );
    }
}
