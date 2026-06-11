package m108_spring_test_full_context.practice.task03;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.concurrent.atomic.AtomicLong;
import static org.assertj.core.api.Assertions.assertThat;

@RestController
@RequestMapping("/api/tasks")
class TaskController03 {
    private final AtomicLong seq = new AtomicLong();
    @PostMapping
    ResponseEntity<TaskDto03> create(@RequestBody CreateTask03 req) {
        TaskDto03 dto = new TaskDto03(seq.incrementAndGet(), req.title());
        return ResponseEntity.created(URI.create("/api/tasks/" + dto.id())).body(dto);
    }
}
