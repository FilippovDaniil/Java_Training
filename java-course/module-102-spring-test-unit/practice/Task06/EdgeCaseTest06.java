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

// --- ТЕСТ (каркас) ---
@ExtendWith(MockitoExtension.class)
class EdgeCaseTest06 {

    @Mock TaskRepository06 repo;
    @InjectMocks TaskService06 service;

    @Test
    void assign_ok() {
        // TODO: when(repo.findById(1L)).thenReturn(Optional.of(new Task06(1L, "Кофе", null)));
        // TODO: when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));
        // TODO: assertThat(service.assign(1L, "alice").assignee()).isEqualTo("alice");
    }

    @Test
    void assign_missing_404() {
        // TODO: when(repo.findById(99L)).thenReturn(Optional.empty());
        // TODO: assertThatThrownBy(() -> service.assign(99L, "alice")).isInstanceOf(NotFound06.class);
    }

    @Test
    void assign_blank_assignee_400() {
        // TODO: assertThatThrownBy(() -> service.assign(1L, "  ")).isInstanceOf(IllegalArgumentException.class);
        // TODO: verifyNoInteractions(repo);
    }
}
