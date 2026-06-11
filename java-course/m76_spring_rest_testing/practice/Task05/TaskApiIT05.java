package m76_spring_rest_testing.practice.task05;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import static org.junit.jupiter.api.Assertions.assertEquals;

// --- ТЕСТ (каркас) ---
// TODO: @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskApiIT05 {

    // TODO: @Autowired TestRestTemplate rest;

    @Test
    void getReturnsTask() {
        // TODO: ResponseEntity<TaskDto05> r = rest.getForEntity("/api/tasks/1", TaskDto05.class);
        // TODO: assertEquals(HttpStatus.OK, r.getStatusCode());
        // TODO: assertEquals("Кофе", r.getBody().title());
    }

    @Test
    void createReturns201() {
        // TODO: ResponseEntity<TaskDto05> r = rest.postForEntity("/api/tasks", new CreateDto05("Чай"), TaskDto05.class);
        // TODO: assertEquals(HttpStatus.CREATED, r.getStatusCode());
    }
}
