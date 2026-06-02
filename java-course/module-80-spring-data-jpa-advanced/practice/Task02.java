/**
 * Задача 02 — Модуль 80: Native query (nativeQuery = true)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Добавьте «сырой» SQL-запрос (по ТАБЛИЦЕ и КОЛОНКАМ, а не сущности):
 *     1) @Query(value = "SELECT * FROM products WHERE price > :min", nativeQuery = true)
 *        List<Product02> nativeExpensive(@Param("min") long min);
 *     2) Подсчёт средней цены через native:
 *        @Query(value = "SELECT AVG(price) FROM products", nativeQuery = true)
 *        Double averagePrice();
 *   Проверьте в CommandLineRunner.
 *
 * ВОПРОС (ответьте комментарием):
 *   Чем native-запрос отличается от JPQL? В чём минус native при смене БД?
 *
 * ПОДСКАЗКА: native использует имена ТАБЛИЦ (products) и КОЛОНОК (price), не сущностей.
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
    protected Product02() {}
    public Product02(String name, long price) { this.name = name; this.price = price; }
    public String getName() { return name; }
}

interface ProductRepository02 extends JpaRepository<Product02, Long> {

    // TODO: @Query(value = "SELECT * FROM products WHERE price > :min", nativeQuery = true)
    List<Product02> nativeExpensive(/* @Param("min") */ long min);

    // TODO: @Query(value = "SELECT AVG(price) FROM products", nativeQuery = true)
    Double averagePrice();
}

@Component
class Runner02 implements CommandLineRunner {
    private final ProductRepository02 repo;
    Runner02(ProductRepository02 repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(new Product02("A", 1000), new Product02("B", 3000), new Product02("C", 500)));
        // TODO: выведите repo.nativeExpensive(800)
        // TODO: выведите repo.averagePrice()
    }
}
