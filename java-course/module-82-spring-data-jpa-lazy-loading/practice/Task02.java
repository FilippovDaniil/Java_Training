/**
 * Задача 02 — Модуль 82: воспроизвести и устранить LazyInitializationException
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) brokenRead(id): БЕЗ @Transactional загрузить категорию через репозиторий и
 *      обратиться к c.getProducts().size(). Поймать LazyInitializationException и
 *      напечатать "Поймано: " + e.getClass().getSimpleName().
 *   2) fixedRead(id): пометить @Transactional, тот же доступ к products — теперь работает.
 *      Вернуть products.size().
 *
 * ЦЕЛЬ: на практике увидеть, что ленивая связь требует открытой сессии (транзакции).
 *
 * ПОДСКАЗКА: чтобы "сессия закрылась" между загрузкой и доступом, в brokenRead не
 *            ставьте @Transactional и выполняйте size() уже после возврата из репозитория.
 *            Для чистоты эксперимента можно выключить spring.jpa.open-in-view=false.
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
public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}

@Entity @Table(name = "categories")
class Category02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product02> products = new ArrayList<>();
    protected Category02() {}
    public Category02(String name) { this.name = name; }
    public Long getId() { return id; }
    public List<Product02> getProducts() { return products; }
    public void addProduct(Product02 p) { products.add(p); p.setCategory(this); }
}

@Entity @Table(name = "products")
class Product02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category02 category;
    protected Product02() {}
    public Product02(String name) { this.name = name; }
    public void setCategory(Category02 c) { this.category = c; }
}

interface CategoryRepository02 extends JpaRepository<Category02, Long> {}

@Service
class CatalogService02 {
    private final CategoryRepository02 repo;
    CatalogService02(CategoryRepository02 repo) { this.repo = repo; }

    // НЕТ @Transactional — сессия закроется внутри findById, доступ к products упадёт
    public void brokenRead(Long id) {
        Category02 c = repo.findById(id).orElseThrow();
        // TODO: try { int n = c.getProducts().size(); ... } catch (Exception e) {
        // TODO:   System.out.println("Поймано: " + e.getClass().getSimpleName()); }
    }

    // TODO: @Transactional
    public int fixedRead(Long id) {
        Category02 c = repo.findById(id).orElseThrow();
        // TODO: return c.getProducts().size();   // внутри транзакции — ОК
        return -1;
    }
}

@Component
class Runner02 implements CommandLineRunner {
    private final CategoryRepository02 repo;
    private final CatalogService02 service;
    Runner02(CategoryRepository02 repo, CatalogService02 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        Category02 c = new Category02("Книги");
        c.addProduct(new Product02("Java")); c.addProduct(new Product02("SQL"));
        Long id = repo.save(c).getId();
        // TODO: service.brokenRead(id);            // ожидается "Поймано: LazyInitializationException"
        // TODO: System.out.println("fixed = " + service.fixedRead(id));  // ожидается 2
    }
}
