/**
 * Задача 01 — Модуль 80: @Query (JPQL) с именованными параметрами
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Добавьте в репозиторий JPQL-запросы через @Query:
 *     1) expensiveInCategory(min, cat):
 *          @Query("SELECT p FROM Product01 p WHERE p.price > :min AND p.category = :cat")
 *          параметры через @Param("min"), @Param("cat")
 *     2) searchByName(part):
 *          @Query("SELECT p FROM Product01 p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :part, '%'))")
 *   Проверьте в CommandLineRunner.
 *
 * ВНИМАНИЕ: в JPQL пишут ИМЯ КЛАССА (Product01) и ПОЛЯ (p.price), а не таблицы/колонки.
 *
 * ПОДСКАЗКА: import org.springframework.data.jpa.repository.Query;
 *            import org.springframework.data.repository.query.Param;
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
public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}

@Entity @Table(name = "products")
class Product01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;
    private String category;
    protected Product01() {}
    public Product01(String name, long price, String category) {
        this.name = name; this.price = price; this.category = category;
    }
    public String getName() { return name; }
}

interface ProductRepository01 extends JpaRepository<Product01, Long> {

    // TODO: @Query("SELECT p FROM Product01 p WHERE p.price > :min AND p.category = :cat")
    List<Product01> expensiveInCategory(/* @Param("min") */ long min, /* @Param("cat") */ String category);

    // TODO: @Query("SELECT p FROM Product01 p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :part, '%'))")
    List<Product01> searchByName(/* @Param("part") */ String part);
}

@Component
class Runner01 implements CommandLineRunner {
    private final ProductRepository01 repo;
    Runner01(ProductRepository01 repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(
                new Product01("Ноутбук Lenovo", 80000, "Электроника"),
                new Product01("Мышь", 900, "Электроника"),
                new Product01("Java book", 1500, "Книги")));
        // TODO: выведите repo.expensiveInCategory(1000, "Электроника")
        // TODO: выведите repo.searchByName("book")
    }
}
