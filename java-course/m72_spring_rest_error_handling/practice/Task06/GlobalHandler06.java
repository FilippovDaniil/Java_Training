package m72_spring_rest_error_handling.practice.task06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

// TODO: @RestControllerAdvice
class GlobalHandler06 {

    // TODO: @ExceptionHandler(TaskNotFoundException06.class) → 404
    // TODO: @ExceptionHandler(DuplicateTaskException06.class) → 409
    // TODO: @ExceptionHandler(InvalidStatusException06.class) → 400
    // Подсказка: каждый возвращает ResponseEntity.status(...).body(Map.of("status",..,"error",ex.getMessage()))

    public ResponseEntity<Map<String, Object>> notFound(TaskNotFoundException06 ex) {
        return null;
    }
}
