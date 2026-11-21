package m120_kafka_producer_spring.practice.task07;

import java.net.URI;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-вход в сервис: HTTP-запрос -> изменение состояния -> событие в Kafka.
 *
 * TODO 1: POST /api/tasks — тело {"title": "...", "authorId": 10}:
 *           service.create(...), ответ 201 Created с телом {"id": ...} и заголовком Location.
 * TODO 2: PATCH /api/tasks/{id} — тело {"status":"DONE"}:
 *           service.changeStatus(...), ответ 200 OK (или 204 No Content).
 *
 * СОГЛАШЕНИЯ REST (как в модулях 68–72): создание — POST на коллекцию, смена статуса — PATCH
 *   с телом-статусом, а не POST /api/tasks/{id}/done.
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController07 {

    private final TaskService07 service;

    public TaskController07(TaskService07 service) {
        this.service = service;
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody Map<String, Object> body) {
        String title = (String) body.get("title");
        long authorId = ((Number) body.get("authorId")).longValue();

        long id = service.create(title, authorId);

        return ResponseEntity
                .created(URI.create("/api/tasks/" + id))
                .body(Map.of("id", id));
    }

    @PatchMapping("/{id}")
    ResponseEntity<?> changeStatus(@PathVariable long id,
                                   @RequestBody Map<String, String> body) {
        String status = body.get("status");
        service.changeStatus(id, status);
        return ResponseEntity.ok().build();
    }
}
