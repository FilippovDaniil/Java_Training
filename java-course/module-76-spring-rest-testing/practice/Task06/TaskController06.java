import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RestController
@RequestMapping("/api/tasks")
class TaskController06 {
    @GetMapping
    public List<TaskDto06> all() {
        return List.of(new TaskDto06(1L, "A"), new TaskDto06(2L, "B"), new TaskDto06(3L, "C"));
    }

    @PostMapping
    public ResponseEntity<TaskDto06> create() {
        return ResponseEntity.created(URI.create("/api/tasks/100")).body(new TaskDto06(100L, "Новая"));
    }
}
