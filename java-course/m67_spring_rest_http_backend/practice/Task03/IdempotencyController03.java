package m67_spring_rest_http_backend.practice.task03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
class IdempotencyController03 {

    private final AtomicInteger createdCount = new AtomicInteger(0);
    private String status = "NEW";

    @PostMapping("/api/counter")
    public int addViaPost() {
        // TODO: увеличьте createdCount и верните новое значение (НЕ идемпотентно)
        return 0;
    }

    @PutMapping("/api/status")
    public String setViaPut(/* TODO: @RequestParam */ String value) {
        // TODO: this.status = value; верните status (идемпотентно)
        return null;
    }
}
