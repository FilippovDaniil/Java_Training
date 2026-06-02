/**
 * Задача 03 — Модуль 81: readOnly = true для читающих методов
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) В CatalogService03 разделите методы по назначению:
 *        - findAll():     @Transactional(readOnly = true)   — только чтение
 *        - countAll():    @Transactional(readOnly = true)
 *        - add(name):     @Transactional                    — изменение
 *   2) В CommandLineRunner добавьте 2 товара через add(...), затем выведите findAll/countAll.
 *
 * ВОПРОС (ответьте комментарием):
 *   Что даёт readOnly = true? Почему его НЕ ставят на методы, изменяющие данные?
 *
 * ПОДСКАЗКА: readOnly подсказывает Hibernate отключить dirty checking → быстрее на чтении.
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    protected Product03() {}
    public Product03(String name) { this.name = name; }
    public String getName() { return name; }
}

interface ProductRepository03 extends JpaRepository<Product03, Long> {}

@Service
class CatalogService03 {
    private final ProductRepository03 repo;
    CatalogService03(ProductRepository03 repo) { this.repo = repo; }

    // TODO: @Transactional(readOnly = true)
    public List<Product03> findAll() {
        return repo.findAll();
    }

    // TODO: @Transactional(readOnly = true)
    public long countAll() {
        return repo.count();
    }

    // TODO: @Transactional
    public Product03 add(String name) {
        return repo.save(new Product03(name));
    }
}

@Component
class Runner03 implements CommandLineRunner {
    private final CatalogService03 service;
    Runner03(CatalogService03 service) { this.service = service; }

    @Override
    public void run(String... args) {
        // TODO: service.add("Кофе"); service.add("Чай");
        // TODO: System.out.println("Всего: " + service.countAll());
        // TODO: service.findAll().forEach(p -> System.out.println(p.getName()));
    }
}
