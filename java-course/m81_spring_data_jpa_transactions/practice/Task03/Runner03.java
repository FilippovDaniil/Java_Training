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

@Component
class Runner03 implements CommandLineRunner {
    private final CatalogService03 service;
    Runner03(CatalogService03 service) { this.service = service; }

    @Override
    public void run(String... args) {
        // TODO: service.add("Кофе"); service.add("Чай");
        // TODO: System.out.println("Всего: " + service.countAll());
        // TODO: service.findAll().forEach(p -> System.out.println(p.getName()));
    }
}
