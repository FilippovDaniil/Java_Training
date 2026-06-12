package m72_spring_rest_error_handling.practice.task07;

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

public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}
