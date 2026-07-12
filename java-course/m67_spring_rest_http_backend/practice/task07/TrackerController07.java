package m67_spring_rest_http_backend.practice.task07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/tasks")
class TrackerController07 {

    private final Map<Long, Task07> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    // TODO: @GetMapping → 200 + список (new ArrayList<>(store.values()))
    @GetMapping
    public List<Task07> list() {
        return new ArrayList<>(store.values());
    }

    // TODO: @GetMapping("/{id}") → 200 либо 404
    @GetMapping("/{id}")
    public ResponseEntity<Task07> get(@PathVariable Long id) {
        return Optional.ofNullable(store.get(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // TODO: @PostMapping → 201 + Location + тело
    @PostMapping
    public ResponseEntity<Task07> create(@RequestParam("title") String title) {
        long id = seq.getAndIncrement();
        Task07 task = new Task07(id, title, "NEW");
        store.put(id, task);

        return ResponseEntity
                .created(URI.create("/api/tasks/" + id))
                .body(task);
    }

    // TODO: @PatchMapping("/{id}") → 200 (обновлённая) либо 404
    @PatchMapping("/{id}")
    public ResponseEntity<Task07> changeStatus(
            @PathVariable("id") Long id,
            @RequestParam("status") String status) {

        return Optional.ofNullable(store.get(id))
                .map(task -> {
                    // Создаем новую задачу с обновленным статусом
                    Task07 updated = new Task07(task.id(), task.title(), status);
                    store.put(id, updated);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // TODO: @DeleteMapping("/{id}") → 204 либо 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (store.containsKey(id)) {
            store.remove(id);
            return ResponseEntity.noContent().build();  // 204
        } else {
            return ResponseEntity.notFound().build();   // 404
        }
    }

    // TODO: @GetMapping(value = "/summary", produces = "text/plain")
    @GetMapping(value = "/summary", produces = "text/plain")
    public String summaryText() {
        return "Всего задач: " + store.size();
    }
}
