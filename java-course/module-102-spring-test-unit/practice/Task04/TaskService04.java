import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class TaskService04 {
    private final TaskRepository04 repo;
    private final NotificationGateway04 gateway;
    TaskService04(TaskRepository04 repo, NotificationGateway04 gateway) {
        this.repo = repo; this.gateway = gateway;
    }
    void complete(Long id) {
        Task04 t = repo.findById(id).orElseThrow();
        repo.save(t);
        gateway.notify(id);
    }
    Task04 create(String title) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("пустой");
        return repo.save(new Task04(null, title));
    }
}
