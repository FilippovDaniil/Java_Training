import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
class TaskController04 {
    @GetMapping("/{id}")
    public String get(@PathVariable Long id) {
        if (id <= 0) throw new TaskNotFoundException04(id);
        return "Задача " + id;
    }
}
