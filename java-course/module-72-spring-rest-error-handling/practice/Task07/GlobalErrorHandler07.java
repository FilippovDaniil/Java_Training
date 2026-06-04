import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// TODO: @RestControllerAdvice
class GlobalErrorHandler07 {

    // TODO: @ExceptionHandler(TaskNotFoundException07.class) → ProblemDetail 404
    public ProblemDetail notFound(TaskNotFoundException07 ex) {
        return null;
    }

    // TODO: @ExceptionHandler(DuplicateTaskException07.class) → ProblemDetail 409
    public ProblemDetail duplicate(DuplicateTaskException07 ex) {
        return null;
    }

    // TODO: @ExceptionHandler(MethodArgumentNotValidException.class) → ProblemDetail 400 + errors
    public ProblemDetail validation(MethodArgumentNotValidException ex) {
        return null;
    }

    // TODO: @ExceptionHandler(Exception.class) → ProblemDetail 500 (без раскрытия деталей)
    public ProblemDetail any(Exception ex) {
        return null;
    }
}
