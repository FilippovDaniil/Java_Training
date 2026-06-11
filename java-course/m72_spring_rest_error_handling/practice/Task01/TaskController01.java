package m72_spring_rest_error_handling.practice.task01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/tasks")
class TaskController01 {

    @GetMapping("/{id}")
    public String get(@PathVariable Long id) {
        // TODO: если id <= 0 — throw new ResponseStatusException(HttpStatus.NOT_FOUND, ...)
        // TODO: иначе вернуть "Задача " + id
        return null;
    }
}
