/**
 * Задача 03 — Модуль 72: Глобальный @RestControllerAdvice
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Вынесите обработку ошибок из контроллера в ГЛОБАЛЬНЫЙ обработчик.
 *   1) В двух контроллерах (TaskController03, ProjectController03) методы бросают
 *      TaskNotFoundException03 при id <= 0.
 *   2) GlobalHandler03 пометьте @RestControllerAdvice и добавьте:
 *        @ExceptionHandler(TaskNotFoundException03.class)
 *        → ResponseEntity 404 с телом Map.of("status",404,"error",ex.getMessage())
 *   3) Убедитесь, что ОБА контроллера используют один обработчик (никакого
 *      @ExceptionHandler в самих контроллерах быть не должно).
 *
 *   Проверьте: GET /api/tasks/0 и GET /api/projects/0 → одинаковый формат 404.
 *
 * ЦЕЛЬ: один контракт ошибок для всего приложения.
 *
 * ПОДСКАЗКА: @RestControllerAdvice = @ControllerAdvice + @ResponseBody.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@SpringBootApplication
public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}

class TaskNotFoundException03 extends RuntimeException {
    TaskNotFoundException03(String what, Long id) { super(what + " " + id + " не найден(а)"); }
}

@RestController
@RequestMapping("/api/tasks")
class TaskController03 {
    @GetMapping("/{id}")
    public String get(@PathVariable Long id) {
        if (id <= 0) throw new TaskNotFoundException03("Задача", id);
        return "Задача " + id;
    }
}

@RestController
@RequestMapping("/api/projects")
class ProjectController03 {
    @GetMapping("/{id}")
    public String get(@PathVariable Long id) {
        if (id <= 0) throw new TaskNotFoundException03("Проект", id);
        return "Проект " + id;
    }
}

// TODO: @RestControllerAdvice
class GlobalHandler03 {

    // TODO: @ExceptionHandler(TaskNotFoundException03.class)
    public ResponseEntity<Map<String, Object>> handle(TaskNotFoundException03 ex) {
        // TODO: 404 + Map.of("status", 404, "error", ex.getMessage())
        return null;
    }
}
