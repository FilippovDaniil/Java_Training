/**
 * Задача 01 — Модуль 75: Версионирование API через URI (v1 и v2)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Сделайте две версии одного ресурса, различающиеся формой ответа:
 *     1) TaskControllerV1 (@RequestMapping("/api/v1/tasks")):
 *          GET /api/v1/tasks/{id} → TaskV1(Long id, String title)
 *     2) TaskControllerV2 (@RequestMapping("/api/v2/tasks")):
 *          GET /api/v2/tasks/{id} → TaskV2(Long id, String title, String status, String assignee)
 *      (v2 добавила поля — это аддитивное, но клиентам v1 переходить не обязательно)
 *
 *   Проверьте оба URL и сравните JSON.
 *
 * ВОПРОС (ответьте комментарием):
 *   Какое изменение требует новой версии, а какое можно внести в существующую?
 *
 * ПОДСКАЗКА: URI-версионирование — самая наглядная стратегия (см. theory.md).
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}

record TaskV1(Long id, String title) {}
record TaskV2(Long id, String title, String status, String assignee) {}

// TODO: @RestController + @RequestMapping("/api/v1/tasks")
class TaskControllerV1 {
    // TODO: @GetMapping("/{id}") → new TaskV1(id, "Купить кофе")
    public TaskV1 get(@PathVariable Long id) {
        return null;
    }
}

// TODO: @RestController + @RequestMapping("/api/v2/tasks")
class TaskControllerV2 {
    // TODO: @GetMapping("/{id}") → new TaskV2(id, "Купить кофе", "NEW", "ivan")
    public TaskV2 get(@PathVariable Long id) {
        return null;
    }
}
