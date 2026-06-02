/**
 * Задача 02 — Модуль 72: Локальный @ExceptionHandler в контроллере
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) TaskNotFoundException02 — кастомное исключение (готово).
 *   2) В get(id): если id <= 0 — бросьте TaskNotFoundException02(id).
 *   3) Добавьте В ЭТОТ ЖЕ контроллер метод-обработчик:
 *        @ExceptionHandler(TaskNotFoundException02.class)
 *        public ResponseEntity<String> handle(TaskNotFoundException02 ex) → 404 + ex.getMessage()
 *
 *   Проверьте:
 *     GET /api/tasks/5 → 200; GET /api/tasks/0 → 404 + текст ошибки.
 *
 * ВОПРОС (ответьте комментарием):
 *   Что ограничивает локальный @ExceptionHandler по сравнению с @RestControllerAdvice?
 *
 * ПОДСКАЗКА: локальный обработчик действует только в пределах своего контроллера.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}

class TaskNotFoundException02 extends RuntimeException {
    TaskNotFoundException02(Long id) { super("Задача " + id + " не найдена"); }
}

@RestController
@RequestMapping("/api/tasks")
class TaskController02 {

    @GetMapping("/{id}")
    public String get(@PathVariable Long id) {
        // TODO: если id <= 0 — throw new TaskNotFoundException02(id)
        return "Задача " + id;
    }

    // TODO: @ExceptionHandler(TaskNotFoundException02.class)
    public ResponseEntity<String> handle(TaskNotFoundException02 ex) {
        // TODO: 404 + ex.getMessage()
        return null;
    }
}
