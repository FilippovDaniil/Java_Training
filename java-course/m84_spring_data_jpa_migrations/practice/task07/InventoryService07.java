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
// Сервис (каркас)
// ============================================================
// TODO: @Service
class InventoryService07 {
    private final ProductRepository07 repo;
    InventoryService07(ProductRepository07 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void purchase(Long id, int qty) {
        // TODO: Product07 p = repo.findById(id).orElseThrow();
        // TODO: if (p.getStock() < qty) throw new IllegalStateException("Недостаточно на складе");
        // TODO: p.setStock(p.getStock() - qty);   // dirty checking, version++
    }

    public void purchaseWithRetry(Long id, int qty, int attempts) {
        // TODO: for (int i = 0; i < attempts; i++) {
        // TODO:     try { purchase(id, qty); return; }
        // TODO:     catch (OptimisticLockingFailureException e) { /* re-read на след. итерации */ }
        // TODO: }
        // TODO: throw new IllegalStateException("Не удалось после " + attempts + " попыток");
    }
}
