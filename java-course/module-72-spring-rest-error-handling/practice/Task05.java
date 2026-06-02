/**
 * Задача 05 — Модуль 72: ProblemDetail для ошибок валидации + кастомные поля
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-validation:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Приведите ошибки валидации к формату ProblemDetail с дополнительными полями.
 *   1) NewTaskDto05 размечен (@NotBlank, @Size). create: @Valid @RequestBody.
 *   2) В GlobalHandler05 обработчик MethodArgumentNotValidException возвращает ProblemDetail:
 *        - forStatus(HttpStatus.BAD_REQUEST)
 *        - setTitle("Ошибка валидации")
 *        - setProperty("timestamp", Instant.now())
 *        - setProperty("errors", Map<поле, сообщение>) — собрать из getFieldErrors()
 *
 * ОЖИДАЕМЫЙ JSON (примерно):
 *   { "title":"Ошибка валидации", "status":400,
 *     "timestamp":"...", "errors": { "title":"не должно быть пустым" } }
 *
 * ЦЕЛЬ: единый формат и для бизнес-ошибок, и для ошибок валидации.
 *
 * ПОДСКАЗКА: ex.getBindingResult().getFieldErrors() → field + defaultMessage.
 */
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}

record NewTaskDto05(@NotBlank @Size(min = 3, max = 100) String title) {}

@RestController
@RequestMapping("/api/tasks")
class TaskController05 {
    @PostMapping
    public String create(@Valid @RequestBody NewTaskDto05 dto) {
        return "OK: " + dto.title();
    }
}

// TODO: @RestControllerAdvice
class GlobalHandler05 {

    // TODO: @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
        // TODO: ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        // TODO: pd.setTitle("Ошибка валидации");
        // TODO: pd.setProperty("timestamp", Instant.now());
        Map<String, String> errors = new HashMap<>();
        // TODO: заполнить errors из ex.getBindingResult().getFieldErrors()
        // TODO: pd.setProperty("errors", errors);
        // TODO: return pd;
        return null;
    }
}
