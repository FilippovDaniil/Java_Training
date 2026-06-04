import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class TaskService06 {
    private final TaskRepository06 repo;
    TaskService06(TaskRepository06 repo) { this.repo = repo; }
    Task06 assign(Long id, String assignee) {
        if (assignee == null || assignee.isBlank())
            throw new IllegalArgumentException("assignee пустой");
        Task06 t = repo.findById(id).orElseThrow(() -> new NotFound06(id));
        return repo.save(new Task06(t.id(), t.title(), assignee));
    }
}
