/**
 * Задача 03 — Модуль 82: увидеть проблему N+1 «вживую»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *   В application.properties включите: spring.jpa.show-sql=true
 *
 * ЗАДАНИЕ:
 *   1) reportNaive(): @Transactional. Загрузить ВСЕ категории (findAll), затем в цикле
 *      по каждой вывести "имя: <products.size()> товаров".
 *   2) Запустите и посчитайте в логе количество SELECT'ов: должно быть 1 (категории)
 *      + по одному на products каждой категории = 1 + N запросов. Это и есть N+1.
 *
 * ЦЕЛЬ: научиться диагностировать N+1 по логу SQL — до того, как «лечить».
 *
 * ПОДСКАЗКА: метод транзакционный, чтобы ленивые products вообще загрузились
 *            (иначе был бы LazyInitializationException, см. Task02). Здесь цель —
 *            именно посчитать запросы, а не упасть.
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}

@Entity @Table(name = "categories")
class Category03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product03> products = new ArrayList<>();
    protected Category03() {}
    public Category03(String name) { this.name = name; }
    public String getName() { return name; }
    public List<Product03> getProducts() { return products; }
    public void addProduct(Product03 p) { products.add(p); p.setCategory(this); }
}

@Entity @Table(name = "products")
class Product03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category03 category;
    protected Product03() {}
    public Product03(String name) { this.name = name; }
    public void setCategory(Category03 c) { this.category = c; }
}

interface CategoryRepository03 extends JpaRepository<Category03, Long> {}

@Service
class ReportService03 {
    private final CategoryRepository03 repo;
    ReportService03(CategoryRepository03 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void reportNaive() {
        // TODO: List<Category03> all = repo.findAll();   // 1 запрос
        // TODO: for (Category03 c : all)                 // +1 запрос на products КАЖДОЙ категории
        // TODO:     System.out.println(c.getName() + ": " + c.getProducts().size() + " товаров");
        // TODO: посчитайте SELECT'ы в логе — их 1 + N
    }
}

@Component
class Runner03 implements CommandLineRunner {
    private final CategoryRepository03 repo;
    private final ReportService03 service;
    Runner03(CategoryRepository03 repo, ReportService03 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        for (String name : new String[]{"Электроника", "Книги", "Одежда"}) {
            Category03 c = new Category03(name);
            c.addProduct(new Product03(name + "-1"));
            c.addProduct(new Product03(name + "-2"));
            repo.save(c);
        }
        // TODO: service.reportNaive();   // в логе: 1 + 3 = 4 SELECT'а
    }
}
