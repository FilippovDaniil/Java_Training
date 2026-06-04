import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class TaskService07 {
    private final TaskRepository07 repo;
    private final NotificationGateway07 gateway;
    TaskService07(TaskRepository07 repo, NotificationGateway07 gateway) {
        this.repo = repo; this.gateway = gateway;
    }
    Task07 create(String title) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("title пустой");
        Task07 saved = repo.save(new Task07(null, title, "NEW"));
        gateway.created(title);
        return saved;
    }
    Task07 complete(Long id) {
        Task07 t = repo.findById(id).orElseThrow(() -> new NotFound07(id));
        Task07 saved = repo.save(new Task07(t.id(), t.title(), "DONE"));
        gateway.completed(id);
        return saved;
    }
}
