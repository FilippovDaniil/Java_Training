/**
 * Задача 03 — Модуль 108: TestRestTemplate POST (создание через HTTP)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Эндпоинт создаёт задачу и возвращает 201 (готов). Отправьте POST по HTTP:
 *     1) @SpringBootTest(classes = Task03.class, webEnvironment = RANDOM_PORT);
 *        @Autowired TestRestTemplate restTemplate.
 *     2) post_creates():
 *          ResponseEntity<TaskDto03> resp = restTemplate.postForEntity(
 *                  "/api/tasks", new CreateTask03("Кофе"), TaskDto03.class);
 *          assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
 *          assertThat(resp.getBody().title()).isEqualTo("Кофе");
 *
 * ЦЕЛЬ: тестировать POST с телом и десериализацию ответа через настоящий HTTP-клиент.
 *
 * ВАЖНО: postForEntity(url, body, ResponseType) сериализует тело и парсит ответ в DTO.
 *
 * ПОДСКАЗКА: TestRestTemplate использует Jackson для (де)сериализации тела автоматически.
 */
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootApplication
public class Task03 {
    public static void main(String[] args) { SpringApplication.run(Task03.class, args); }
}

record CreateTask03(String title) {}
record TaskDto03(Long id, String title) {}

@RestController
@RequestMapping("/api/tasks")
class TaskController03 {
    private final AtomicLong seq = new AtomicLong();
    @PostMapping
    ResponseEntity<TaskDto03> create(@RequestBody CreateTask03 req) {
        TaskDto03 dto = new TaskDto03(seq.incrementAndGet(), req.title());
        return ResponseEntity.created(URI.create("/api/tasks/" + dto.id())).body(dto);
    }
}

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(
// TODO:     classes = Task03.class,
// TODO:     webEnvironment = org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT)
class HttpPostTest03 {

    // TODO: @Autowired TestRestTemplate restTemplate;

    @Test
    void post_creates() {
        // TODO: ResponseEntity<TaskDto03> resp = restTemplate.postForEntity(
        // TODO:         "/api/tasks", new CreateTask03("Кофе"), TaskDto03.class);
        // TODO: assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        // TODO: assertThat(resp.getBody().title()).isEqualTo("Кофе");
    }
}
