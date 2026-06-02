/**
 * Задача 05 — Модуль 79: Top/First и сортировка через Sort
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Добавьте в репозиторий:
 *        - findTop3ByOrderByPriceDesc()       → List<Product05> (топ-3 дорогих)
 *        - findFirstByOrderByPriceAsc()        → Optional<Product05> (самый дешёвый)
 *   2) В CommandLineRunner используйте также готовый findAll(Sort):
 *        repo.findAll(Sort.by("price").descending())  — все, по убыванию цены.
 *   3) Выведите результаты.
 *
 * ЦЕЛЬ: ограничение количества (Top/First) и динамическая сортировка (Sort).
 *
 * ПОДСКАЗКА:
 *   import org.springframework.data.domain.Sort;
 *   Top<N> в имени метода ограничивает выборку; Sort передаётся аргументом.
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}

@Entity @Table(name = "products")
class Product05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;
    protected Product05() {}
    public Product05(String name, long price) { this.name = name; this.price = price; }
    public String getName() { return name; }
    public long getPrice() { return price; }
}

interface ProductRepository05 extends JpaRepository<Product05, Long> {
    // TODO: List<Product05> findTop3ByOrderByPriceDesc();
    // TODO: Optional<Product05> findFirstByOrderByPriceAsc();
}

@Component
class Runner05 implements CommandLineRunner {
    private final ProductRepository05 repo;
    Runner05(ProductRepository05 repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(
                new Product05("A", 100), new Product05("B", 5000),
                new Product05("C", 2000), new Product05("D", 80000),
                new Product05("E", 300)));
        // TODO: выведите топ-3 дорогих (findTop3ByOrderByPriceDesc)
        // TODO: выведите самый дешёвый (findFirstByOrderByPriceAsc)
        // TODO: выведите все по убыванию цены: repo.findAll(Sort.by("price").descending())
    }
}
