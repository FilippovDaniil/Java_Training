package m76_spring_rest_testing.practice.task05;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
