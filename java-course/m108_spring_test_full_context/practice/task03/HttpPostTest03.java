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

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(
// TODO:     classes = Task03.class,
// TODO:     webEnvironment = org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT)
class HttpPostTest03 {

    // TODO: @Autowired TestRestTemplate restTemplate;

    @Test
    void post_creates() {
        // TODO: ResponseEntity<TaskDto03> resp = restTemplate.postForEntity(
        // TODO:         "/api/tasks", new CreateTask03("Кофе"), TaskDto03.class);
        // TODO: assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        // TODO: assertThat(resp.getBody().title()).isEqualTo("Кофе");
    }
}
