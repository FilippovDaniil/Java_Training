/**
 * Задача 06 — Модуль 77: Сервисный слой поверх репозитория
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Правильная архитектура: Контроллер/Runner → Сервис → Репозиторий → БД.
 *   1) ProductService06 (@Service) внедряет ProductRepository06.
 *        - addProduct(name, price): сохранить и вернуть Product06
 *        - findExpensive(min): вернуть из findAll() только товары с price >= min (через Stream)
 *        - total(): вернуть repo.count()
 *   2) В CommandLineRunner используйте СЕРВИС (не репозиторий напрямую):
 *        добавьте 3 товара, выведите дорогие (>= 400) и общее число.
 *
 * ЦЕЛЬ: закрепить слоистую архитектуру — бизнес-логика в сервисе, доступ к данным в репозитории.
 *
 * ПОДСКАЗКА: репозиторий внедряется в сервис, сервис — в Runner/контроллер.
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@SpringBootApplication
public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}

@Entity
@Table(name = "products")
class Product06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;

    protected Product06() {}
    public Product06(String name, long price) { this.name = name; this.price = price; }
    public String getName() { return name; }
    public long getPrice() { return price; }
}

interface ProductRepository06 extends JpaRepository<Product06, Long> {}

// TODO: @Service
class ProductService06 {

    // TODO: внедрите ProductRepository06 через конструктор

    public Product06 addProduct(String name, long price) {
        // TODO: return repo.save(new Product06(name, price));
        return null;
    }

    public List<Product06> findExpensive(long min) {
        // TODO: repo.findAll().stream().filter(p -> p.getPrice() >= min).toList()
        return null;
    }

    public long total() {
        // TODO: return repo.count();
        return 0;
    }
}

@Component
class ServiceRunner06 implements CommandLineRunner {
    private final ProductService06 service;
    ServiceRunner06(ProductService06 service) { this.service = service; }

    @Override
    public void run(String... args) {
        // TODO: добавьте "Кофе"(500), "Чай"(300), "Кофемашина"(50000)
        // TODO: выведите дорогие (>=400) и service.total()
    }
}
