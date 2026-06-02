/**
 * Задача 03 — Модуль 79: Derived queries — And/Or/Between/Like/OrderBy
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Добавьте в ProductRepository03 более сложные derived-методы:
 *     - findByCategoryAndPriceGreaterThan(String category, long price)
 *     - findByPriceBetween(long min, long max)
 *     - findByNameContainingIgnoreCase(String part)       // подстрока без учёта регистра
 *     - findByCategoryOrderByPriceDesc(String category)   // сортировка в имени
 *   Проверьте каждый в CommandLineRunner.
 *
 * ЦЕЛЬ: освоить ключевые слова derived-запросов (см. таблицу в theory.md).
 *
 * ПОДСКАЗКА:
 *   Between — диапазон; Containing — LIKE %...%; IgnoreCase — без регистра;
 *   OrderBy<Поле>Desc — сортировка.
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@SpringBootApplication
public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}

@Entity @Table(name = "products")
class Product03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;
    private String category;
    protected Product03() {}
    public Product03(String name, long price, String category) {
        this.name = name; this.price = price; this.category = category;
    }
    public String getName() { return name; }
    public long getPrice() { return price; }
}

interface ProductRepository03 extends JpaRepository<Product03, Long> {
    // TODO: List<Product03> findByCategoryAndPriceGreaterThan(String category, long price);
    // TODO: List<Product03> findByPriceBetween(long min, long max);
    // TODO: List<Product03> findByNameContainingIgnoreCase(String part);
    // TODO: List<Product03> findByCategoryOrderByPriceDesc(String category);
}

@Component
class QueryRunner03 implements CommandLineRunner {
    private final ProductRepository03 repo;
    QueryRunner03(ProductRepository03 repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(
                new Product03("Ноутбук Lenovo", 80000, "Электроника"),
                new Product03("Мышь", 900, "Электроника"),
                new Product03("Клавиатура", 2500, "Электроника"),
                new Product03("Java book", 1500, "Книги")));
        // TODO: проверьте все четыре метода и выведите результаты
    }
}
