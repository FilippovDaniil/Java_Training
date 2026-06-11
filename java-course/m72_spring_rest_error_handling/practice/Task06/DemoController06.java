package m72_spring_rest_error_handling.practice.task06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/demo")
class DemoController06 {
    @GetMapping
    public String demo(@RequestParam String kind) {
        // TODO: "missing" → throw new TaskNotFoundException06()
        // TODO: "dup"     → throw new DuplicateTaskException06()
        // TODO: "status"  → throw new InvalidStatusException06()
        return "ok";
    }
}
