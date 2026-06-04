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
class GlobalExceptionHandler05 {

    // TODO: @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fields = new HashMap<>();
        // TODO: пройдитесь по ex.getBindingResult().getFieldErrors()
        //       и для каждого положите fields.put(e.getField(), e.getDefaultMessage())
        // TODO: верните ResponseEntity.badRequest().body(...)
        return null;
    }

    // TODO: @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NoSuchElementException ex) {
        // TODO: верните 404 с телом { "status": 404, "error": ex.getMessage() }
        return null;
    }
}
