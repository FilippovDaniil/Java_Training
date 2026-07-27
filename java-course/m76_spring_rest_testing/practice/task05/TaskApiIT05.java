package m76_spring_rest_testing.practice.task05;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import static org.junit.jupiter.api.Assertions.assertEquals;

// --- ТЕСТ (каркас) ---
// TODO: @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskApiIT05 {

    // TODO: @Autowired TestRestTemplate rest;
    @Resource
    TestRestTemplate rest;

    @Test
    void getReturnsTask() {
        // TODO: ResponseEntity<TaskDto05> r = rest.getForEntity("/api/tasks/1", TaskDto05.class);
        // TODO: assertEquals(HttpStatus.OK, r.getStatusCode());
        // TODO: assertEquals("Кофе", r.getBody().title());
        ResponseEntity<TaskDto05> r = rest.getForEntity("/api/tasks/1", TaskDto05.class);
        assertEquals(HttpStatus.OK, r.getStatusCode());
        assertEquals("Кофе", r.getBody().title());
    }

    @Test
    void createReturns201() {
        // TODO: ResponseEntity<TaskDto05> r = rest.postForEntity("/api/tasks", new CreateDto05("Чай"), TaskDto05.class);
        ResponseEntity<TaskDto05> r = rest.
                postForEntity("/api/tasks", new CreateDto05("Чай"), TaskDto05.class);
        // TODO: assertEquals(HttpStatus.CREATED, r.getStatusCode());
        assertEquals(HttpStatus.CREATED, r.getStatusCode());
    }
}
