/**
 * Задача 04 — Модуль 84: оптимистичная блокировка — поле @Version
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) В Product04 добавьте поле версии:
 *        @Version
 *        private long version;
 *      и геттер getVersion().
 *   2) priceUpdate(id, newPrice): @Transactional — загрузить товар, setPrice (dirty checking).
 *   3) В CommandLineRunner: сохраните товар (version=0 после INSERT), затем дважды
 *      обновите цену через сервис; после каждого обновления перечитайте товар и
 *      выведите version — она должна расти: 0 → 1 → 2.
 *
 * ЦЕЛЬ: увидеть, что Hibernate сам инкрементит @Version при каждом UPDATE
 *       (UPDATE ... SET version = version + 1 WHERE id=? AND version=?).
 *
 * ПОДСКАЗКА: версией управляет Hibernate — НЕ присваивайте её вручную.
 *            Перечитывать товар удобнее в отдельном @Transactional-методе.
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private int price;

    // TODO: @Version
    private long version;

    protected Product04() {}
    public Product04(String name, int price) { this.name = name; this.price = price; }
    public Long getId() { return id; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public long getVersion() { return version; }
}

interface ProductRepository04 extends JpaRepository<Product04, Long> {}

@Service
class ProductService04 {
    private final ProductRepository04 repo;
    ProductService04(ProductRepository04 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void priceUpdate(Long id, int newPrice) {
        // TODO: repo.findById(id).orElseThrow().setPrice(newPrice);   // dirty checking, version++
    }

    @Transactional(readOnly = true)
    public long versionOf(Long id) {
        return repo.findById(id).orElseThrow().getVersion();
    }
}

@Component
class Runner04 implements CommandLineRunner {
    private final ProductRepository04 repo;
    private final ProductService04 service;
    Runner04(ProductRepository04 repo, ProductService04 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        Product04 p = repo.save(new Product04("Ноутбук", 50000));
        Long id = p.getId();
        // TODO: System.out.println("после INSERT: " + service.versionOf(id));  // 0
        // TODO: service.priceUpdate(id, 48000); System.out.println(service.versionOf(id)); // 1
        // TODO: service.priceUpdate(id, 47000); System.out.println(service.versionOf(id)); // 2
    }
}
