package m84_spring_data_jpa_migrations.practice.task04;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
class Runner04 implements CommandLineRunner {
    private final ProductRepository04 repo;
    private final ProductService04 service;
    Runner04(ProductRepository04 repo, ProductService04 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        Product04 p = repo.save(new Product04("Ноутбук", 50000));
        Long id = p.getId();
        // TODO: System.out.println("после INSERT: " + service.versionOf(id));  // 0
        // TODO: service.priceUpdate(id, 48000); System.out.println(service.versionOf(id)); // 1
        // TODO: service.priceUpdate(id, 47000); System.out.println(service.versionOf(id)); // 2
    }
}
