import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: @RestController + @RequestMapping("/api/v2/tasks")
class TaskControllerV2 {
    // TODO: @GetMapping("/{id}") → new TaskV2(id, "Купить кофе", "NEW", "ivan")
    public TaskV2 get(@PathVariable Long id) {
        return null;
    }
}
