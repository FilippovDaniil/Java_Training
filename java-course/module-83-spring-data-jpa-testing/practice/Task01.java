/**
 * Задача 01 — Модуль 83: первый @DataJpaTest (save + findById)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-data-jpa, spring-boot-starter-test, com.h2database:h2 (см. theory.md).
 *
 * ФОРМАТ: это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   1) Пометьте класс @DataJpaTest (поднимет JPA-слой на встроенной H2, авто-rollback).
 *   2) Внедрите ProductRepository01 через @Autowired.
 *   3) Тест saves_and_finds_by_id():
 *        - сохранить new Product01("Молоко");
 *        - проверить, что repo.findById(saved.getId()) присутствует (isPresent)
 *          и имя совпадает ("Молоко").
 *   4) Тест missing_id_returns_empty(): repo.findById(999L) → isEmpty().
 *
 * ЦЕЛЬ: запустить первый срез слоя данных и понять, что H2 подключается сама.
 *
 * ПОДСКАЗКА: assertThat(...).isPresent() / .isEmpty() для Optional; AssertJ уже на classpath.
 */
import jakarta.persistence.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;

import static org.assertj.core.api.Assertions.assertThat;

// TODO: @DataJpaTest
class Task01 {

    // TODO: @Autowired
    ProductRepository01 repo;

    @Test
    void saves_and_finds_by_id() {
        // TODO: Product01 saved = repo.save(new Product01("Молоко"));
        // TODO: assertThat(repo.findById(saved.getId())).isPresent();
        // TODO: assertThat(repo.findById(saved.getId()).get().getName()).isEqualTo("Молоко");
    }

    @Test
    void missing_id_returns_empty() {
        // TODO: assertThat(repo.findById(999L)).isEmpty();
    }
}

// --- класс-под-тестом дан готовым ---
@Entity @Table(name = "products")
class Product01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    protected Product01() {}
    public Product01(String name) { this.name = name; }
    public Long getId() { return id; }
    public String getName() { return name; }
}

interface ProductRepository01 extends JpaRepository<Product01, Long> {}
