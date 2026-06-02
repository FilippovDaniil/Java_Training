/**
 * Задача 07 — Модуль 68: МИНИ-ПРОЕКТ «Дизайн API Task Tracker»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЦЕЛЬ: спроектировать ЦЕЛОСТНУЮ поверхность REST-API Task Tracker, применив все
 *       правила дизайна модуля 68. Реализация — каркасы (важна именно структура URI/методов).
 *
 * ЗАДАНИЕ:
 *
 *   1) ОФОРМИТЕ КОНТРАКТ. В комментарии ниже (DESIGN) заполните таблицу
 *      "метод | URI | назначение | статус успеха" для ресурсов:
 *      tasks (коллекция/элемент), смена статуса, comments задачи, tasks проекта,
 *      фильтрация коллекции.
 *
 *   2) РЕАЛИЗУЙТЕ КАРКАСЫ контроллера TrackerApiController07 по своему контракту:
 *      - GET    /api/tasks                       — список (+ фильтры status, assignee, page, size)
 *      - POST   /api/tasks                        — создать (201)
 *      - GET    /api/tasks/{id}                   — один
 *      - PUT    /api/tasks/{id}                   — заменить целиком
 *      - PATCH  /api/tasks/{id}                   — сменить статус (тело {status})
 *      - DELETE /api/tasks/{id}                   — удалить (204)
 *      - GET    /api/tasks/{id}/comments          — комментарии задачи
 *      - GET    /api/projects/{projectId}/tasks   — задачи проекта
 *
 *   3) Каждый метод пусть возвращает строку-описание (что он делает) ИЛИ ResponseEntity
 *      с корректным статусом. Главное — правильные URI, методы и статусы.
 *
 * ПРОВЕРКА СЕБЯ (чек-лист дизайна):
 *   [ ] нет глаголов в URI
 *   [ ] существительные во множественном числе
 *   [ ] смена статуса — PATCH, не отдельный endpoint
 *   [ ] вложенность ≤ 2 уровней
 *   [ ] фильтры — query-параметры
 *   [ ] создание — 201, удаление — 204
 *
 * =====================  DESIGN (заполните)  =====================
 *  метод  | URI                              | назначение            | статус
 *  -------+----------------------------------+-----------------------+--------
 *  GET    | /api/tasks                       | список задач+фильтры  | 200
 *  POST   | /api/tasks                       | создать задачу        | 201
 *  GET    | /api/tasks/{id}                  | одна задача           | 200/404
 *  ...    | ...                              | ...                   | ...
 * ===============================================================
 *
 * ПОДСКАЗКА: используйте наработки Task01–Task06 этого модуля.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}

record TaskStatusPatch07(String status) {}

@RestController
class TrackerApiController07 {

    // TODO: @GetMapping("/api/tasks") + фильтры (status, assignee, page, size)
    public String list(/* @RequestParam(required=false) */ String status) {
        return null;
    }

    // TODO: @PostMapping("/api/tasks") → 201
    public ResponseEntity<String> create() {
        return null;
    }

    // TODO: @GetMapping("/api/tasks/{id}")
    public String getOne(@PathVariable Long id) {
        return null;
    }

    // TODO: @PutMapping("/api/tasks/{id}") — заменить целиком
    public String replace(@PathVariable Long id) {
        return null;
    }

    // TODO: @PatchMapping("/api/tasks/{id}") — сменить статус
    public String patchStatus(@PathVariable Long id, /* @RequestBody */ TaskStatusPatch07 dto) {
        return null;
    }

    // TODO: @DeleteMapping("/api/tasks/{id}") → 204
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return null;
    }

    // TODO: @GetMapping("/api/tasks/{id}/comments")
    public String comments(@PathVariable Long id) {
        return null;
    }

    // TODO: @GetMapping("/api/projects/{projectId}/tasks")
    public String projectTasks(@PathVariable Long projectId) {
        return null;
    }
}
