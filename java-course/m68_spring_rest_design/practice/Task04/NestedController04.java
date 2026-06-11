package m68_spring_rest_design.practice.task04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
class NestedController04 {

    // TODO: @GetMapping("/api/projects/{projectId}/tasks")
    public String projectTasks(@PathVariable Long projectId) {
        return null;
    }

    // TODO: @PostMapping("/api/projects/{projectId}/tasks")
    public String createInProject(@PathVariable Long projectId) {
        return null;
    }

    // TODO: @GetMapping("/api/tasks/{taskId}/comments")
    public String taskComments(@PathVariable Long taskId) {
        return null;
    }
}
