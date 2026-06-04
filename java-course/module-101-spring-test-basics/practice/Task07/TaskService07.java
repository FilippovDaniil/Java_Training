import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TaskService07 {
    private final TaskRepository07 repo;
    TaskService07(TaskRepository07 repo) { this.repo = repo; }

    Task07 find(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFound07(id));
    }

    Task07 create(String title) {
        return repo.save(new Task07(null, title));
    }
}
