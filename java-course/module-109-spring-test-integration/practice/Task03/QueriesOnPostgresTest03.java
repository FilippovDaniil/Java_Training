/**
 * Задача 03 — Модуль 109: derived/@Query на реальном PostgreSQL
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-data-jpa, spring-boot-starter-test,
 *   org.testcontainers:postgresql, org.testcontainers:junit-jupiter, org.postgresql:postgresql.
 *   НУЖЕН запущенный Docker.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Проверьте, что запросы репозитория работают на НАСТОЯЩЕМ Postgres (а не только на H2):
 *     1) @DataJpaTest; @AutoConfigureTestDatabase(replace = NONE); @Testcontainers.
 *     2) @Container @ServiceConnection static PostgreSQLContainer<?> postgres = ...("postgres:16-alpine").
 *     3) @Autowired ProductRepository03 repository.
 *     4) derived_query_on_pg():
 *          repository.save(new Product03("Кофе арабика", 100));
 *          repository.save(new Product03("Чай", 50));
 *          assertThat(repository.findByNameContainingIgnoreCase("кофе")).hasSize(1);
 *     5) custom_query_on_pg():
 *          assertThat(repository.findExpensive(60)).hasSize(1);
 *
 * ЦЕЛЬ: убедиться, что derived/@Query-методы корректны на проде-СУБД (диалект Postgres).
 *
 * ВАЖНО: ILIKE/регистронезависимость и др. могут вести себя иначе, чем в H2 — потому и тестируем на Postgres.
 *
 * ПОДСКАЗКА: данные сохраняются и читаются из реального контейнера PostgreSQL.
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

// --- ТЕСТ (каркас) ---
// TODO: @DataJpaTest
// TODO: @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// TODO: @Testcontainers
class QueriesOnPostgresTest03 {

    // TODO: @Container @ServiceConnection
    // TODO: static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    // TODO: @Autowired ProductRepository03 repository;

    @Test
    void derived_query_on_pg() {
        // TODO: repository.save(new Product03("Кофе арабика", 100));
        // TODO: repository.save(new Product03("Чай", 50));
        // TODO: assertThat(repository.findByNameContainingIgnoreCase("кофе")).hasSize(1);
    }

    @Test
    void custom_query_on_pg() {
        // TODO: repository.save(new Product03("Дешёвый", 10));
        // TODO: repository.save(new Product03("Дорогой", 1000));
        // TODO: assertThat(repository.findExpensive(60)).hasSize(1);
    }
}
