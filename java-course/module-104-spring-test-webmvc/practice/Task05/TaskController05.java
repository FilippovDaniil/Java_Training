import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RestController
@RequestMapping("/api/tasks")
class TaskController05 {
    private final TaskService05 service;
    TaskController05(TaskService05 service) { this.service = service; }

    @GetMapping("/{id}")
    public TaskDto05 get(@PathVariable Long id) { return service.find(id); }
}
