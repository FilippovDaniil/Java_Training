package m124_kafka_event_patterns.practice.task04;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Сторона чтения (Query) в CQRS: обращается ТОЛЬКО к проекции, не к источнику событий.
 *
 * TODO 1: GET /api/dashboard/tasks?status=DONE — список задач из проекции.
 * TODO 2: GET /api/dashboard/stats — { "NEW": 3, "DONE": 7 } из countByStatus().
 * TODO 3: добавьте в ответ метку «данные актуальны на updatedAt» — честно показывать
 *         eventual consistency вместо того, чтобы делать вид, что данные мгновенные.
 */
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController04 {

    private final TaskViewRepository04 views;

    public DashboardController04(TaskViewRepository04 views) {
        this.views = views;
    }

    @GetMapping("/tasks")
    ResponseEntity<?> byStatus(@RequestParam String status) {
        List<Map<String, Object>> result = views.findByStatus(status).stream()
                .map(v -> Map.<String, Object>of(
                        "taskId", v.getTaskId(),
                        "title", v.getTitle(),
                        "authorId", v.getAuthorId(),
                        "status", v.getStatus(),
                        "updatedAt", v.getUpdatedAt()))
                .toList();
        return ResponseEntity.ok(Map.of(
                "data", result,
                "asOf", Instant.now(),
                "note", "eventually consistent"));
    }

    @GetMapping("/stats")
    ResponseEntity<?> stats() {
        List<Map<String, Object>> stats = views.countByStatus().stream()
                .map(row -> Map.<String, Object>of("status", row[0], "count", row[1]))
                .toList();
        return ResponseEntity.ok(Map.of(
                "stats", stats,
                "asOf", Instant.now(),
                "note", "eventually consistent"));
    }
}
