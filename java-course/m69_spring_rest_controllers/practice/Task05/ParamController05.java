package m69_spring_rest_controllers.practice.task05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
class ParamController05 {

    // TODO: @GetMapping("/by-tags")
    public String byTags(/* @RequestParam */ List<String> tags) {
        // TODO: верните "Тегов: " + tags.size() + " → " + tags
        return null;
    }

    // TODO: @GetMapping("/filter")
    public String withDefaults(
            /* @RequestParam(defaultValue = "ALL") */ String status,
            /* @RequestParam(defaultValue = "10") */ int limit) {
        // TODO: верните "status=" + status + ", limit=" + limit
        return null;
    }

    // TODO: @GetMapping("/maybe")
    public String optional(/* @RequestParam */ Optional<String> q) {
        // TODO: q.isPresent() ? "q задан: " + q.get() : "q не задан"
        return null;
    }
}
