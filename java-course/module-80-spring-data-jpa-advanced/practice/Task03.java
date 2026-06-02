/**
 * Задача 03 — Модуль 80: Интерфейсная проекция (выбрать часть полей)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Не тяните всю сущность — верните только имя и цену через интерфейсную проекцию.
 *   1) Объявите интерфейс-проекцию:
 *        interface ProductView03 { String getName(); long getPrice(); }
 *   2) В репозитории derived-метод, возвращающий проекцию:
 *        List<ProductView03> findByCategory(String category);
 *   3) В CommandLineRunner выведите имя и цену каждой проекции.
 *
 * ЦЕЛЬ: вернуть «срез» данных — это быстрее, чем грузить полную сущность.
 *
 * ПОДСКАЗКА: имена геттеров проекции должны совпадать с полями сущности (getName ↔ name).
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
    private String secretNote;   // не хотим тянуть наружу
    protected Product03() {}
    public Product03(String name, long price, String category) {
        this.name = name; this.price = price; this.category = category; this.secretNote = "служебное";
    }
}

// TODO: интерфейс-проекция
interface ProductView03 {
    // TODO: String getName();
    // TODO: long getPrice();
}

interface ProductRepository03 extends JpaRepository<Product03, Long> {
    // TODO: List<ProductView03> findByCategory(String category);
}

@Component
class Runner03 implements CommandLineRunner {
    private final ProductRepository03 repo;
    Runner03(ProductRepository03 repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(
                new Product03("Ноутбук", 80000, "Электроника"),
                new Product03("Мышь", 900, "Электроника")));
        // TODO: repo.findByCategory("Электроника").forEach(v -> System.out.println(v.getName() + " = " + v.getPrice()));
    }
}
