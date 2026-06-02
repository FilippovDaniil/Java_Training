/**
 * Задача 06 — Модуль 80: Specification — динамический фильтр
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Постройте запрос динамически из НЕОБЯЗАТЕЛЬНЫХ условий.
 *   1) Репозиторий наследует ещё и JpaSpecificationExecutor<Product06>.
 *   2) В ProductSpecs06 реализуйте две спецификации (возвращают null, если параметр null):
 *        hasCategory(category): cb.equal(root.get("category"), category)
 *        priceAtLeast(min):     cb.greaterThanOrEqualTo(root.get("price"), min)
 *   3) В сервисе search(category, min) соберите:
 *        Specification.where(hasCategory(category)).and(priceAtLeast(min))
 *      и верните repo.findAll(spec).
 *   4) В CommandLineRunner вызовите search с разными комбинациями (оба заданы,
 *      только категория, только цена, ничего) и выведите размеры результатов.
 *
 * ЦЕЛЬ: один метод поиска покрывает любой набор фильтров (null → условие не применяется).
 *
 * ПОДСКАЗКА:
 *   import org.springframework.data.jpa.domain.Specification;
 *   import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@SpringBootApplication
public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}

@Entity @Table(name = "products")
class Product06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;
    private String category;
    protected Product06() {}
    public Product06(String name, long price, String category) {
        this.name = name; this.price = price; this.category = category;
    }
}

interface ProductRepository06 extends JpaRepository<Product06, Long>,
                                      JpaSpecificationExecutor<Product06> {}

class ProductSpecs06 {
    public static Specification<Product06> hasCategory(String category) {
        // TODO: return (root, query, cb) -> category == null ? null : cb.equal(root.get("category"), category);
        return null;
    }
    public static Specification<Product06> priceAtLeast(Long min) {
        // TODO: return (root, query, cb) -> min == null ? null : cb.greaterThanOrEqualTo(root.get("price"), min);
        return null;
    }
}

@Service
class SearchService06 {
    private final ProductRepository06 repo;
    SearchService06(ProductRepository06 repo) { this.repo = repo; }

    public List<Product06> search(String category, Long minPrice) {
        // TODO: Specification<Product06> spec = Specification
        //          .where(ProductSpecs06.hasCategory(category))
        //          .and(ProductSpecs06.priceAtLeast(minPrice));
        // TODO: return repo.findAll(spec);
        return null;
    }
}

@Component
class Runner06 implements CommandLineRunner {
    private final ProductRepository06 repo;
    private final SearchService06 service;
    Runner06(ProductRepository06 repo, SearchService06 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(
                new Product06("Ноутбук", 80000, "Электроника"),
                new Product06("Мышь", 900, "Электроника"),
                new Product06("Java book", 1500, "Книги")));
        // TODO: выведите размеры результатов для search("Электроника", 1000L),
        //       search("Книги", null), search(null, 1000L), search(null, null)
    }
}
