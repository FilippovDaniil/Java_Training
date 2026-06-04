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
// Обработчик ошибок (каркас)
// ============================================================

// TODO: @RestControllerAdvice
class ValidationHandler07 {

    // TODO: @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handle(MethodArgumentNotValidException ex) {
        Map<String, String> fields = new HashMap<>();
        // TODO: ex.getBindingResult().getFieldErrors()
        //         .forEach(e -> fields.put(e.getField(), e.getDefaultMessage()));
        // TODO: верните ResponseEntity.badRequest().body(Map.of("status", 400, "fields", fields))
        return null;
    }
}
