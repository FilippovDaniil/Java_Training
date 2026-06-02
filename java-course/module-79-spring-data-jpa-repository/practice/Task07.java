/**
 * Задача 07 — Модуль 79: МИНИ-ПРОЕКТ «Каталог shop-data-jpa с богатым репозиторием»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЦЕЛЬ: построить репозиторий каталога с полным набором derived-методов и сервис
 *       поверх него — основа поиска товаров в shop-data-jpa.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) Сущность Product07: id, name, price (long), category, available (boolean).
 *
 *   2) ProductRepository07 — добавьте методы:
 *        - List<Product07> findByCategory(String category);
 *        - List<Product07> findByCategoryAndAvailableTrue(String category);   // только в наличии
 *        - List<Product07> findByPriceBetween(long min, long max);
 *        - List<Product07> findByNameContainingIgnoreCase(String part);
 *        - long countByCategory(String category);
 *        - Page<Product07> findByAvailableTrue(Pageable pageable);
 *        - List<Product07> findTop5ByOrderByPriceDesc();
 *
 *   3) CatalogService07 (@Service) — методы поверх репозитория:
 *        - inStock(category)     → findByCategoryAndAvailableTrue
 *        - search(part)          → findByNameContainingIgnoreCase
 *        - priceRange(min, max)  → findByPriceBetween
 *        - topExpensive()        → findTop5ByOrderByPriceDesc
 *        - firstPage(size)       → findByAvailableTrue(PageRequest.of(0, size))
 *
 *   4) CommandLineRunner: засейте ~8 товаров (разные категории, цены, наличие),
 *      вызовите все методы сервиса, выведите количества/результаты.
 *
 * АРХИТЕКТУРА:
 *
 *   Runner ──► CatalogService07 ──► ProductRepository07 (derived) ──► таблица products
 *
 * ПОДСКАЗКА: AvailableTrue в имени метода = условие available = true; см. Task02–Task06.
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}

@Entity @Table(name = "products")
class Product07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;
    private String category;
    private boolean available;
    protected Product07() {}
    public Product07(String name, long price, String category, boolean available) {
        this.name = name; this.price = price; this.category = category; this.available = available;
    }
    public String getName() { return name; }
}

interface ProductRepository07 extends JpaRepository<Product07, Long> {
    // TODO: List<Product07> findByCategory(String category);
    // TODO: List<Product07> findByCategoryAndAvailableTrue(String category);
    // TODO: List<Product07> findByPriceBetween(long min, long max);
    // TODO: List<Product07> findByNameContainingIgnoreCase(String part);
    // TODO: long countByCategory(String category);
    // TODO: Page<Product07> findByAvailableTrue(Pageable pageable);
    // TODO: List<Product07> findTop5ByOrderByPriceDesc();
}

// TODO: @Service
class CatalogService07 {

    // TODO: внедрите ProductRepository07

    public List<Product07> inStock(String category) { return null; }       // TODO
    public List<Product07> search(String part) { return null; }            // TODO
    public List<Product07> priceRange(long min, long max) { return null; } // TODO
    public List<Product07> topExpensive() { return null; }                 // TODO
    public Page<Product07> firstPage(int size) { return null; }            // TODO: findByAvailableTrue(PageRequest.of(0, size))
}

// TODO: @Component
class CatalogRunner07 implements CommandLineRunner {

    // TODO: внедрите ProductRepository07 (для сидинга) и CatalogService07

    @Override
    public void run(String... args) {
        // TODO: засейте ~8 товаров и вызовите методы сервиса, выводя результаты
    }
}
