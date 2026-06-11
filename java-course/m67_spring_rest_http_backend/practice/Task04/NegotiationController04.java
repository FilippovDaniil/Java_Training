package m67_spring_rest_http_backend.practice.task04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class NegotiationController04 {

    // TODO: @GetMapping(value = "/api/task", produces = "application/json")
    public TaskInfo04 asJson() {
        // TODO: верните new TaskInfo04("Купить кофе", "NEW")
        return null;
    }

    // TODO: @GetMapping(value = "/api/task", produces = "text/plain")
    public String asText() {
        // TODO: верните "Задача: Купить кофе [NEW]"
        return null;
    }
}
