/**
 * Задача 06 — Модуль 82: DTO-проекция вместо загрузки сущностей
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Когда view нужны лишь «имя категории + число товаров», грузить сущности и
 *   трогать ленивые связи незачем. Запросите сразу DTO одним агрегирующим запросом.
 *
 *   1) Объявлен record CategorySummary06(String name, long productCount).
 *   2) В CategoryRepository06 добавьте:
 *        @Query("select new CategorySummary06(c.name, count(p)) " +
 *               "from Category06 c left join c.products p group by c.id, c.name")
 *        List<CategorySummary06> summaries();
 *      (примечание: конструкторное выражение new в JPQL требует ПОЛНОГО имени класса,
 *       если он в пакете; здесь классы в default package — допустимо для шаблона курса.)
 *   3) report(): вызвать summaries() (БЕЗ @Transactional — сущностей нет, lazy не при чём)
 *      и вывести каждую строку.
 *
 * ЦЕЛЬ: понять, что DTO-проекция полностью снимает проблемы lazy/N+1 для read-моделей.
 *
 * ПОДСКАЗКА: нет управляемых сущностей → нет прокси → нет LazyInitializationException.
 *            left join — чтобы категории без товаров тоже попали (count = 0).
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}

record CategorySummary06(String name, long productCount) {}

@Entity @Table(name = "categories")
class Category06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product06> products = new ArrayList<>();
    protected Category06() {}
    public Category06(String name) { this.name = name; }
    public void addProduct(Product06 p) { products.add(p); p.setCategory(this); }
}

@Entity @Table(name = "products")
class Product06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category06 category;
    protected Product06() {}
    public Product06(String name) { this.name = name; }
    public void setCategory(Category06 c) { this.category = c; }
}

interface CategoryRepository06 extends JpaRepository<Category06, Long> {
    // TODO: @Query("select new CategorySummary06(c.name, count(p)) " +
    // TODO:        "from Category06 c left join c.products p group by c.id, c.name")
    // TODO: List<CategorySummary06> summaries();
}

@Service
class ReportService06 {
    private final CategoryRepository06 repo;
    ReportService06(CategoryRepository06 repo) { this.repo = repo; }

    public void report() {
        // TODO: for (CategorySummary06 s : repo.summaries())
        // TODO:     System.out.println(s.name() + " -> " + s.productCount());
    }
}

@Component
class Runner06 implements CommandLineRunner {
    private final CategoryRepository06 repo;
    private final ReportService06 service;
    Runner06(CategoryRepository06 repo, ReportService06 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        Category06 electronics = new Category06("Электроника");
        electronics.addProduct(new Product06("Ноутбук"));
        electronics.addProduct(new Product06("Телефон"));
        repo.save(electronics);
        repo.save(new Category06("Пустая категория"));   // count должен быть 0
        // TODO: service.report();
    }
}
