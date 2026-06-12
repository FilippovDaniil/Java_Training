package m109_spring_test_integration.practice.task05;

/**
 * Задача 05 — Модуль 109: общий контейнер на класс (static) и изоляция тестов
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-data-jpa, spring-boot-starter-test,
 *   org.testcontainers:postgresql, org.testcontainers:junit-jupiter, org.postgresql:postgresql.
 *   НУЖЕН запущенный Docker.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Один static-контейнер обслуживает несколько тестов класса. Обеспечьте изоляцию данных
 *   между тестами очисткой перед каждым:
 *     1) @DataJpaTest; @AutoConfigureTestDatabase(replace = NONE); @Testcontainers.
 *     2) @Container @ServiceConnection static PostgreSQLContainer<?> postgres = ...("postgres:16-alpine").
 *     3) @Autowired ProductRepository05 repository;
 *        @BeforeEach void clean() { repository.deleteAll(); }   // изоляция: контейнер общий
 *     4) first_test(): repository.save(new Product05("Кофе")); assertThat(repository.count()).isEqualTo(1).
 *     5) second_test(): assertThat(repository.count()).isEqualTo(0).  // чисто, т.к. deleteAll в @BeforeEach
 *
 * ЦЕЛЬ: понять, что static-контейнер ОДИН на класс (быстро), а изоляцию данных обеспечиваем сами.
 *
 * ВАЖНО: @DataJpaTest откатывает транзакцию, но контейнер общий — для надёжности чистим явно.
 *
 * ПОДСКАЗКА: один контейнер на класс экономит секунды старта (его поднятие — дорого).
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.BeforeEach;
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
class SharedContainerTest05 {

    // TODO: @Container @ServiceConnection
    // TODO: static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    // TODO: @Autowired ProductRepository05 repository;

    @BeforeEach
    void clean() {
        // TODO: repository.deleteAll();
    }

    @Test
    void first_test() {
        // TODO: repository.save(new Product05("Кофе"));
        // TODO: assertThat(repository.count()).isEqualTo(1);
    }

    @Test
    void second_test() {
        // TODO: assertThat(repository.count()).isEqualTo(0);
    }
}
