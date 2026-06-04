import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/api/tasks")
class TaskController05 {

    // TODO: @GetMapping("/{id}") → 200 + "Задача <id>"
    public ResponseEntity<String> getOne(@PathVariable Long id) {
        return null;
    }

    // TODO: @PostMapping → 201 + Location + "Создана задача"
    public ResponseEntity<String> create() {
        return null;
    }

    // TODO: @PutMapping("/{id}") → 200 + "Задача <id> заменена"
    public ResponseEntity<String> replace(@PathVariable Long id) {
        return null;
    }

    // TODO: @PatchMapping("/{id}") → 200 + "Статус задачи <id> изменён"
    public ResponseEntity<String> patch(@PathVariable Long id) {
        return null;
    }

    // TODO: @DeleteMapping("/{id}") → 204
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return null;
    }
}
