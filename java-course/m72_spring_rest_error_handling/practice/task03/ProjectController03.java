package m72_spring_rest_error_handling.practice.task03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
class ProjectController03 {
    @GetMapping("/{id}")
    public String get(@PathVariable Long id) {
        if (id <= 0) throw new TaskNotFoundException03("Проект", id);
        return "Проект " + id;
    }
}
