package m71_spring_rest_validation.practice.task07;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.lang.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// ============================================================
// Контроллер (каркас)
// ============================================================

@RestController
@RequestMapping("/api/tasks")
class TaskController07 {

    @PostMapping
    public String create(/* TODO: @Valid */ @RequestBody CreateTaskRequest07 dto) {
        // TODO: верните "OK: " + dto.title()
        return null;
    }
}
