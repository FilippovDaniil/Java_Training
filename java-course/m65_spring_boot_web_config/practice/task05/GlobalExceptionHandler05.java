package m65_spring_boot_web_config.practice.task05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

// TODO: @RestControllerAdvice
@RestControllerAdvice
class GlobalExceptionHandler05 {

    // TODO: @ExceptionHandler(MethodArgumentNotValidException.class)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fields = new HashMap<>();
        // TODO: пройдитесь по ex.getBindingResult().getFieldErrors()
        //       и для каждого положите fields.put(e.getField(), e.getDefaultMessage())
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> fields.put(e.getField(), e.getDefaultMessage()));

        Map<String, Object> body = Map.of(
                "status", 400,
                "error", "Validation Failed",
                "fields", fields
        );
        // TODO: верните ResponseEntity.badRequest().body(...)
        return ResponseEntity.badRequest().body(body);
    }

    // TODO: @ExceptionHandler(NoSuchElementException.class)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NoSuchElementException ex) {
        // TODO: верните 404 с телом { "status": 404, "error": ex.getMessage() }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("status", 404, "error", ex.getMessage()));
    }
}
