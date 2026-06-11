package m102_spring_test_unit.practice.task04;

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

// --- ТЕСТ (каркас) ---
@ExtendWith(MockitoExtension.class)
class VerifyTest04 {

    @Mock TaskRepository04 repo;
    @Mock NotificationGateway04 gateway;
    @InjectMocks TaskService04 service;

    @Test
    void complete_notifies() {
        // TODO: when(repo.findById(1L)).thenReturn(Optional.of(new Task04(1L, "Кофе")));
        // TODO: service.complete(1L);
        // TODO: verify(repo).save(any());
        // TODO: verify(gateway).notify(1L);
    }

    @Test
    void create_blank_no_repo() {
        // TODO: assertThatThrownBy(() -> service.create("  ")).isInstanceOf(IllegalArgumentException.class);
        // TODO: verifyNoInteractions(repo);
        // TODO: verify(gateway, never()).notify(anyLong());
    }
}
