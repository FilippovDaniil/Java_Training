package m72_spring_rest_error_handling.practice.task06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

// TODO: @RestControllerAdvice
@RestControllerAdvice
class GlobalHandler06 {

    // TODO: @ExceptionHandler(TaskNotFoundException06.class) → 404
    @ExceptionHandler(TaskNotFoundException06.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(TaskNotFoundException06 ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "status", HttpStatus.NOT_FOUND.value(),
                        "error", ex.getMessage()
                ));
    }

    // TODO: @ExceptionHandler(DuplicateTaskException06.class) → 409
    @ExceptionHandler(DuplicateTaskException06.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate(DuplicateTaskException06 ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "status", HttpStatus.CONFLICT.value(),
                        "error", ex.getMessage()
                ));
    }

    // TODO: @ExceptionHandler(InvalidStatusException06.class) → 400
    @ExceptionHandler(InvalidStatusException06.class)
    public ResponseEntity<Map<String, Object>> handleInvalidStatus(InvalidStatusException06 ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "status", HttpStatus.BAD_REQUEST.value(),
                        "error", ex.getMessage()
                ));
    }
}
