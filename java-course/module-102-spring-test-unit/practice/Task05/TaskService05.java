import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TaskService05 {
    private final TaskRepository05 repo;
    TaskService05(TaskRepository05 repo) { this.repo = repo; }
    Task05 create(String title) {
        return repo.save(new Task05(null, title, "NEW"));   // логика: новый статус = NEW
    }
}
