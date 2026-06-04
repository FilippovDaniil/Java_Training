/**
 * Задача 02 — Модуль 109: @ServiceConnection — современный способ (без @DynamicPropertySource)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-data-jpa, spring-boot-starter-test,
 *   org.testcontainers:postgresql, org.testcontainers:junit-jupiter, org.postgresql:postgresql.
 *   Spring Boot 3.1+ (для @ServiceConnection). НУЖЕН запущенный Docker.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   То же, что в задаче 01, но БЕЗ ручного прокидывания свойств — через @ServiceConnection:
 *     1) @DataJpaTest; @AutoConfigureTestDatabase(replace = NONE); @Testcontainers.
 *     2) @Container @ServiceConnection
 *        static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");
 *     3) @Autowired ProductRepository02 repository.
 *     4) auto_wired_datasource():
 *          repository.save(new Product02("Чай", 50));
 *          assertThat(repository.count()).isEqualTo(1);
 *
 * ЦЕЛЬ: освоить @ServiceConnection — Spring сам берёт url/username/password из контейнера.
 *
 * ВАЖНО: @ServiceConnection заменяет @DynamicPropertySource — рекомендуется в новых проектах.
 *
 * ПОДСКАЗКА: сравните с задачей 01 — здесь нет метода props(), и это короче.
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
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;

// --- ТЕСТ (каркас) ---
// TODO: @DataJpaTest
// TODO: @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// TODO: @Testcontainers
class ServiceConnectionTest02 {

    // TODO: @Container
    // TODO: @ServiceConnection
    // TODO: static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    // TODO: @Autowired ProductRepository02 repository;

    @Test
    void auto_wired_datasource() {
        // TODO: repository.save(new Product02("Чай", 50));
        // TODO: assertThat(repository.count()).isEqualTo(1);
    }
}
