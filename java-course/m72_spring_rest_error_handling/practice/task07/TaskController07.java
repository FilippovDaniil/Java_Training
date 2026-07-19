package m72_spring_rest_error_handling.practice.task07;

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

// ============ КОНТРОЛЛЕР ============
@RestController
@RequestMapping("/api/tasks")
class TaskController07 {

    private final Map<Long, String> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    @GetMapping("/{id}")
    public String get(@PathVariable("id") Long id) {
        // TODO: если нет в store — throw new TaskNotFoundException07(id); иначе вернуть значение
        String task = store.get(id);
        if (task == null) {
            throw new TaskNotFoundException07(id);
        }
        return task;
    }

    @GetMapping()
    public Collection<String> get() {
        return store.values();
    }


    @PostMapping
    public String create(@Valid @RequestBody CreateTaskDto07 dto) {
        // TODO: если store.containsValue(dto.title()) — throw new DuplicateTaskException07(dto.title())
        if (store.containsValue(dto.title())) {
            throw new DuplicateTaskException07(dto.title());
        }
        // TODO: иначе store.put(seq.getAndIncrement(), dto.title()); вернуть "Создано: " + dto.title()
        Long id = seq.getAndIncrement();
        store.put(id, dto.title());
        return "Создано: " + dto.title();
    }

    @GetMapping("/boom")
    public String boom() {
        throw new RuntimeException("сбой");
    }
}
