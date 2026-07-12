package m67_spring_rest_http_backend.practice.task05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/api/tasks")
class TaskController05 {

    // TODO: @GetMapping("/{id}") → 200 + "Задача <id>"
    @GetMapping("/{id}")
    public ResponseEntity<String> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok("Задача: " + id);
    }

    // TODO: @PostMapping → 201 + Location + "Создана задача"
    @PostMapping
    public ResponseEntity<String> create() {
        URI location = URI.create("/api/tasks/100");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(location)
                .body("Создана задача");
    }

    // TODO: @PutMapping("/{id}") → 200 + "Задача <id> заменена"
    @PutMapping("/{id}")
    public ResponseEntity<String> replace(@PathVariable("id") Long id) {
        return ResponseEntity.ok("Задача <" + id + "> заменена");
    }

    // TODO: @PatchMapping("/{id}") → 200 + "Статус задачи <id> изменён"
    @PatchMapping("/{id}")
    public ResponseEntity<String> patch(@PathVariable("id") Long id) {
        return ResponseEntity.ok("Статус задачи <" + id + "> изменён");
    }

    // TODO: @DeleteMapping("/{id}") → 204
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        return ResponseEntity.noContent().build();
    }
}
