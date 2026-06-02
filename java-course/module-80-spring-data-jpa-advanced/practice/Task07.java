/**
 * Задача 07 — Модуль 80: МИНИ-ПРОЕКТ «Поисковый слой shop-data-jpa»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЦЕЛЬ: собрать продвинутый поисковый слой каталога: JPQL-поиск, DTO-проекция для
 *       списка, @Modifying для скидок и Specification для гибкого фильтра.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) Сущность Product07: id, name, price (long), category, available (boolean).
 *
 *   2) DTO-проекция: record ProductCard07(String name, long price, String category).
 *
 *   3) ProductRepository07 extends JpaRepository<Product07, Long>, JpaSpecificationExecutor<Product07>:
 *        - @Query JPQL: searchByKeyword(kw) — name LIKE %kw% (без регистра)
 *        - @Query DTO-проекция: cards() → List<ProductCard07> (new ProductCard07(...))
 *        - @Modifying @Query: discount(factor, cat) — умножить цены категории на factor
 *
 *   4) ProductSpecs07: hasCategory(cat), priceBetween(min, max), availableOnly()
 *      (каждая возвращает null, если параметр не задан; availableOnly — всегда условие available=true).
 *
 *   5) CatalogSearchService07 (@Service):
 *        - search(kw)                  → repo.searchByKeyword
 *        - cards()                     → repo.cards()
 *        - filter(cat, min, max)       → repo.findAll(Specification.where(...).and(...).and(availableOnly()))
 *        - applyDiscount(cat, factor)  → @Transactional, repo.discount(...)
 *
 *   6) CommandLineRunner: засейте ~6 товаров, прогоните все методы, выведите результаты.
 *
 * АРХИТЕКТУРА:
 *
 *   Runner ─► CatalogSearchService07 ─► ProductRepository07
 *                                        ├─ JPQL @Query (поиск, DTO-проекция)
 *                                        ├─ @Modifying (скидка)
 *                                        └─ JpaSpecificationExecutor (динамический фильтр)
 *
 * ПОДСКАЗКА: соберите вместе Task01 (JPQL), Task04 (DTO), Task05 (@Modifying), Task06 (Specification).
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}

record ProductCard07(String name, long price, String category) {}

interface ProductRepository07 extends JpaRepository<Product07, Long>,
                                      JpaSpecificationExecutor<Product07> {

    // TODO: @Query JPQL поиск по подстроке без регистра
    List<Product07> searchByKeyword(/* @Param("kw") */ String kw);

    // TODO: @Query DTO-проекция: SELECT new ProductCard07(p.name, p.price, p.category) FROM Product07 p
    List<ProductCard07> cards();

    // TODO: @Modifying @Query UPDATE цен категории на factor
    int discount(/* @Param("factor") */ double factor, /* @Param("cat") */ String category);
}

class ProductSpecs07 {
    public static Specification<Product07> hasCategory(String cat) { return null; }            // TODO
    public static Specification<Product07> priceBetween(Long min, Long max) { return null; }    // TODO
    public static Specification<Product07> availableOnly() { return null; }                     // TODO: available = true
}

// TODO: @Service
class CatalogSearchService07 {

    // TODO: внедрите ProductRepository07

    public List<Product07> search(String kw) { return null; }                  // TODO
    public List<ProductCard07> cards() { return null; }                        // TODO
    public List<Product07> filter(String cat, Long min, Long max) { return null; } // TODO: Specification
    // TODO: @Transactional
    public int applyDiscount(String cat, double factor) { return 0; }          // TODO
}

// TODO: @Component
class SearchRunner07 implements CommandLineRunner {

    // TODO: внедрите ProductRepository07 (сидинг) и CatalogSearchService07

    @Override
    public void run(String... args) {
        // TODO: засейте ~6 товаров (разные категории/цены/наличие)
        // TODO: вызовите search/cards/filter/applyDiscount и выведите результаты
    }
}
