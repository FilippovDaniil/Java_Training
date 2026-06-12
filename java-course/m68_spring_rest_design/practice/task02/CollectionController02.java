package m68_spring_rest_design.practice.task02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
class CollectionController02 {

    // --- Коллекция /api/tasks ---

    // TODO: @GetMapping → "все задачи"
    public String all() {
        return null;
    }

    // TODO: @PostMapping → "создать задачу в коллекции"
    public String create() {
        return null;
    }

    // --- Элемент /api/tasks/{id} ---

    // TODO: @GetMapping("/{id}") → "задача " + id
    public String one(@PathVariable Long id) {
        return null;
    }

    // TODO: @PutMapping("/{id}") → "заменить задачу " + id
    public String replace(@PathVariable Long id) {
        return null;
    }

    // TODO: @DeleteMapping("/{id}") → "удалить задачу " + id
    public String delete(@PathVariable Long id) {
        return null;
    }
}
