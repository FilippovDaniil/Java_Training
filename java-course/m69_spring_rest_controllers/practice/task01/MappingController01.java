package m69_spring_rest_controllers.practice.task01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: @RestController + @RequestMapping("/api/tasks")
@RestController
@RequestMapping("/api/tasks")
class MappingController01 {

    // TODO: @GetMapping → "все задачи"
    @GetMapping
    public String all() {
        return "все задачи";
    }

    // TODO: @GetMapping("/{id}") → "задача " + id
    @GetMapping("/{id}")
    public String one(@PathVariable("id") Long id) {
        return "задача " + id;
    }

    // TODO: @GetMapping("/{id}/comments/{cid}")
    @GetMapping("/{id}/comments/{cid}")
    public String comment(@PathVariable("id") Long id, @PathVariable("cid") Long cid) {
        // TODO: верните "комментарий " + cid + " задачи " + id
        return "комментарий " + cid + " задачи " + id;
    }
}
