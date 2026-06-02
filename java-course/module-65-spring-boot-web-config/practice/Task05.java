/**
 * Задача 05 — Модуль 65: @RestControllerAdvice — единый контракт ошибок
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-validation:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) GlobalExceptionHandler05 пометьте @RestControllerAdvice.
 *   2) Метод handleValidation: @ExceptionHandler(MethodArgumentNotValidException.class).
 *      Соберите Map<имя_поля, сообщение> из ex.getBindingResult().getFieldErrors()
 *      и верните ResponseEntity.badRequest().body(...) с телом:
 *        { "status": 400, "error": "Validation Failed", "fields": { ... } }
 *   3) Метод handleNotFound: @ExceptionHandler(NoSuchElementException.class).
 *      Верните 404 с телом { "status": 404, "error": <сообщение исключения> }.
 *   4) В контроллере метод find(id): если id <= 0 — бросьте NoSuchElementException
 *      с сообщением "Товар не найден: " + id.
 *
 * ЦЕЛЬ: какие бы ошибки ни возникали, клиент получает их в ОДНОМ формате.
 *
 * ПОДСКАЗКА:
 *   @RestControllerAdvice = @ControllerAdvice + @ResponseBody (тело — JSON).
 *   Map.of("status", 404, "error", msg) — быстрый способ собрать тело ответа.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@SpringBootApplication
public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}

@RestController
@RequestMapping("/api/products")
class LookupController05 {

    @GetMapping("/{id}")
    public String find(@PathVariable Long id) {
        // TODO: если id <= 0 — бросьте new NoSuchElementException("Товар не найден: " + id)
        return "Товар " + id;
    }
}

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
