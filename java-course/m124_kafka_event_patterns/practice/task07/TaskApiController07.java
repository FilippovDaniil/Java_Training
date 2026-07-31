package m124_kafka_event_patterns.practice.task07;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * API капстоуна: команды идут в write-сторону, запросы — в проекцию.
 *
 * TODO 1: POST   /api/tasks              — создать задачу (201 + id).
 * TODO 2: PATCH  /api/tasks/{id}         — сменить статус (200/204).
 * TODO 3: GET    /api/tasks?status=DONE  — из ПРОЕКЦИИ, не из tasks7.
 * TODO 4: GET    /api/tasks/stats        — количество по статусам из проекции.
 * TODO 5: GET    /api/ops                — {outboxUnsent, viewRows, lastRelayRun}:
 *         эксплуатационная сводка «сходится ли система».
 *
 * ПОЧЕМУ ЧТЕНИЕ ИЗ ПРОЕКЦИИ, А НЕ ИЗ tasks7: смысл CQRS — разные модели под запись и чтение.
 *   Если GET начнёт читать из write-модели «потому что там свежее», проекция станет мёртвым
 *   кодом, а разделение — фикцией. Задержку проекции показывайте явно (updatedAt).
 */
@RestController
@RequestMapping("/api")
public class TaskApiController07 {

    private final TaskCommandService07 commands;
    private final TaskViewRepository07 views;
    private final OutboxRepository07 outbox;
    private final OutboxRelay07 relay;

    public TaskApiController07(TaskCommandService07 commands, TaskViewRepository07 views,
                               OutboxRepository07 outbox, OutboxRelay07 relay) {
        this.commands = commands;
        this.views = views;
        this.outbox = outbox;
        this.relay = relay;
    }

    @PostMapping("/tasks")
    ResponseEntity<?> create(@RequestBody Map<String, Object> body) {
        // TODO 1
        return ResponseEntity.status(501).build();
    }

    @PatchMapping("/tasks/{id}")
    ResponseEntity<?> changeStatus(@PathVariable long id, @RequestBody Map<String, String> body) {
        // TODO 2
        return ResponseEntity.status(501).build();
    }

    @GetMapping("/tasks")
    ResponseEntity<?> byStatus(@RequestParam String status) {
        // TODO 3
        return ResponseEntity.status(501).build();
    }

    @GetMapping("/ops")
    ResponseEntity<?> ops() {
        // TODO 5
        return ResponseEntity.status(501).build();
    }
}
