package m74_spring_rest_crud_file.practice.task01;

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
class CrudController01 {

    private final Map<Long, Task01Model> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    // TODO: @PostMapping → 201 + Location + задача
    public ResponseEntity<Task01Model> create(@RequestParam String title) {
        return null;
    }

    // TODO: @GetMapping → список
    public List<Task01Model> all() {
        return null;
    }

    // TODO: @GetMapping("/{id}") → 200 либо 404
    public ResponseEntity<Task01Model> one(@PathVariable Long id) {
        return null;
    }

    // TODO: @PutMapping("/{id}") → 200 либо 404
    public ResponseEntity<Task01Model> replace(@PathVariable Long id, @RequestParam String title) {
        return null;
    }

    // TODO: @DeleteMapping("/{id}") → 204 либо 404
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return null;
    }
}
