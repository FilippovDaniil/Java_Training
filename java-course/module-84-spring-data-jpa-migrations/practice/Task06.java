/**
 * Задача 06 — Модуль 84: аудит (@CreatedDate, @LastModifiedDate, @CreatedBy)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Включите аудит: на классе конфигурации AuditConfig06 поставьте @EnableJpaAuditing
 *      и объявите бин AuditorAware<String> (вернёт, например, "admin").
 *   2) В Product06:
 *        - @EntityListeners(AuditingEntityListener.class) на классе;
 *        - @CreatedDate      Instant createdAt;
 *        - @LastModifiedDate Instant updatedAt;
 *        - @CreatedBy        String createdBy;
 *        - @LastModifiedBy   String updatedBy;
 *      добавьте геттеры.
 *   3) В Runner: сохраните товар (createdAt/createdBy заполнятся), затем обновите цену
 *      (updatedAt/updatedBy обновятся) — выведите все четыре поля до и после.
 *
 * ЦЕЛЬ: получить автоматический аудит «кто/когда создал и менял» без ручного кода.
 *
 * ПОДСКАЗКА: без @EnableJpaAuditing поля останутся null; @CreatedBy берётся из AuditorAware.
 *            createdAt после первого save и после update НЕ меняется, updatedAt — меняется.
 */
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

@SpringBootApplication
public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}

@Configuration
// TODO: @EnableJpaAuditing
class AuditConfig06 {
    // TODO: @Bean
    AuditorAware<String> auditorAware() {
        // TODO: return () -> Optional.of("admin");   // в реале — из SecurityContext
        return Optional::empty;
    }
}

@Entity @Table(name = "products")
// TODO: @EntityListeners(AuditingEntityListener.class)
class Product06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;

    // TODO: @CreatedDate
    private Instant createdAt;
    // TODO: @LastModifiedDate
    private Instant updatedAt;
    // TODO: @CreatedBy
    private String createdBy;
    // TODO: @LastModifiedBy
    private String updatedBy;

    protected Product06() {}
    public Product06(String name, int price) { this.name = name; this.price = price; }
    public Long getId() { return id; }
    public void setPrice(int price) { this.price = price; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public String getCreatedBy() { return createdBy; }
    public String getUpdatedBy() { return updatedBy; }
}

interface ProductRepository06 extends JpaRepository<Product06, Long> {}

@Service
class ProductService06 {
    private final ProductRepository06 repo;
    ProductService06(ProductRepository06 repo) { this.repo = repo; }

    @Transactional
    public void updatePrice(Long id, int price) {
        repo.findById(id).orElseThrow().setPrice(price);
    }
}

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
