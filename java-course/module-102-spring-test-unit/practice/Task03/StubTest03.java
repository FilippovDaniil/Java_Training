import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// --- ТЕСТ (каркас) ---
@ExtendWith(MockitoExtension.class)
class StubTest03 {

    @Mock TaskRepository03 repo;
    @InjectMocks TaskService03 service;

    @Test
    void returns() {
        // TODO: when(repo.findById(1L)).thenReturn(Optional.of(new Task03(1L, "Кофе")));
        // TODO: assertThat(service.find(1L).title()).isEqualTo("Кофе");
    }

    @Test
    void empty_then_default() {
        // TODO: when(repo.findById(99L)).thenReturn(Optional.empty());
        // TODO: assertThat(service.findOrDefault(99L)).isEqualTo("(нет)");
    }

    @Test
    void save_throws() {
        // TODO: when(repo.save(any())).thenThrow(new RuntimeException("db down"));
        // TODO: assertThatThrownBy(() -> service.create("X")).hasMessageContaining("db down");
    }

    @Test
    void save_echoes() {
        // TODO: when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));
        // TODO: assertThat(service.create("Чай").title()).isEqualTo("Чай");
    }
}
