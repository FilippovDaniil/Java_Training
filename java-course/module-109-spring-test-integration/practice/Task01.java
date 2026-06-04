/**
 * Задача 01 — Модуль 109: @Testcontainers + @Container + @DynamicPropertySource (классика)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-data-jpa, spring-boot-starter-test,
 *   org.testcontainers:postgresql, org.testcontainers:junit-jupiter, org.postgresql:postgresql.
 *   НУЖЕН запущенный Docker (Rancher/Docker Desktop).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Подключите реальный PostgreSQL через Testcontainers и протестируйте репозиторий:
 *     1) @DataJpaTest;
 *        @AutoConfigureTestDatabase(replace = NONE);   // НЕ подменять на H2!
 *        @Testcontainers.
 *     2) @Container static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");
 *     3) @DynamicPropertySource static void props(DynamicPropertyRegistry r):
 *          r.add("spring.datasource.url", postgres::getJdbcUrl);
 *          r.add("spring.datasource.username", postgres::getUsername);
 *          r.add("spring.datasource.password", postgres::getPassword);
 *     4) @Autowired ProductRepository01 repository.
 *     5) runs_on_real_postgres():
 *          repository.save(new Product01("Кофе", 100));
 *          assertThat(repository.count()).isEqualTo(1);
 *
 * ЦЕЛЬ: научиться поднимать настоящий PostgreSQL для теста и прокидывать его координаты в Spring.
 *
 * ВАЖНО: @Container на STATIC-поле → один контейнер на класс; replace=NONE обязателен.
 *
 * ПОДСКАЗКА: getJdbcUrl/getUsername/getPassword — методы контейнера, передаются как Supplier.
 */
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

// --- entity и репозиторий (готовы) ---
@Entity
class Product01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    protected Product01() {}
    Product01(String name, int price) { this.name = name; this.price = price; }
}

interface ProductRepository01 extends JpaRepository<Product01, Long> {}

// --- ТЕСТ (каркас) ---
// TODO: @DataJpaTest
// TODO: @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// TODO: @Testcontainers
class PostgresRepositoryTest01 {

    // TODO: @Container
    // TODO: static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    // TODO: @DynamicPropertySource
    // TODO: static void props(DynamicPropertyRegistry r) {
    // TODO:     r.add("spring.datasource.url", postgres::getJdbcUrl);
    // TODO:     r.add("spring.datasource.username", postgres::getUsername);
    // TODO:     r.add("spring.datasource.password", postgres::getPassword);
    // TODO: }

    // TODO: @Autowired ProductRepository01 repository;

    @Test
    void runs_on_real_postgres() {
        // TODO: repository.save(new Product01("Кофе", 100));
        // TODO: assertThat(repository.count()).isEqualTo(1);
    }
}
