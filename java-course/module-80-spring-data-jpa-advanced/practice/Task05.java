/**
 * Задача 05 — Модуль 80: @Modifying — массовое обновление
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Поднимите цены на категорию одним запросом (bulk-update).
 *   1) В репозитории:
 *        @Modifying
 *        @Query("UPDATE Product05 p SET p.price = p.price * :factor WHERE p.category = :cat")
 *        int raisePrices(@Param("factor") double factor, @Param("cat") String category);
 *      (возвращает число изменённых строк)
 *   2) PriceService05 (@Service): метод raise(cat, factor) пометьте @Transactional
 *      и вызовите repo.raisePrices(...).
 *   3) В CommandLineRunner: засейте товары, вызовите raise("Электроника", 1.1),
 *      выведите число изменённых строк и новые цены.
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему после bulk-update уже загруженные в кэш объекты могут «не знать» новых цен?
 *
 * ПОДСКАЗКА: @Modifying-запрос требует @Transactional; bulk обходит persistence context.
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    private String category;
    protected Product05() {}
    public Product05(String name, long price, String category) {
        this.name = name; this.price = price; this.category = category;
    }
    public String getName() { return name; }
    public long getPrice() { return price; }
}

interface ProductRepository05 extends JpaRepository<Product05, Long> {

    // TODO: @Modifying
    // TODO: @Query("UPDATE Product05 p SET p.price = p.price * :factor WHERE p.category = :cat")
    int raisePrices(/* @Param("factor") */ double factor, /* @Param("cat") */ String category);
}

@Service
class PriceService05 {
    private final ProductRepository05 repo;
    PriceService05(ProductRepository05 repo) { this.repo = repo; }

    // TODO: @Transactional
    public int raise(String category, double factor) {
        // TODO: return repo.raisePrices(factor, category);
        return 0;
    }
}

@Component
class Runner05 implements CommandLineRunner {
    private final ProductRepository05 repo;
    private final PriceService05 service;
    Runner05(ProductRepository05 repo, PriceService05 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(
                new Product05("Мышь", 1000, "Электроника"),
                new Product05("Клавиатура", 2000, "Электроника"),
                new Product05("Книга", 500, "Книги")));
        // TODO: int changed = service.raise("Электроника", 1.1); System.out.println("Изменено: " + changed);
        // TODO: repo.findAll().forEach(p -> System.out.println(p.getName() + " = " + p.getPrice()));
    }
}
