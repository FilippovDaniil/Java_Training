package m77_spring_data_jpa_intro.practice.task06;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

@Component
class ServiceRunner06 implements CommandLineRunner {
    private final ProductService06 service;
    ServiceRunner06(ProductService06 service) { this.service = service; }

    @Override
    public void run(String... args) {
        // TODO: добавьте "Кофе"(500), "Чай"(300), "Кофемашина"(50000)
        // TODO: выведите дорогие (>=400) и service.total()
    }
}
