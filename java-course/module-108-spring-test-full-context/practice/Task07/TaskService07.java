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

@Service
class TaskService07 {
    private final TaskRepository07 repo;
    TaskService07(TaskRepository07 repo) { this.repo = repo; }
    TaskDto07 create(String title) {
        TaskEntity07 saved = repo.save(new TaskEntity07(title));
        return new TaskDto07(saved.getId(), saved.getTitle());
    }
    List<TaskDto07> all() {
        return repo.findAll().stream().map(t -> new TaskDto07(t.getId(), t.getTitle())).toList();
    }
}
