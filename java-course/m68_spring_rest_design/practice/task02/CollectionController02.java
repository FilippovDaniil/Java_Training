package m68_spring_rest_design.practice.task02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
class CollectionController02 {

    // --- Коллекция /api/tasks ---

    // TODO: @GetMapping → "все задачи"
    @GetMapping
    public String all() {
        return "все задачи";
    }

    // TODO: @PostMapping → "создать задачу в коллекции"
    @PostMapping
    public String create() {
        return "создать задачу в коллекции";
    }

    // --- Элемент /api/tasks/{id} ---

    // TODO: @GetMapping("/{id}") → "задача " + id
    @GetMapping("/{id}")
    public String one(@PathVariable("id") Long id) {
        return "задача " + id;
    }

    // TODO: @PutMapping("/{id}") → "заменить задачу " + id
    @PutMapping("/{id}")
    public String replace(@PathVariable("id") Long id) {
        return "заменить задачу " + id;
    }

    // TODO: @DeleteMapping("/{id}") → "удалить задачу " + id
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        return "удалить задачу " + id;
    }
}
