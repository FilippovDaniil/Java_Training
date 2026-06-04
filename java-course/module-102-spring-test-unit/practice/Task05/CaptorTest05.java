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

// --- ТЕСТ (каркас) ---
@ExtendWith(MockitoExtension.class)
class CaptorTest05 {

    @Mock TaskRepository05 repo;
    @InjectMocks TaskService05 service;
    // TODO: @Captor ArgumentCaptor<Task05> captor;

    @Test
    void saves_with_new_status() {
        // TODO: when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));
        // TODO: service.create("Кофе");
        // TODO: verify(repo).save(captor.capture());
        // TODO: Task05 saved = captor.getValue();
        // TODO: assertThat(saved.title()).isEqualTo("Кофе");
        // TODO: assertThat(saved.status()).isEqualTo("NEW");
    }
}
