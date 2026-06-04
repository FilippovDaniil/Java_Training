/**
 * Задача 07 — Модуль 108: МИНИ-ПРОЕКТ «End-to-end тест Task Tracker через HTTP»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-data-jpa, com.h2database:h2, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЦЕЛЬ: сквозной (end-to-end) тест полного приложения через настоящий HTTP:
 *       POST создаёт задачу → GET списка её отражает. Все слои реальные, БД — H2.
 *
 * ПРИЛОЖЕНИЕ Task07 (контроллер + сервис + JPA-репозиторий, готово ниже):
 *   POST /api/tasks {title}  → 201 + TaskDto07;
 *   GET  /api/tasks          → список TaskDto07.
 *
 * ЗАДАНИЕ — @SpringBootTest(classes = Task07.class, webEnvironment = RANDOM_PORT),
 *           @Autowired TestRestTemplate rest:
 *
 *   1) create_then_list_reflects_it():
 *        ResponseEntity<TaskDto07> created = rest.postForEntity(
 *                "/api/tasks", new CreateTask07("Кофе"), TaskDto07.class);
 *        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
 *        Long id = created.getBody().id();
 *
 *        ResponseEntity<TaskDto07[]> list = rest.getForEntity("/api/tasks", TaskDto07[].class);
 *        assertThat(list.getStatusCode()).isEqualTo(HttpStatus.OK);
 *        assertThat(list.getBody()).extracting(TaskDto07::id).contains(id);
 *        assertThat(list.getBody()).extracting(TaskDto07::title).contains("Кофе");
 *
 * ОЖИДАЕМЫЙ ИТОГ: подтверждена интеграция всех слоёв через реальный сетевой стек.
 *
 * ПОДСКАЗКА: для массива в ответе используйте тип TaskDto07[].class.
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Task07 {
    public static void main(String[] args) { SpringApplication.run(Task07.class, args); }
}
