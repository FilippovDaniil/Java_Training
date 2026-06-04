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
    public List<Task07> list() {
        return null;
    }

    // TODO: @GetMapping("/{id}") → 200 либо 404
    public ResponseEntity<Task07> get(@PathVariable Long id) {
        return null;
    }

    // TODO: @PostMapping → 201 + Location + тело
    public ResponseEntity<Task07> create(@RequestParam String title) {
        // TODO: id = seq.getAndIncrement(); new Task07(id, title, "NEW"); store.put(...)
        // TODO: ResponseEntity.created(URI.create("/api/tasks/" + id)).body(task)
        return null;
    }

    // TODO: @PatchMapping("/{id}") → 200 (обновлённая) либо 404
    public ResponseEntity<Task07> changeStatus(@PathVariable Long id, @RequestParam String status) {
        return null;
    }

    // TODO: @DeleteMapping("/{id}") → 204 либо 404
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return null;
    }

    // TODO: @GetMapping(value = "/summary", produces = "text/plain")
    public String summaryText() {
        // TODO: верните "Всего задач: " + store.size()
        return null;
    }
}
