/**
 * Задача 07 — Модуль 109: МИНИ-ПРОЕКТ «Интеграционный тест на PostgreSQL (Testcontainers)»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-data-jpa, spring-boot-starter-test,
 *   org.testcontainers:postgresql, org.testcontainers:junit-jupiter, org.postgresql:postgresql.
 *   НУЖЕН запущенный Docker.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЦЕЛЬ: полноценный интеграционный тест сервиса каталога на НАСТОЯЩЕМ PostgreSQL.
 *       Один static-контейнер на класс, @ServiceConnection, изоляция через @BeforeEach.
 *       Капстоун модуля.
 *
 * ПРИЛОЖЕНИЕ Task07 (сервис + JPA-репозиторий, готово ниже):
 *   ProductService07: create(name, price), countExpensive(min), findByName(name).
 *
 * ЗАДАНИЕ — @SpringBootTest(classes = Task07.class) + @Testcontainers,
 *           @Container @ServiceConnection static PostgreSQLContainer postgres ("postgres:16-alpine"),
 *           @Autowired ProductService07 service, @Autowired ProductRepository07 repo,
 *           @BeforeEach void clean() { repo.deleteAll(); }:
 *
 *   1) create_and_read():
 *        service.create("Кофе", 100);
 *        assertThat(service.findByName("Кофе")).isPresent();
 *
 *   2) count_expensive():
 *        service.create("Дешёвый", 10);
 *        service.create("Дорогой", 1000);
 *        assertThat(service.countExpensive(100)).isEqualTo(1);
 *
 *   3) isolation_between_tests():
 *        assertThat(repo.count()).isEqualTo(0);   // @BeforeEach очистил общий контейнер
 *
 * ОЖИДАЕМЫЙ ИТОГ: бизнес-логика подтверждена на той же СУБД, что в проде; тесты изолированы.
 *
 * ПОДСКАЗКА: соедините задачи 02 (@ServiceConnection), 04 (@SpringBootTest), 05 (изоляция).
 */
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) { SpringApplication.run(Task07.class, args); }
}

@Entity
class Product07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    protected Product07() {}
    Product07(String name, int price) { this.name = name; this.price = price; }
}

interface ProductRepository07 extends JpaRepository<Product07, Long> {
    Optional<Product07> findByName(String name);
    long countByPriceGreaterThan(int min);
}

@Service
class ProductService07 {
    private final ProductRepository07 repo;
    ProductService07(ProductRepository07 repo) { this.repo = repo; }
    void create(String name, int price) { repo.save(new Product07(name, price)); }
    long countExpensive(int min) { return repo.countByPriceGreaterThan(min); }
    Optional<Product07> findByName(String name) { return repo.findByName(name); }
}

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(classes = Task07.class)
// TODO: @Testcontainers
class CatalogIntegrationSuite07 {

    // TODO: @Container @ServiceConnection
    // TODO: static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    // TODO: @Autowired ProductService07 service;
    // TODO: @Autowired ProductRepository07 repo;

    @BeforeEach
    void clean() {
        // TODO: repo.deleteAll();
    }

    @Test
    void create_and_read() {
        // TODO: service.create("Кофе", 100);
        // TODO: assertThat(service.findByName("Кофе")).isPresent();
    }

    @Test
    void count_expensive() {
        // TODO: service.create("Дешёвый", 10);
        // TODO: service.create("Дорогой", 1000);
        // TODO: assertThat(service.countExpensive(100)).isEqualTo(1);
    }

    @Test
    void isolation_between_tests() {
        // TODO: assertThat(repo.count()).isEqualTo(0);
    }
}
