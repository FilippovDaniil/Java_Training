/**
 * Задача 03 — Модуль 68: Смена статуса через PATCH (а не через глагол в URI)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ДАНО (анти-паттерн): для смены статуса делают отдельные «глагольные» эндпоинты:
 *   PUT /api/tasks/42/complete
 *   PUT /api/tasks/42/reopen
 *   PUT /api/tasks/42/archive
 *
 * ЗАДАНИЕ:
 *   Замените всё это ОДНИМ эндпоинтом смены состояния:
 *     PATCH /api/tasks/{id}  с телом { "status": "DONE" }
 *   1) StatusPatchDto — record (String status). Готов.
 *   2) changeStatus(id, dto): @PatchMapping("/{id}"), тело через @RequestBody.
 *      Верните "Статус задачи <id> → <status>".
 *
 * ВОПРОС (ответьте комментарием):
 *   Чем PATCH со статусом лучше, чем три отдельных эндпоинта complete/reopen/archive?
 *
 * ПОДСКАЗКА: смена состояния = частичное обновление ресурса → PATCH (см. theory.md).
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}

record StatusPatchDto(String status) {}

@RestController
@RequestMapping("/api/tasks")
class StatusController03 {

    // TODO: @PatchMapping("/{id}")
    public String changeStatus(@PathVariable Long id, /* @RequestBody */ StatusPatchDto dto) {
        // TODO: верните "Статус задачи " + id + " → " + dto.status()
        return null;
    }
}
