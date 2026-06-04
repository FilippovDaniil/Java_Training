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

    // TODO: @GetMapping — фильтр по статусу + page/size
    public List<Task07Model> list(
            /* @RequestParam(required = false) */ Status07 status,
            /* @RequestParam(defaultValue = "0") */ int page,
            /* @RequestParam(defaultValue = "20") */ int size) {
        // TODO: если status != null — отфильтровать, иначе вернуть все
        return null;
    }

    // TODO: @GetMapping("/{id}") → 200 либо 404
    public ResponseEntity<Task07Model> getOne(@PathVariable Long id) {
        return null;
    }

    // TODO: @PostMapping + @ResponseStatus(HttpStatus.CREATED)
    public Task07Model create(
            /* @RequestHeader(value="Authorization", required=false) */ String auth,
            /* @RequestBody */ CreateDto07 dto) {
        // TODO: id = seq.getAndIncrement(); new Task07Model(id, dto.title(), Status07.NEW); store.put(...)
        return null;
    }

    // TODO: @PatchMapping("/{id}") → 200 либо 404
    public ResponseEntity<Task07Model> patchStatus(@PathVariable Long id, /* @RequestBody */ StatusDto07 dto) {
        return null;
    }

    // TODO: @DeleteMapping("/{id}") + @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        // TODO: store.remove(id)
    }
}
