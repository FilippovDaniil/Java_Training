/**
 * Задача 04 — Модуль 82: устранить N+1 через JOIN FETCH
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *   spring.jpa.show-sql=true — чтобы убедиться, что запрос теперь ОДИН.
 *
 * ЗАДАНИЕ:
 *   1) В CategoryRepository04 добавьте метод с @Query и JOIN FETCH:
 *        @Query("select distinct c from Category04 c join fetch c.products")
 *        List<Category04> findAllWithProducts();
 *   2) reportFetch(): @Transactional, вызвать findAllWithProducts() и вывести
 *      имя + размер products каждой категории.
 *   3) Сравните лог с Task03: теперь должен быть ОДИН SELECT с JOIN (без N+1).
 *
 * ЦЕЛЬ: освоить JOIN FETCH как основной приём против N+1.
 *
 * ПОДСКАЗКА: distinct убирает дубли корня (JOIN размножает строки категории
 *            по числу её товаров). Имя сущности в JPQL — Category04, не имя таблицы.
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}

@Entity @Table(name = "categories")
class Category04 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product04> products = new ArrayList<>();
    protected Category04() {}
    public Category04(String name) { this.name = name; }
    public String getName() { return name; }
    public List<Product04> getProducts() { return products; }
    public void addProduct(Product04 p) { products.add(p); p.setCategory(this); }
}

@Entity @Table(name = "products")
class Product04 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category04 category;
    protected Product04() {}
    public Product04(String name) { this.name = name; }
    public void setCategory(Category04 c) { this.category = c; }
}

interface CategoryRepository04 extends JpaRepository<Category04, Long> {
    // TODO: @Query("select distinct c from Category04 c join fetch c.products")
    // TODO: List<Category04> findAllWithProducts();
}

@Service
class ReportService04 {
    private final CategoryRepository04 repo;
    ReportService04(CategoryRepository04 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void reportFetch() {
        // TODO: for (Category04 c : repo.findAllWithProducts())
        // TODO:     System.out.println(c.getName() + ": " + c.getProducts().size());
        // TODO: в логе — ОДИН SELECT ... JOIN ...
    }
}

@Component
class Runner04 implements CommandLineRunner {
    private final CategoryRepository04 repo;
    private final ReportService04 service;
    Runner04(CategoryRepository04 repo, ReportService04 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        for (String name : new String[]{"Электроника", "Книги", "Одежда"}) {
            Category04 c = new Category04(name);
            c.addProduct(new Product04(name + "-1"));
            c.addProduct(new Product04(name + "-2"));
            repo.save(c);
        }
        // TODO: service.reportFetch();
    }
}
