package m72_spring_rest_error_handling.practice.task02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
class TaskController02 {

    @GetMapping("/{id}")
    public String get(@PathVariable("id") Long id) {
        // TODO: если id <= 0 — throw new TaskNotFoundException02(id)
        if (id <= 0) {
            throw new TaskNotFoundException02(id);
        }
        return "Задача " + id;
    }

    // TODO: @ExceptionHandler(TaskNotFoundException02.class)
    @ExceptionHandler(TaskNotFoundException02.class)
    public ResponseEntity<String> handle(TaskNotFoundException02 ex) {
        // TODO: 404 + ex.getMessage()
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

