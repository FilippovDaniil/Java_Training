package m109_spring_test_integration.practice.task04;

/**
 * Задача 04 — Модуль 109: @SpringBootTest + Testcontainers (полная интеграция)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-data-jpa, spring-boot-starter-test,
 *   org.testcontainers:postgresql, org.testcontainers:junit-jupiter, org.postgresql:postgresql.
 *   НУЖЕН запущенный Docker.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Поднимите ВЕСЬ контекст приложения поверх реального PostgreSQL:
 *     1) @SpringBootTest(classes = Task04.class); @Testcontainers.
 *     2) @Container @ServiceConnection
 *        static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine").
 *     3) @Autowired ProductService04 service.
 *     4) end_to_end_on_postgres():
 *          service.create("Кофе", 100);
 *          assertThat(service.count()).isEqualTo(1);
 *
 * ЦЕЛЬ: end-to-end через реальный сервис → репозиторий → настоящий PostgreSQL.
 *
 * ВАЖНО: в @SpringBootTest @ServiceConnection настраивает основной datasource приложения.
 *
 * ПОДСКАЗКА: сочетание из модулей 108 (полный контекст) и 109 (контейнер).
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
import static org.assertj.core.api.Assertions.assertThat;

public class Task04 {
    public static void main(String[] args) { SpringApplication.run(Task04.class, args); }
}
