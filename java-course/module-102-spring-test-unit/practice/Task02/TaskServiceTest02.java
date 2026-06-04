import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

// --- ТЕСТ (каркас) ---
// TODO: @ExtendWith(MockitoExtension.class)
class TaskServiceTest02 {

    // TODO: @Mock TaskRepository02 repo;
    // TODO: @InjectMocks TaskService02 service;

    @Test
    void find_returns_title() {
        // TODO: when(repo.findById(1L)).thenReturn(Optional.of(new Task02(1L, "Кофе")));
        // TODO: assertThat(service.title(1L)).isEqualTo("Кофе");
    }
}
