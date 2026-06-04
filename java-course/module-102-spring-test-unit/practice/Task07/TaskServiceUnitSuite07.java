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

// --- ТЕСТ (каркас) ---
// TODO: @ExtendWith(MockitoExtension.class)
class TaskServiceUnitSuite07 {

    // TODO: @Mock TaskRepository07 repo;
    // TODO: @Mock NotificationGateway07 gateway;
    // TODO: @InjectMocks TaskService07 service;
    // TODO: @Captor ArgumentCaptor<Task07> captor;

    @Test
    void create_persists_NEW_and_notifies() {
        // TODO: when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));
        // TODO: service.create("Кофе");
        // TODO: verify(repo).save(captor.capture());
        // TODO: assertThat(captor.getValue().status()).isEqualTo("NEW");
        // TODO: verify(gateway).created("Кофе");
    }

    @Test
    void create_blank_rejected_no_side_effects() {
        // TODO: assertThatThrownBy(() -> service.create(" ")).isInstanceOf(IllegalArgumentException.class);
        // TODO: verifyNoInteractions(repo, gateway);
    }

    @Test
    void complete_sets_DONE() {
        // TODO: when(repo.findById(1L)).thenReturn(Optional.of(new Task07(1L, "Кофе", "NEW")));
        // TODO: when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));
        // TODO: assertThat(service.complete(1L).status()).isEqualTo("DONE");
        // TODO: verify(gateway).completed(1L);
    }

    @Test
    void complete_missing_throws() {
        // TODO: when(repo.findById(99L)).thenReturn(Optional.empty());
        // TODO: assertThatThrownBy(() -> service.complete(99L)).isInstanceOf(NotFound07.class);
        // TODO: verify(gateway, never()).completed(anyLong());
    }
}
