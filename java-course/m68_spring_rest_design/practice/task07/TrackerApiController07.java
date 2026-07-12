package m68_spring_rest_design.practice.task07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api")
class TrackerApiController07 {

    // ========== GET /api/tasks - список задач с фильтрами ==========
    @GetMapping("/tasks")
    public ResponseEntity<Map<String, Object>> list(
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "assignee", required = false) String assignee,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {

        // TODO: здесь должна быть логика с сервисом
        return ResponseEntity.ok(Map.of(
                "message", "Список задач. Фильтры: status=" + status +
                        ", assignee=" + assignee +
                        ", page=" + page +
                        ", size=" + size,
                "tasks", new Object[0]
        ));
    }

    // ========== POST /api/tasks - создать задачу ==========
    @PostMapping("/tasks")
    public ResponseEntity<String> create(@RequestBody Map<String, Object> task) {
        // TODO: здесь должна быть логика с сервисом
        Long newId = 1L; // В реальности id генерируется

        return ResponseEntity
                .created(URI.create("/api/tasks/" + newId))
                .body("Задача создана с id: " + newId);
    }

    // ========== GET /api/tasks/{id} - получить задачу ==========
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Map<String, Object>> getOne(@PathVariable("id") Long id) {
        // TODO: здесь должна быть логика с сервисом
        // Для примера - задача найдена
        if (id > 0 && id < 100) {
            return ResponseEntity.ok(Map.of(
                    "id", id,
                    "title", "Пример задачи",
                    "status", "NEW",
                    "assignee", "user@example.com"
            ));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ========== PUT /api/tasks/{id} - заменить задачу целиком ==========
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Map<String, Object>> replace(
            @PathVariable("id") Long id,
            @RequestBody Map<String, Object> task) {

        // TODO: здесь должна быть логика с сервисом
        if (id > 0 && id < 100) {
            return ResponseEntity.ok(Map.of(
                    "id", id,
                    "message", "Задача заменена",
                    "updated", task
            ));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ========== PATCH /api/tasks/{id} - обновить статус ==========
    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Map<String, Object>> patchStatus(
            @PathVariable("id") Long id,
            @RequestBody TaskStatusPatch07 dto) {

        // TODO: здесь должна быть логика с сервисом
        if (id > 0 && id < 100) {
            return ResponseEntity.ok(Map.of(
                    "id", id,
                    "message", "Статус изменен на: " + dto.status(),
                    "newStatus", dto.status()
            ));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ========== DELETE /api/tasks/{id} - удалить задачу ==========
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        // TODO: здесь должна быть логика с сервисом
        if (id > 0 && id < 100) {
            return ResponseEntity.noContent().build();  // 204
        } else {
            return ResponseEntity.notFound().build();   // 404
        }
    }

    // ========== GET /api/tasks/{id}/comments - комментарии задачи ==========
    @GetMapping("/tasks/{id}/comments")
    public ResponseEntity<Map<String, Object>> comments(@PathVariable("id") Long id) {
        // TODO: здесь должна быть логика с сервисом
        if (id > 0 && id < 100) {
            return ResponseEntity.ok(Map.of(
                    "taskId", id,
                    "comments", new Object[]{
                            Map.of("id", 1, "text", "Первый комментарий"),
                            Map.of("id", 2, "text", "Второй комментарий")
                    }
            ));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ========== GET /api/projects/{projectId}/tasks - задачи проекта ==========
    @GetMapping("/projects/{projectId}/tasks")
    public ResponseEntity<Map<String, Object>> projectTasks(@PathVariable("id") Long projectId) {
        // TODO: здесь должна быть логика с сервисом
        return ResponseEntity.ok(Map.of(
                "projectId", projectId,
                "message", "Задачи проекта с id: " + projectId,
                "tasks", new Object[]{
                        Map.of("id", 1, "title", "Задача 1 проекта"),
                        Map.of("id", 2, "title", "Задача 2 проекта")
                }
        ));
    }
}
