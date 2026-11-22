package m124_kafka_event_patterns.practice.task01;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * REST для проверки outbox.
 *
 * TODO 1: POST /api/tasks — создать задачу (201 + {"id": ...}).
 * TODO 2: GET /api/outbox — список неотправленных записей (id, topic, aggregateId, occurredAt).
 * TODO 3: GET /api/outbox/count — сколько записей ждёт отправки (удобно для проверки релэя).
 */
@RestController
@RequestMapping("/api")
public class TaskController01 {

    private final TaskService01 service;
    private final OutboxRepository01 outbox;

    public TaskController01(TaskService01 service, OutboxRepository01 outbox) {
        this.service = service;
        this.outbox = outbox;
    }

    @PostMapping("/tasks")
    ResponseEntity<?> create(@RequestBody Map<String, Object> body) {
        String title = (String) body.get("title");
        long authorId = ((Number) body.get("authorId")).longValue();
        long id = service.createTask(title, authorId);
        return ResponseEntity.status(201).body(Map.of("id", id));
    }

    @GetMapping("/outbox")
    ResponseEntity<?> unsent() {
        List<Map<String, Object>> result = outbox.findBySentAtIsNullOrderByOccurredAtAsc().stream()
                .map(m -> Map.<String, Object>of(
                        "id", m.getId(),
                        "topic", m.getTopic(),
                        "type", m.getType(),
                        "aggregateId", m.getAggregateId(),
                        "occurredAt", m.getOccurredAt()))
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/outbox/count")
    ResponseEntity<?> count() {
        return ResponseEntity.ok(Map.of("count", outbox.findBySentAtIsNullOrderByOccurredAtAsc().size()));
    }
}
