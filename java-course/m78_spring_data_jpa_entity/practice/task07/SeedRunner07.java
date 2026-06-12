package m78_spring_data_jpa_entity.practice.task07;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

// TODO: interface ProductRepository07 extends JpaRepository<Product07, Long> {}

// TODO: @Component
class SeedRunner07 implements CommandLineRunner {

    // TODO: внедрите ProductRepository07

    @Override
    public void run(String... args) {
        // TODO: сохраните 2 товара (например, "Ноутбук"/"SKU-1" и "Мышь"/"SKU-2")
        // TODO: выведите count и getDisplayLabel() каждого
    }
}
