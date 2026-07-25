package m74_spring_rest_crud_file.practice.task01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/tasks")
class CrudController01 {

    private final Map<Long, Task01Model> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    // ========== CREATE ==========
    @PostMapping
    public ResponseEntity<Task01Model> create(@RequestParam("title") String title) {
        // Генерируем новый ID
        Long id = seq.getAndIncrement();

        // Создаем задачу
        Task01Model task = new Task01Model(id, title);
        store.put(id, task);

        // Создаем Location URI
        URI location = URI.create("/api/tasks/" + id);

        // Возвращаем 201 Created с Location и телом
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(location)
                .body(task);
    }

    // ========== READ ALL ==========
    @GetMapping
    public List<Task01Model> all() {
        return new ArrayList<>(store.values());
    }

    // ========== READ ONE ==========
    @GetMapping("/{id}")
    public ResponseEntity<Task01Model> one(@PathVariable("id") Long id) {
        Task01Model task = store.get(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ========== UPDATE (FULL REPLACE) ==========
    @PutMapping("/{id}")
    public ResponseEntity<Task01Model> replace(
            @PathVariable("id") Long id,
            @RequestParam("title") String title) {

        Task01Model existing = store.get(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        // Создаем новую задачу с тем же id, но новым заголовком
        Task01Model updated = new Task01Model(id, title);
        store.put(id, updated);

        return ResponseEntity.ok(updated);
    }

    // ========== DELETE ==========
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Task01Model removed = store.remove(id);
        if (removed != null) {
            return ResponseEntity.noContent().build();  // 204
        } else {
            return ResponseEntity.notFound().build();   // 404
        }
    }
}
