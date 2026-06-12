package m68_spring_rest_design.practice.task01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

// TODO: @RestController + @RequestMapping("/api/tasks")
class TaskRestController01 {

    // TODO: @GetMapping → "список задач"   (правило: ___)
    public String list() {
        return null;
    }

    // TODO: @GetMapping("/{id}") → "задача " + id   (правило: ___)
    public String getOne(/* @PathVariable */ Long id) {
        return null;
    }

    // TODO: @PostMapping → "создана"   (правило: ___)
    public String create() {
        return null;
    }

    // TODO: @DeleteMapping("/{id}") → "удалена " + id   (правило: ___)
    public String delete(/* @PathVariable */ Long id) {
        return null;
    }
}
