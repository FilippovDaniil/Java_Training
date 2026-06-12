package m84_spring_data_jpa_migrations.practice.task06;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.Optional;

@Component
class Runner06 implements CommandLineRunner {
    private final ProductRepository06 repo;
    private final ProductService06 service;
    Runner06(ProductRepository06 repo, ProductService06 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        Product06 p = repo.save(new Product06("Клавиатура", 2000));
        Long id = p.getId();
        // TODO: вывести createdAt/createdBy (заполнены), updatedAt
        // TODO: service.updatePrice(id, 1800);
        // TODO: перечитать repo.findById(id) и вывести updatedAt/updatedBy (обновились),
        //       createdAt — прежний
    }
}
