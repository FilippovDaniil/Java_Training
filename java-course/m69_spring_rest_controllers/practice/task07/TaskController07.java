package m69_spring_rest_controllers.practice.task07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
class TaskController07 {

    private final Map<Long, Task07Model> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    // ========== GET /api/tasks - список с фильтрацией и пагинацией ==========
    @GetMapping
    public List<Task07Model> list(
            @RequestParam(required = false) Status07 status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        // Фильтруем по статусу (если указан)
        List<Task07Model> filtered = store.values().stream()
                .filter(task -> status == null || task.status() == status)
                .collect(Collectors.toList());

        // Применяем пагинацию
        int start = page * size;
        int end = Math.min(start + size, filtered.size());

        if (start >= filtered.size()) {
            return Collections.emptyList();
        }

        return filtered.subList(start, end);
    }

    // ========== GET /api/tasks/{id} - получить задачу ==========
    @GetMapping("/{id}")
    public ResponseEntity<Task07Model> getOne(@PathVariable("id") Long id) {
        Task07Model task = store.get(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ========== POST /api/tasks - создать задачу ==========
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task07Model create(
            @RequestHeader(value = "Authorization", required = false) String auth,
            @RequestBody CreateDto07 dto) {

        long id = seq.getAndIncrement();
        Task07Model task = new Task07Model(id, dto.title(), Status07.NEW);
        store.put(id, task);

        // Можно использовать auth для логирования
        System.out.println("Создана задача от: " + auth);

        return task;
    }

    // ========== PATCH /api/tasks/{id} - обновить статус ==========
    @PatchMapping("/{id}")
    public ResponseEntity<Task07Model> patchStatus(
            @PathVariable Long id,
            @RequestBody StatusDto07 dto) {

        Task07Model existing = store.get(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        // Создаем новую задачу с обновленным статусом
        Task07Model updated = new Task07Model(
                existing.id(),
                existing.title(),
                dto.status()
        );
        store.put(id, updated);

        return ResponseEntity.ok(updated);
    }

    // ========== DELETE /api/tasks/{id} - удалить задачу ==========
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        store.remove(id);
    }
}
