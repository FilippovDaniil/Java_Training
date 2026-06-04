import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RestController
@RequestMapping("/api/tasks")
class TaskController02 {
    @PostMapping
    public ResponseEntity<TaskView02> create(@RequestBody CreateTaskDto02 dto) {
        TaskView02 saved = new TaskView02(1L, dto.title());
        return ResponseEntity.created(URI.create("/api/tasks/1")).body(saved);
    }
}
