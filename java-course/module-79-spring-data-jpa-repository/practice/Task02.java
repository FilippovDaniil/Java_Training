/**
 * Задача 02 — Модуль 79: Derived queries — простые
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Добавьте в ProductRepository02 методы-запросы (тело писать НЕ нужно — только сигнатуры):
 *     - findByCategory(String category)            → List<Product02>
 *     - findByPriceLessThan(long price)            → List<Product02>
 *     - findBySku(String sku)                      → Optional<Product02>  (0 или 1)
 *   В CommandLineRunner засейте данные и проверьте каждый метод (выведите результаты).
 *
 * ЦЕЛЬ: понять, что имя метода = запрос (Spring сам генерирует SQL).
 *
 * ПОДСКАЗКА:
 *   Имя поля в методе должно ТОЧНО совпадать с полем сущности (category, price, sku).
 *   Для «ноль или один» используйте Optional.
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}

@Entity @Table(name = "products")
class Product02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;
    private String category;
    private String sku;
    protected Product02() {}
    public Product02(String name, long price, String category, String sku) {
        this.name = name; this.price = price; this.category = category; this.sku = sku;
    }
    public String getName() { return name; }
}

interface ProductRepository02 extends JpaRepository<Product02, Long> {
    // TODO: List<Product02> findByCategory(String category);
    // TODO: List<Product02> findByPriceLessThan(long price);
    // TODO: Optional<Product02> findBySku(String sku);
}

@Component
class QueryRunner02 implements CommandLineRunner {
    private final ProductRepository02 repo;
    QueryRunner02(ProductRepository02 repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(
                new Product02("Ноутбук", 80000, "Электроника", "SKU-1"),
                new Product02("Мышь", 900, "Электроника", "SKU-2"),
                new Product02("Java book", 1500, "Книги", "SKU-3")));
        // TODO: выведите repo.findByCategory("Электроника").size()
        // TODO: выведите дешёвые: repo.findByPriceLessThan(2000)
        // TODO: выведите repo.findBySku("SKU-1") (Optional)
    }
}
