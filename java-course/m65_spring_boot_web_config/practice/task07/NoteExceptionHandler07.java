package m65_spring_boot_web_config.practice.task07;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ConcurrentHashMap;

// ============================================================
// Единый обработчик ошибок (каркас)
// ============================================================

// TODO: @RestControllerAdvice
@RestControllerAdvice
class NoteExceptionHandler07 {

    // TODO: @ExceptionHandler(MethodArgumentNotValidException.class) → 400 + { status, error, fields }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fields = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> fields.put(e.getField(), e.getDefaultMessage()));

        Map<String, Object> body = Map.of(
                "status", 400,
                "error", "Validation Failed",
                "fields", fields
        );
        return ResponseEntity.badRequest().body(body);
    }

    // TODO: @ExceptionHandler(NoSuchElementException.class) → 404 + { status, error }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("status", 404, "error", ex.getMessage()));
    }
}
