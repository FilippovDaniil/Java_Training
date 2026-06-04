/**
 * Задача 03 — Модуль 107: JOIN FETCH инициализирует коллекцию (нет Lazy-ошибки)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-data-jpa, com.h2database:h2, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Репозиторий имеет метод с JOIN FETCH (готов). Докажите, что коллекция загружена сразу —
 *   её можно трогать даже после детача:
 *     1) @DataJpaTest; @Autowired TestEntityManager em; @Autowired CategoryRepository03 repo.
 *     2) join_fetch_initializes():
 *          Long id = em.persistAndFlush(categoryWith2()).getId();
 *          em.clear();
 *          Category03 loaded = repo.findByIdWithProducts(id).orElseThrow();
 *          em.detach(loaded);                                       // даже после детача...
 *          assertThat(Hibernate.isInitialized(loaded.getProducts())).isTrue();
 *          assertThat(loaded.getProducts()).hasSize(2);             // ...без LazyInitializationException
 *
 * ЦЕЛЬ: показать, как JOIN FETCH решает проблему lazy — связь грузится одним запросом.
 *
 * ВАЖНО: Hibernate.isInitialized(collection) проверяет, инициализирована ли связь.
 *
 * ПОДСКАЗКА: сравните с задачей 02 — там детач ломал доступ, здесь JOIN FETCH спасает.
 */
import jakarta.persistence.*;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

// --- сущности и репозиторий (готовы) ---
@Entity
class Category03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product03> products = new ArrayList<>();

    protected Category03() {}
    Category03(String name) { this.name = name; }
    void addProduct(Product03 p) { products.add(p); p.setCategory(this); }
    Long getId() { return id; }
    List<Product03> getProducts() { return products; }
}

@Entity
class Product03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category03 category;

    protected Product03() {}
    Product03(String name) { this.name = name; }
    void setCategory(Category03 c) { this.category = c; }
}

interface CategoryRepository03 extends JpaRepository<Category03, Long> {
    @Query("select c from Category03 c join fetch c.products where c.id = :id")
    Optional<Category03> findByIdWithProducts(@Param("id") Long id);
}

// --- ТЕСТ (каркас) ---
// TODO: @DataJpaTest
class JoinFetchTest03 {

    // TODO: @Autowired TestEntityManager em;
    // TODO: @Autowired CategoryRepository03 repo;

    @Test
    void join_fetch_initializes() {
        // TODO: Long id = em.persistAndFlush(categoryWith2()).getId();
        // TODO: em.clear();
        // TODO: Category03 loaded = repo.findByIdWithProducts(id).orElseThrow();
        // TODO: em.detach(loaded);
        // TODO: assertThat(Hibernate.isInitialized(loaded.getProducts())).isTrue();
        // TODO: assertThat(loaded.getProducts()).hasSize(2);
    }

    private Category03 categoryWith2() {
        Category03 c = new Category03("Напитки");
        c.addProduct(new Product03("Кофе"));
        c.addProduct(new Product03("Чай"));
        return c;
    }
}
