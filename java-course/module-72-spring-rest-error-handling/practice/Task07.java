/**
 * Задача 07 — Модуль 72: МИНИ-ПРОЕКТ «Единый контракт ошибок Task Tracker»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-validation:3.2.x (см. theory.md).
 *
 * ЦЕЛЬ: собрать промышленный обработчик ошибок: единый формат ProblemDetail
 *       для 404 (нет задачи), 409 (дубликат), 400 (валидация) и 500 (всё прочее).
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) Хранилище задач в памяти (Map<Long, String>). Исключения:
 *        TaskNotFoundException07(id)  → 404
 *        DuplicateTaskException07(title) → 409
 *
 *   2) Контроллер TaskController07 (@RequestMapping("/api/tasks")):
 *        - GET  /api/tasks/{id}        → задача либо throw TaskNotFoundException07
 *        - POST /api/tasks (@Valid CreateTaskDto07{ @NotBlank title })
 *              → если title уже есть в значениях — throw DuplicateTaskException07,
 *                иначе сохранить (id = seq++) и вернуть "Создано: " + title
 *        - GET  /api/tasks/boom        → throw new RuntimeException("сбой") (для 500)
 *
 *   3) GlobalErrorHandler07 (@RestControllerAdvice) — ВСЁ возвращает ProblemDetail:
 *        - TaskNotFoundException07           → 404, title "Задача не найдена"
 *        - DuplicateTaskException07          → 409, title "Дубликат задачи"
 *        - MethodArgumentNotValidException   → 400, title "Ошибка валидации",
 *                                              property "errors" (поле→сообщение)
 *        - Exception (catch-all)             → 500, title "Внутренняя ошибка",
 *                                              detail НЕ раскрывает стектрейс
 *
 *   Проверьте:
 *     GET  /api/tasks/999            → 404 ProblemDetail
 *     POST /api/tasks {"title":""}   → 400 ProblemDetail c errors
 *     POST дважды один title         → 409 ProblemDetail
 *     GET  /api/tasks/boom           → 500 ProblemDetail (без деталей)
 *
 * АРХИТЕКТУРА:
 *
 *   TaskController07 ──бросает──► (NotFound/Duplicate/Validation/RuntimeException)
 *                                        │
 *                          GlobalErrorHandler07 → ProblemDetail (единый формат)
 *
 * ПОДСКАЗКА: переиспользуйте Task04 (ProblemDetail), Task05 (валидация), Task06 (иерархия).
 */
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

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}

class TaskNotFoundException07 extends RuntimeException {
    private final Long id;
    TaskNotFoundException07(Long id) { super("Задача " + id + " не найдена"); this.id = id; }
    public Long getId() { return id; }
}
class DuplicateTaskException07 extends RuntimeException {
    DuplicateTaskException07(String title) { super("Задача '" + title + "' уже существует"); }
}

record CreateTaskDto07(@NotBlank String title) {}

@RestController
@RequestMapping("/api/tasks")
class TaskController07 {

    private final Map<Long, String> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    @GetMapping("/{id}")
    public String get(@PathVariable Long id) {
        // TODO: если нет в store — throw new TaskNotFoundException07(id); иначе вернуть значение
        return null;
    }

    @PostMapping
    public String create(@Valid @RequestBody CreateTaskDto07 dto) {
        // TODO: если store.containsValue(dto.title()) — throw new DuplicateTaskException07(dto.title())
        // TODO: иначе store.put(seq.getAndIncrement(), dto.title()); вернуть "Создано: " + dto.title()
        return null;
    }

    @GetMapping("/boom")
    public String boom() {
        throw new RuntimeException("сбой");
    }
}

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
