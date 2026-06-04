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

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) { SpringApplication.run(Task07.class, args); }
}

record CreateTask07(String title) {}
record TaskDto07(Long id, String title) {}

@Entity
class TaskEntity07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    protected TaskEntity07() {}
    TaskEntity07(String title) { this.title = title; }
    Long getId() { return id; }
    String getTitle() { return title; }
}

interface TaskRepository07 extends JpaRepository<TaskEntity07, Long> {}

@Service
class TaskService07 {
    private final TaskRepository07 repo;
    TaskService07(TaskRepository07 repo) { this.repo = repo; }
    TaskDto07 create(String title) {
        TaskEntity07 saved = repo.save(new TaskEntity07(title));
        return new TaskDto07(saved.getId(), saved.getTitle());
    }
    List<TaskDto07> all() {
        return repo.findAll().stream().map(t -> new TaskDto07(t.getId(), t.getTitle())).toList();
    }
}

@RestController
@RequestMapping("/api/tasks")
class TaskController07 {
    private final TaskService07 service;
    TaskController07(TaskService07 service) { this.service = service; }

    @PostMapping
    ResponseEntity<TaskDto07> create(@RequestBody CreateTask07 req) {
        TaskDto07 dto = service.create(req.title());
        return ResponseEntity.created(URI.create("/api/tasks/" + dto.id())).body(dto);
    }

    @GetMapping
    List<TaskDto07> all() { return service.all(); }
}

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(
// TODO:     classes = Task07.class,
// TODO:     webEnvironment = org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT)
class EndToEndTest07 {

    // TODO: @Autowired TestRestTemplate rest;

    @Test
    void create_then_list_reflects_it() {
        // TODO: ResponseEntity<TaskDto07> created = rest.postForEntity(
        // TODO:         "/api/tasks", new CreateTask07("Кофе"), TaskDto07.class);
        // TODO: assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        // TODO: Long id = created.getBody().id();
        // TODO: ResponseEntity<TaskDto07[]> list = rest.getForEntity("/api/tasks", TaskDto07[].class);
        // TODO: assertThat(list.getStatusCode()).isEqualTo(HttpStatus.OK);
        // TODO: assertThat(list.getBody()).extracting(TaskDto07::id).contains(id);
        // TODO: assertThat(list.getBody()).extracting(TaskDto07::title).contains("Кофе");
    }
}
