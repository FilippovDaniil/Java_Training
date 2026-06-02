/**
 * Задача 07 — Модуль 78: МИНИ-ПРОЕКТ «Полная сущность Product для shop-data-jpa»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЦЕЛЬ: собрать «боевую» сущность товара, применив все приёмы модуля 78 —
 *       генерацию ключа, ограничения колонок, enum, встраиваемый Money, timestamp,
 *       вычисляемое @Transient-поле.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) @Embeddable Money07 { long amount; String currency; } (+ конструкторы).
 *
 *   2) enum ProductStatus07 { DRAFT, ACTIVE, ARCHIVED }.
 *
 *   3) @Entity @Table(name = "products") Product07:
 *        - id:        @Id @GeneratedValue(IDENTITY)
 *        - name:      @Column(nullable = false, length = 100)
 *        - sku:       @Column(nullable = false, unique = true, length = 32)
 *        - price:     @Embedded Money07
 *        - status:    @Enumerated(EnumType.STRING) ProductStatus07
 *        - createdAt: LocalDateTime (авто-маппинг)
 *        - displayLabel: @Transient + getDisplayLabel() = name + " [" + status + "]"
 *
 *   4) ProductRepository07 extends JpaRepository<Product07, Long>.
 *
 *   5) CommandLineRunner: сохранить 2 товара, вывести count и для каждого — getDisplayLabel().
 *      Запустить с show-sql=true и изучить DDL: колонки amount/currency встроены,
 *      status — VARCHAR, display_label отсутствует.
 *
 * АРХИТЕКТУРА (одна таблица products):
 *
 *   | id | name | sku | amount | currency | status | created_at |
 *                       └── из @Embedded Money07 ──┘
 *   (display_label НЕТ — @Transient)
 *
 * ПОДСКАЗКА: соберите вместе наработки Task02–Task05 этого модуля.
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}

// TODO: @Embeddable
class Money07 {
    private long amount;
    private String currency;
    protected Money07() {}
    public Money07(long amount, String currency) { this.amount = amount; this.currency = currency; }
}

enum ProductStatus07 { DRAFT, ACTIVE, ARCHIVED }

@Entity
@Table(name = "products")
class Product07 {

    // TODO: @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: @Column(nullable = false, length = 100)
    private String name;

    // TODO: @Column(nullable = false, unique = true, length = 32)
    private String sku;

    // TODO: @Embedded
    private Money07 price;

    // TODO: @Enumerated(EnumType.STRING)
    private ProductStatus07 status;

    private LocalDateTime createdAt;

    // TODO: @Transient
    private String displayLabel;

    protected Product07() {}
    public Product07(String name, String sku, Money07 price, ProductStatus07 status) {
        this.name = name; this.sku = sku; this.price = price;
        this.status = status; this.createdAt = LocalDateTime.now();
    }

    public String getDisplayLabel() {
        // TODO: верните name + " [" + status + "]"
        return null;
    }
}

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
