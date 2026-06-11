package m84_spring_data_jpa_migrations.practice.task07;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.OptimisticLockingFailureException;
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

// ============================================================
// Runner (каркас)
// ============================================================
// TODO: @Component
class Runner07 implements CommandLineRunner {
    private final ProductRepository07 repo;
    private final InventoryService07 service;
    Runner07(ProductRepository07 repo, InventoryService07 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        Long id = repo.save(new Product07("Видеокарта", 10)).getId();
        // TODO: service.purchase(id, 1);   // stock → 9, version → 1
        // TODO: Product07 p = repo.findById(id).orElseThrow();
        // TODO: System.out.println("stock=" + p.getStock() + " version=" + p.getVersion());
        // TODO: System.out.println("createdBy=" + p.getCreatedBy() + " createdAt=" + p.getCreatedAt());
        // TODO: System.out.println("updatedBy=" + p.getUpdatedBy() + " updatedAt=" + p.getUpdatedAt());
        //       Ожидается: stock=9, version=1, createdBy=system, updatedAt заполнен
    }
}
