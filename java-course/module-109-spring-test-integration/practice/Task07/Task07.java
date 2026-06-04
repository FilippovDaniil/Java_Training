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

public class Task07 {
    public static void main(String[] args) { SpringApplication.run(Task07.class, args); }
}
