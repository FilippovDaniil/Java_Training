/**
 * Задача 06 — Модуль 72: Иерархия исключений → разные статусы
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Заведите иерархию бизнес-исключений и сопоставьте каждому HTTP-статус.
 *   1) Базовое BusinessException06 и наследники:
 *        TaskNotFoundException06   → 404
 *        DuplicateTaskException06  → 409
 *        InvalidStatusException06  → 400
 *   2) В контроллере demo(kind): по значению kind бросьте соответствующее исключение
 *      ("missing" → NotFound, "dup" → Duplicate, "status" → Invalid).
 *   3) В GlobalHandler06 (@RestControllerAdvice) объявите ТРИ обработчика —
 *      по одному на каждый подтип — каждый со своим статусом и телом
 *      Map.of("status", <код>, "error", ex.getMessage()).
 *
 *   Проверьте:
 *      GET /api/demo?kind=missing → 404
 *      GET /api/demo?kind=dup     → 409
 *      GET /api/demo?kind=status  → 400
 *
 * ЦЕЛЬ: показать, как одна иерархия исключений отображается в разные коды.
 *
 * ПОДСКАЗКА: Spring выбирает самый специфичный @ExceptionHandler по типу.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@SpringBootApplication
public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}

class BusinessException06 extends RuntimeException {
    BusinessException06(String msg) { super(msg); }
}
class TaskNotFoundException06 extends BusinessException06 {
    TaskNotFoundException06() { super("Задача не найдена"); }
}
class DuplicateTaskException06 extends BusinessException06 {
    DuplicateTaskException06() { super("Такая задача уже существует"); }
}
class InvalidStatusException06 extends BusinessException06 {
    InvalidStatusException06() { super("Недопустимый статус"); }
}

@RestController
@RequestMapping("/api/demo")
class DemoController06 {
    @GetMapping
    public String demo(@RequestParam String kind) {
        // TODO: "missing" → throw new TaskNotFoundException06()
        // TODO: "dup"     → throw new DuplicateTaskException06()
        // TODO: "status"  → throw new InvalidStatusException06()
        return "ok";
    }
}

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
