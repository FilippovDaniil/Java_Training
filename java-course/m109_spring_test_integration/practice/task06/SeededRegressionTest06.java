package m109_spring_test_integration.practice.task06;

/**
 * Задача 06 — Модуль 109: сидинг через @Sql на реальном PostgreSQL
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-data-jpa, spring-boot-starter-test,
 *   org.testcontainers:postgresql, org.testcontainers:junit-jupiter, org.postgresql:postgresql.
 *   НУЖЕН запущенный Docker.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Засейте данные SQL-скриптом на настоящем Postgres и проверьте запрос (регрессионный сценарий):
 *     1) Создайте src/test/resources/seed-pg.sql:
 *          INSERT INTO product06 (name, price) VALUES ('Кофе', 100);
 *          INSERT INTO product06 (name, price) VALUES ('Чай', 50);
 *          INSERT INTO product06 (name, price) VALUES ('Сок', 70);
 *     2) @DataJpaTest; @AutoConfigureTestDatabase(replace = NONE); @Testcontainers.
 *     3) @Container @ServiceConnection static PostgreSQLContainer<?> postgres = ...("postgres:16-alpine").
 *     4) @Autowired ProductRepository06 repository.
 *     5) Метод seeded_query() пометьте @Sql("/seed-pg.sql"):
 *          assertThat(repository.count()).isEqualTo(3);
 *          assertThat(repository.findByPriceGreaterThan(60)).hasSize(2);  // Кофе(100), Сок(70)
 *
 * ЦЕЛЬ: регрессионный тест — детерминированные данные (@Sql) + проверка на проде-СУБД.
 *
 * ВАЖНО: схему создаёт Hibernate (ddl-auto в @DataJpaTest), затем @Sql наполняет данными.
 *
 * ПОДСКАЗКА: @Sql выполняется ПОСЛЕ создания схемы — таблица product06 уже существует.
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
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

// --- ТЕСТ (каркас) ---
// TODO: @DataJpaTest
// TODO: @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// TODO: @Testcontainers
class SeededRegressionTest06 {

    // TODO: @Container @ServiceConnection
    // TODO: static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    // TODO: @Autowired ProductRepository06 repository;

    @Test
    // TODO: @Sql("/seed-pg.sql")
    void seeded_query() {
        // TODO: assertThat(repository.count()).isEqualTo(3);
        // TODO: assertThat(repository.findByPriceGreaterThan(60)).hasSize(2);
    }
}
