package m72_spring_rest_error_handling.practice.task07;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// ============ ГЛОБАЛЬНЫЙ ОБРАБОТЧИК ============
@RestControllerAdvice
class GlobalErrorHandler07 {

    // TODO: @ExceptionHandler(TaskNotFoundException07.class) → ProblemDetail 404
    @ExceptionHandler(TaskNotFoundException07.class)
    public ProblemDetail notFound(TaskNotFoundException07 ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("Ресурс не найден");
        pd.setDetail(ex.getMessage());
        pd.setProperty("timestamp", Instant.now());
        pd.setProperty("id", ex.getId());
        return pd;
    }

    // TODO: @ExceptionHandler(DuplicateTaskException07.class) → ProblemDetail 409
    @ExceptionHandler(DuplicateTaskException07.class)
    public ProblemDetail duplicate(DuplicateTaskException07 ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pd.setTitle("Конфликт ресурсов");
        pd.setDetail(ex.getMessage());
        pd.setProperty("timestamp", Instant.now());
        return pd;
    }

    // TODO: @ExceptionHandler(MethodArgumentNotValidException.class) → ProblemDetail 400 + errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail validation(MethodArgumentNotValidException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Ошибка валидации");
        pd.setDetail("Проверьте поля и повторите запрос");
        pd.setProperty("timestamp", Instant.now());

        // Собираем ошибки полей
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        pd.setProperty("errors", errors);

        return pd;
    }

    // TODO: @ExceptionHandler(Exception.class) → ProblemDetail 500 (без раскрытия деталей)
    @ExceptionHandler(Exception.class)
    public ProblemDetail any(Exception ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle("Внутренняя ошибка сервера");
        pd.setDetail("Произошла непредвиденная ошибка. Попробуйте позже.");
        pd.setProperty("timestamp", Instant.now());
        // Не раскрываем детали ex.getMessage() в production!
        return pd;
    }
}
