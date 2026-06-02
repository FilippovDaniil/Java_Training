/**
 * Задача 05 — Модуль 76: Интеграционный тест @SpringBootTest + TestRestTemplate
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-test:3.2.x (см. theory.md).
 *
 * Это ТЕСТ-КЛАСС (без main). Поднимает ВЕСЬ контекст и реальный сервер.
 *
 * ЗАДАНИЕ:
 *   1) Класс TaskApiIT05 пометьте
 *        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 *      — поднимется встроенный Tomcat на случайном порту.
 *   2) Внедрите @Autowired TestRestTemplate rest.
 *   3) Тесты (бьют по настоящему HTTP):
 *        getReturnsTask():
 *          ResponseEntity<TaskDto05> r = rest.getForEntity("/api/tasks/1", TaskDto05.class);
 *          assertEquals(HttpStatus.OK, r.getStatusCode());
 *          assertEquals("Кофе", r.getBody().title());
 *        createReturns201():
 *          ResponseEntity<TaskDto05> r = rest.postForEntity("/api/tasks",
 *                 new CreateDto05("Чай"), TaskDto05.class);
 *          assertEquals(HttpStatus.CREATED, r.getStatusCode());
 *
 * ЦЕЛЬ: сквозная проверка через реальный HTTP-стек (без моков).
 *
 * ПОДСКАЗКА: TestRestTemplate сам сериализует тело и десериализует ответ.
 */
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

// --- Приложение, DTO, контроллер (готовы) ---
@SpringBootApplication
class IntegrationApp05 {}

record TaskDto05(Long id, String title) {}
record CreateDto05(String title) {}

@RestController
@RequestMapping("/api/tasks")
class TaskController05 {
    @GetMapping("/{id}")
    public TaskDto05 get(@PathVariable Long id) { return new TaskDto05(id, "Кофе"); }

    @PostMapping
    public ResponseEntity<TaskDto05> create(@RequestBody CreateDto05 dto) {
        return ResponseEntity.created(URI.create("/api/tasks/2")).body(new TaskDto05(2L, dto.title()));
    }
}

// --- ТЕСТ (каркас) ---
// TODO: @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskApiIT05 {

    // TODO: @Autowired TestRestTemplate rest;

    @Test
    void getReturnsTask() {
        // TODO: ResponseEntity<TaskDto05> r = rest.getForEntity("/api/tasks/1", TaskDto05.class);
        // TODO: assertEquals(HttpStatus.OK, r.getStatusCode());
        // TODO: assertEquals("Кофе", r.getBody().title());
    }

    @Test
    void createReturns201() {
        // TODO: ResponseEntity<TaskDto05> r = rest.postForEntity("/api/tasks", new CreateDto05("Чай"), TaskDto05.class);
        // TODO: assertEquals(HttpStatus.CREATED, r.getStatusCode());
    }
}
