/**
 * Задача 01 — Модуль 82: связи и FetchType (LAZY/EAGER)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Свяжите Category01 (1) и Product01 (N):
 *        - в Category01: @OneToMany(mappedBy = "category") List<Product01> products
 *          (по умолчанию LAZY — оставьте так);
 *        - в Product01: @ManyToOne(fetch = FetchType.LAZY) Category01 category
 *          (ЯВНО LAZY — иначе @ManyToOne был бы EAGER).
 *   2) В CommandLineRunner: создайте категорию "Электроника", два товара в ней,
 *      сохраните; выведите products.size() для категории.
 *
 * ЦЕЛЬ: освоить двунаправленную связь и осознанный выбор FetchType.LAZY.
 *
 * ПОДСКАЗКА: владелец связи — сторона с @ManyToOne (колонка category_id).
 *            mappedBy на @OneToMany указывает имя поля-владельца.
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}

@Entity @Table(name = "categories")
class Category01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // TODO: @OneToMany(mappedBy = "category")
    private List<Product01> products = new ArrayList<>();

    protected Category01() {}
    public Category01(String name) { this.name = name; }
    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Product01> getProducts() { return products; }
}

@Entity @Table(name = "products")
class Product01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // TODO: @ManyToOne(fetch = FetchType.LAZY)  @JoinColumn(name = "category_id")
    private Category01 category;

    protected Product01() {}
    public Product01(String name, Category01 category) { this.name = name; this.category = category; }
    public Long getId() { return id; }
}

interface CategoryRepository01 extends JpaRepository<Category01, Long> {}
interface ProductRepository01 extends JpaRepository<Product01, Long> {}

@Component
class Runner01 implements CommandLineRunner {
    private final CategoryRepository01 categories;
    private final ProductRepository01 products;
    Runner01(CategoryRepository01 c, ProductRepository01 p) { this.categories = c; this.products = p; }

    @Override
    @Transactional
    public void run(String... args) {
        Category01 electronics = categories.save(new Category01("Электроника"));
        // TODO: products.save(new Product01("Ноутбук", electronics));
        // TODO: products.save(new Product01("Телефон", electronics));
        // TODO: внутри транзакции выведите categories.findById(electronics.getId()).get().getProducts().size()
    }
}
