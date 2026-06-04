import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/tasks")
class FilterController04 {

    private static final List<TaskDto04> ALL = List.of(
            new TaskDto04(1L, "A", "DONE", "ivan", 5),
            new TaskDto04(2L, "B", "NEW", "ivan", 2),
            new TaskDto04(3L, "C", "DONE", "anna", 4));

    // TODO: @GetMapping
    public List<TaskDto04> filter(
            /* @RequestParam(required = false) */ String status,
            /* @RequestParam(required = false) */ String assignee,
            /* @RequestParam(required = false) */ Integer priorityMin) {
        // TODO: накопите условия на Stream и верните результат
        return null;
    }
}
