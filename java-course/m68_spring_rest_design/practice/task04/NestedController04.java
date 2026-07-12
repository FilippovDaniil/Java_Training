package m68_spring_rest_design.practice.task04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
class NestedController04 {

    // TODO: @GetMapping("/api/projects/{projectId}/tasks")
    @GetMapping("/api/projects/{projectId}/tasks")
    public String projectTasks(@PathVariable("projectId") Long projectId) {
        return "Получить задачи проекта: " + projectId;
    }

    // TODO: @PostMapping("/api/projects/{projectId}/tasks")
    @PostMapping("/api/projects/{projectId}/tasks")
    public String createInProject(@PathVariable("projectId") Long projectId) {
        return "Создать задачу проекта: " + projectId;
    }

    // TODO: @GetMapping("/api/tasks/{taskId}/comments")
    @GetMapping("/api/tasks/{taskId}/comments")
    public String taskComments(@PathVariable("taskId") Long taskId) {
        return "Получить комментарии по задаче: " + taskId;
    }
}
