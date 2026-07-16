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
    @GetMapping("/by-tags")
    public String byTags(/* @RequestParam */ @RequestParam("tags") List<String> tags) {
        // TODO: верните "Тегов: " + tags.size() + " → " + tags
        return "Тегов: " + tags.size() + " → " + tags;
    }

    // TODO: @GetMapping("/filter")
    @GetMapping("/filter")
    public String withDefaults(
            /* @RequestParam(defaultValue = "ALL") */ @RequestParam(name = "status", defaultValue = "ALL") String status,
            /* @RequestParam(defaultValue = "10") */ @RequestParam(name = "limit", defaultValue = "10") int limit) {
        // TODO: верните "status=" + status + ", limit=" + limit
        return "status=" + status + ", limit=" + limit;
    }

    // TODO: @GetMapping("/maybe")
    @GetMapping("/maybe")
    public String optional(/* @RequestParam */ @RequestParam(name = "q", required = false) Optional<String> q) {
        // TODO: q.isPresent() ? "q задан: " + q.get() : "q не задан"
        return q.isPresent() ? "q задан: " + q.get() : "q не задан";
    }
}
