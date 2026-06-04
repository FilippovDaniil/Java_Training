/**
 * Задача 05 — Модуль 82: устранить N+1 через @EntityGraph
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) В CategoryRepository05 добавьте derived-метод с @EntityGraph, который жадно
 *      подтянет products БЕЗ написания JPQL:
 *        @EntityGraph(attributePaths = "products")
 *        List<Category05> findByNameContaining(String part);
 *   2) reportGraph(part): @Transactional, вызвать метод и вывести имя + products.size().
 *   3) Сравните лог с Task03/04: @EntityGraph даёт один JOIN-запрос, как JOIN FETCH,
 *      но декларативно поверх derived query.
 *
 * ЦЕЛЬ: освоить @EntityGraph как декларативную альтернативу JOIN FETCH.
 *
 * ПОДСКАЗКА: attributePaths принимает имена ПОЛЕЙ-связей сущности (можно несколько:
 *            {"products", "products.supplier"}). Имя метода — обычный derived query.
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}

@Entity @Table(name = "categories")
class Category05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product05> products = new ArrayList<>();
    protected Category05() {}
    public Category05(String name) { this.name = name; }
    public String getName() { return name; }
    public List<Product05> getProducts() { return products; }
    public void addProduct(Product05 p) { products.add(p); p.setCategory(this); }
}

@Entity @Table(name = "products")
class Product05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category05 category;
    protected Product05() {}
    public Product05(String name) { this.name = name; }
    public void setCategory(Category05 c) { this.category = c; }
}

interface CategoryRepository05 extends JpaRepository<Category05, Long> {
    // TODO: @EntityGraph(attributePaths = "products")
    // TODO: List<Category05> findByNameContaining(String part);
}

@Service
class ReportService05 {
    private final CategoryRepository05 repo;
    ReportService05(CategoryRepository05 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void reportGraph(String part) {
        // TODO: for (Category05 c : repo.findByNameContaining(part))
        // TODO:     System.out.println(c.getName() + ": " + c.getProducts().size());
    }
}

@Component
class Runner05 implements CommandLineRunner {
    private final CategoryRepository05 repo;
    private final ReportService05 service;
    Runner05(CategoryRepository05 repo, ReportService05 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        for (String name : new String[]{"Электроника", "Книги", "Одежда"}) {
            Category05 c = new Category05(name);
            c.addProduct(new Product05(name + "-1"));
            c.addProduct(new Product05(name + "-2"));
            repo.save(c);
        }
        // TODO: service.reportGraph("");   // пустая подстрока подходит всем — один JOIN-запрос
    }
}
