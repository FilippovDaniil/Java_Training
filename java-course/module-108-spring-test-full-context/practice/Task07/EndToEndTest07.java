import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(
// TODO:     classes = Task07.class,
// TODO:     webEnvironment = org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT)
class EndToEndTest07 {

    // TODO: @Autowired TestRestTemplate rest;

    @Test
    void create_then_list_reflects_it() {
        // TODO: ResponseEntity<TaskDto07> created = rest.postForEntity(
        // TODO:         "/api/tasks", new CreateTask07("Кофе"), TaskDto07.class);
        // TODO: assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        // TODO: Long id = created.getBody().id();
        // TODO: ResponseEntity<TaskDto07[]> list = rest.getForEntity("/api/tasks", TaskDto07[].class);
        // TODO: assertThat(list.getStatusCode()).isEqualTo(HttpStatus.OK);
        // TODO: assertThat(list.getBody()).extracting(TaskDto07::id).contains(id);
        // TODO: assertThat(list.getBody()).extracting(TaskDto07::title).contains("Кофе");
    }
}
