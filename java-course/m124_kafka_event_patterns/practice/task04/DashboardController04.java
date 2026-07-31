package m124_kafka_event_patterns.practice.task04;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        // TODO 1
        return ResponseEntity.status(501).build();
    }

    @GetMapping("/stats")
    ResponseEntity<?> stats() {
        // TODO 2-3
        return ResponseEntity.status(501).build();
    }
}
