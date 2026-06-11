package m108_spring_test_full_context.practice.task06;

/**
 * Задача 06 — Модуль 108: реальные бины всех слоёв (@SpringBootTest без @MockBean)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-data-jpa, com.h2database:h2, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Проверьте сквозное прохождение через РЕАЛЬНЫЕ бины (контроллер не нужен — дёргаем сервис):
 *     1) @SpringBootTest(classes = Task06.class);   // webEnvironment=MOCK, веб не нужен
 *        @Autowired TaskService06 service; @Autowired TaskRepository06 repository.
 *     2) create_persists_through_real_stack():
 *          Long id = service.create("Кофе");        // реальный сервис → реальный репозиторий → H2
 *          assertThat(repository.findById(id)).isPresent();
 *          assertThat(repository.findById(id).orElseThrow().getTitle()).isEqualTo("Кофе");
 *
 * ЦЕЛЬ: убедиться, что в @SpringBootTest сервис и репозиторий — НАСТОЯЩИЕ (не моки),
 *       и данные реально проходят весь стек до БД.
 *
 * ВАЖНО: @Transactional на тесте откатит данные после (изоляция между тестами).
 *
 * ПОДСКАЗКА: здесь не мокаем ничего — в этом и смысл интеграционного теста.
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

public class Task06 {
    public static void main(String[] args) { SpringApplication.run(Task06.class, args); }
}
