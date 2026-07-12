package m68_spring_rest_design.practice.task01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

// TODO: @RestController + @RequestMapping("/api/tasks")
@RestController
@RequestMapping("/api/tasks")
class TaskRestController01 {

    // TODO: @GetMapping → "список задач"   (правило: ___)
    @GetMapping
    public String list() {
        return "список задач";
    }

    // TODO: @GetMapping("/{id}") → "задача " + id   (правило: ___)
    @GetMapping("/{id}")
    public String getOne(/* @PathVariable */ @PathVariable("id") Long id) {
        return "задача " + id;
    }

    // TODO: @PostMapping → "создана"   (правило: ___)
    @PostMapping
    public String create() {
        return "создана";
    }

    // TODO: @DeleteMapping("/{id}") → "удалена " + id   (правило: ___)
    @DeleteMapping("/{id}")
    public String delete(/* @PathVariable */ @PathVariable("id") Long id) {
        return "удалена " + id;
    }
}
