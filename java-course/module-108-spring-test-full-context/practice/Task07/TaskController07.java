import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RestController
@RequestMapping("/api/tasks")
class TaskController07 {
    private final TaskService07 service;
    TaskController07(TaskService07 service) { this.service = service; }

    @PostMapping
    ResponseEntity<TaskDto07> create(@RequestBody CreateTask07 req) {
        TaskDto07 dto = service.create(req.title());
        return ResponseEntity.created(URI.create("/api/tasks/" + dto.id())).body(dto);
    }

    @GetMapping
    List<TaskDto07> all() { return service.all(); }
}
