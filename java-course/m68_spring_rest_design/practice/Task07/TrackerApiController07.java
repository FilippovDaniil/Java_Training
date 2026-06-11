package m68_spring_rest_design.practice.task07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
class TrackerApiController07 {

    // TODO: @GetMapping("/api/tasks") + фильтры (status, assignee, page, size)
    public String list(/* @RequestParam(required=false) */ String status) {
        return null;
    }

    // TODO: @PostMapping("/api/tasks") → 201
    public ResponseEntity<String> create() {
        return null;
    }

    // TODO: @GetMapping("/api/tasks/{id}")
    public String getOne(@PathVariable Long id) {
        return null;
    }

    // TODO: @PutMapping("/api/tasks/{id}") — заменить целиком
    public String replace(@PathVariable Long id) {
        return null;
    }

    // TODO: @PatchMapping("/api/tasks/{id}") — сменить статус
    public String patchStatus(@PathVariable Long id, /* @RequestBody */ TaskStatusPatch07 dto) {
        return null;
    }

    // TODO: @DeleteMapping("/api/tasks/{id}") → 204
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return null;
    }

    // TODO: @GetMapping("/api/tasks/{id}/comments")
    public String comments(@PathVariable Long id) {
        return null;
    }

    // TODO: @GetMapping("/api/projects/{projectId}/tasks")
    public String projectTasks(@PathVariable Long projectId) {
        return null;
    }
}
