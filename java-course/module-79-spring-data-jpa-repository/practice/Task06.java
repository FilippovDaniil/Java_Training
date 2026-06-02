/**
 * Задача 06 — Модуль 79: Пагинация через Pageable / Page
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Добавьте derived-метод с пагинацией:
 *        Page<Product06> findByCategory(String category, Pageable pageable);
 *   2) В CommandLineRunner:
 *        - засейте 7 товаров категории "Электроника";
 *        - запросите 1-ю страницу по 3: PageRequest.of(0, 3, Sort.by("price").descending());
 *        - выведите: page.getContent().size(), page.getTotalElements(),
 *          page.getTotalPages(), page.hasNext().
 *        - используйте также готовый repo.findAll(PageRequest.of(0, 2)).
 *
 * ВОПРОС (ответьте комментарием):
 *   Чем Page отличается от Slice? Когда Slice предпочтительнее?
 *
 * ПОДСКАЗКА:
 *   import org.springframework.data.domain.*;
 *   Page делает дополнительный COUNT-запрос (для totalElements/totalPages).
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

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

interface ProductRepository06 extends JpaRepository<Product06, Long> {
    // TODO: Page<Product06> findByCategory(String category, Pageable pageable);
}

@Component
class Runner06 implements CommandLineRunner {
    private final ProductRepository06 repo;
    Runner06(ProductRepository06 repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        List<Product06> items = IntStream.rangeClosed(1, 7)
                .mapToObj(i -> new Product06("Товар " + i, i * 1000L, "Электроника"))
                .toList();
        repo.saveAll(items);
        // TODO: Pageable pr = PageRequest.of(0, 3, Sort.by("price").descending());
        // TODO: Page<Product06> page = repo.findByCategory("Электроника", pr);
        // TODO: выведите size/totalElements/totalPages/hasNext
    }
}
