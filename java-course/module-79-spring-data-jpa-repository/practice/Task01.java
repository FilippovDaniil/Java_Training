/**
 * Задача 01 — Модуль 79: Базовый CRUD через JpaRepository
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) ProductRepository01 extends JpaRepository<Product01, Long> (готов — пустой).
 *   2) В CommandLineRunner используйте ТОЛЬКО готовые методы JpaRepository:
 *        - saveAll(List.of(...)) — сохранить 3 товара
 *        - count()              — вывести количество
 *        - findById(1L)         — вывести имя первого (Optional!)
 *        - existsById(1L)       — вывести true/false
 *        - deleteById(1L), затем снова count()
 *
 * ЦЕЛЬ: освоить «бесплатный» CRUD без единого собственного метода.
 *
 * ПОДСКАЗКА: findById возвращает Optional — .orElseThrow() или .map(...).
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
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
    protected Product01() {}
    public Product01(String name, long price) { this.name = name; this.price = price; }
    public String getName() { return name; }
}

interface ProductRepository01 extends JpaRepository<Product01, Long> {}

@Component
class CrudRunner01 implements CommandLineRunner {
    private final ProductRepository01 repo;
    CrudRunner01(ProductRepository01 repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        // TODO: repo.saveAll(List.of(new Product01("Кофе",500), new Product01("Чай",300), new Product01("Сахар",100)));
        // TODO: System.out.println("Всего: " + repo.count());
        // TODO: repo.findById(1L).ifPresent(p -> System.out.println("Первый: " + p.getName()));
        // TODO: System.out.println("Есть #1: " + repo.existsById(1L));
        // TODO: repo.deleteById(1L); System.out.println("После удаления: " + repo.count());
    }
}
