package m75_spring_rest_config_openapi.practice.task01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: @RestController + @RequestMapping("/api/v1/tasks")
class TaskControllerV1 {
    // TODO: @GetMapping("/{id}") → new TaskV1(id, "Купить кофе")
    public TaskV1 get(@PathVariable Long id) {
        return null;
    }
}
