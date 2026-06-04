import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
class NegotiationController03 {

    // TODO: @PostMapping(consumes = "application/json", produces = "application/json")
    public CreateTaskDto createJson(/* @RequestBody */ CreateTaskDto dto) {
        // TODO: верните dto
        return null;
    }

    // TODO: @GetMapping → "все задачи"
    public String listAll() {
        return null;
    }

    // TODO: @GetMapping(params = "status") → "задачи со статусом " + status
    public String listByStatus(/* @RequestParam */ String status) {
        return null;
    }
}
