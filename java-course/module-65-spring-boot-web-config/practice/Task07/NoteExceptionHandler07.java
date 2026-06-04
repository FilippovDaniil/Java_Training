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
class NoteExceptionHandler07 {

    // TODO: @ExceptionHandler(MethodArgumentNotValidException.class) → 400 + { status, error, fields }
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        return null;
    }

    // TODO: @ExceptionHandler(NoSuchElementException.class) → 404 + { status, error }
    public ResponseEntity<Map<String, Object>> handleNotFound(NoSuchElementException ex) {
        return null;
    }
}
