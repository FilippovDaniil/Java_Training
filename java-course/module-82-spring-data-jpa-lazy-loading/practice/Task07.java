/**
 * Задача 07 — Модуль 82: МИНИ-ПРОЕКТ «Эффективная витрина каталога shop-data-jpa»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *   В application.properties: spring.jpa.show-sql=true, spring.jpa.open-in-view=false
 *
 * ЦЕЛЬ: собрать один сервис каталога, который три типовых экрана отдаёт БЕЗ N+1 и
 *       без LazyInitializationException, осознанно выбирая приём под каждый кейс.
 *
 * МОДЕЛЬ:
 *   Category07 (1) —— (N) Product07.  Все связи LAZY.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   Репозиторий CategoryRepository07:
 *     1) @Query("select distinct c from Category07 c join fetch c.products")
 *        List<Category07> findAllWithProducts();              // экран «категория + её товары»
 *     2) @EntityGraph(attributePaths = "products")
 *        List<Category07> findByNameContaining(String part);  // поиск по имени с товарами
 *     3) @Query("select new CatalogRow07(c.name, count(p)) from Category07 c " +
 *               "left join c.products p group by c.id, c.name")
 *        List<CatalogRow07> overview();                       // сводка для главной (DTO)
 *
 *   Сервис CatalogService07 (@Service):
 *     - listWithProducts(): @Transactional — печатает каждую категорию и её products.size();
 *     - search(part): @Transactional — печатает найденные категории и число товаров;
 *     - overview(): БЕЗ @Transactional — печатает сводку (имя -> количество).
 *
 *   Runner07: засейте 3 категории (по 2 товара) + 1 пустую; вызовите все три метода;
 *     в логе убедитесь, что каждый экран — это 1 (или 1+агрегат) запрос, а НЕ 1+N.
 *
 * АРХИТЕКТУРА (приём под кейс):
 *
 *   «категория + товары»  → JOIN FETCH (полный контроль, distinct от дублей)
 *   «поиск + товары»      → @EntityGraph (декларативно поверх derived query)
 *   «сводка для главной»  → DTO-проекция (сущности не нужны → нет lazy/N+1 вовсе)
 *
 * ОЖИДАЕМЫЙ ИТОГ:
 *   - listWithProducts(): один SELECT с JOIN, выведены 3 категории по 2 товара;
 *   - search("ни"): найдена "Книги" (1 товарная пара) одним JOIN-запросом;
 *   - overview(): 4 строки (3 по 2 + пустая с 0) одним агрегирующим запросом.
 *
 * ПОДСКАЗКА: соберите вместе Task04 (JOIN FETCH), Task05 (@EntityGraph), Task06 (DTO).
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}

// ============================================================
// DTO для сводки
// ============================================================
record CatalogRow07(String name, long productCount) {}

// ============================================================
// Сущности (связи LAZY)
// ============================================================
@Entity @Table(name = "categories")
class Category07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product07> products = new ArrayList<>();
    protected Category07() {}
    public Category07(String name) { this.name = name; }
    public String getName() { return name; }
    public List<Product07> getProducts() { return products; }
    public void addProduct(Product07 p) { products.add(p); p.setCategory(this); }
}

@Entity @Table(name = "products")
class Product07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category07 category;
    protected Product07() {}
    public Product07(String name) { this.name = name; }
    public void setCategory(Category07 c) { this.category = c; }
}

// ============================================================
// Репозиторий (каркас)
// ============================================================
interface CategoryRepository07 extends JpaRepository<Category07, Long> {
    // TODO: @Query("select distinct c from Category07 c join fetch c.products")
    // TODO: List<Category07> findAllWithProducts();

    // TODO: @EntityGraph(attributePaths = "products")
    // TODO: List<Category07> findByNameContaining(String part);

    // TODO: @Query("select new CatalogRow07(c.name, count(p)) from Category07 c " +
    // TODO:        "left join c.products p group by c.id, c.name")
    // TODO: List<CatalogRow07> overview();
}

// ============================================================
// Сервис (каркас)
// ============================================================
// TODO: @Service
class CatalogService07 {
    private final CategoryRepository07 repo;
    CatalogService07(CategoryRepository07 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void listWithProducts() {
        // TODO: for (Category07 c : repo.findAllWithProducts())
        // TODO:     System.out.println(c.getName() + ": " + c.getProducts().size());
    }

    // TODO: @Transactional
    public void search(String part) {
        // TODO: for (Category07 c : repo.findByNameContaining(part))
        // TODO:     System.out.println("найдено: " + c.getName() + " (" + c.getProducts().size() + ")");
    }

    public void overview() {
        // TODO: for (CatalogRow07 r : repo.overview())
        // TODO:     System.out.println(r.name() + " -> " + r.productCount());
    }
}

// ============================================================
// Runner (каркас)
// ============================================================
// TODO: @Component
class CatalogRunner07 implements CommandLineRunner {
    private final CategoryRepository07 repo;
    private final CatalogService07 service;
    CatalogRunner07(CategoryRepository07 repo, CatalogService07 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        for (String name : new String[]{"Электроника", "Книги", "Одежда"}) {
            Category07 c = new Category07(name);
            c.addProduct(new Product07(name + "-1"));
            c.addProduct(new Product07(name + "-2"));
            repo.save(c);
        }
        repo.save(new Category07("Распродажа"));   // пустая → overview покажет 0
        // TODO: service.listWithProducts();
        // TODO: service.search("ни");     // "Книги"
        // TODO: service.overview();       // 4 строки, последняя с 0
    }
}
