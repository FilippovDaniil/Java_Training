/**
 * Задача 04 — Модуль 80: DTO-проекция через конструктор в JPQL
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Верните результат запроса сразу в виде DTO (class-based проекция).
 *   1) DTO: record ProductSummary04(String name, long price). Готов.
 *   2) В репозитории @Query с конструктором в SELECT:
 *        @Query("SELECT new <ПОЛНОЕ_ИМЯ>.ProductSummary04(p.name, p.price) FROM Product04 p WHERE p.price >= :min")
 *        List<ProductSummary04> summaries(@Param("min") long min);
 *      ВНИМАНИЕ: в default package полное имя = просто ProductSummary04
 *      (если положите классы в пакет — укажите FQN, напр. com.example.ProductSummary04).
 *   3) Проверьте в CommandLineRunner.
 *
 * ЦЕЛЬ: получить готовый DTO прямо из запроса, без ручного маппинга.
 *
 * ПОДСКАЗКА: конструктор DTO в JPQL: SELECT new Имя(поле1, поле2) FROM ...
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@SpringBootApplication
public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}

@Entity @Table(name = "products")
class Product04 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;
    protected Product04() {}
    public Product04(String name, long price) { this.name = name; this.price = price; }
}

record ProductSummary04(String name, long price) {}

interface ProductRepository04 extends JpaRepository<Product04, Long> {

    // TODO: @Query("SELECT new ProductSummary04(p.name, p.price) FROM Product04 p WHERE p.price >= :min")
    List<ProductSummary04> summaries(/* @Param("min") */ long min);
}

@Component
class Runner04 implements CommandLineRunner {
    private final ProductRepository04 repo;
    Runner04(ProductRepository04 repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(new Product04("A", 100), new Product04("B", 5000), new Product04("C", 2000)));
        // TODO: repo.summaries(1000).forEach(s -> System.out.println(s.name() + " = " + s.price()));
    }
}
