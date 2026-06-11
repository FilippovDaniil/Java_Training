package m72_spring_rest_error_handling.practice.task03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

// TODO: @RestControllerAdvice
class GlobalHandler03 {

    // TODO: @ExceptionHandler(TaskNotFoundException03.class)
    public ResponseEntity<Map<String, Object>> handle(TaskNotFoundException03 ex) {
        // TODO: 404 + Map.of("status", 404, "error", ex.getMessage())
        return null;
    }
}
