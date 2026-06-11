package m101_spring_test_basics.practice.task07;

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

// --- ТЕСТ (каркас) ---
// TODO: @ExtendWith(MockitoExtension.class)
class TaskServiceTest07 {

    // TODO: @Mock TaskRepository07 repo;
    // TODO: @InjectMocks TaskService07 service;

    @Test
    void find_returns_task() {
        // TODO: when(repo.findById(1L)).thenReturn(Optional.of(new Task07(1L, "Кофе")));
        // TODO: assertThat(service.find(1L).title()).isEqualTo("Кофе");
        // TODO: verify(repo).findById(1L);
    }

    @Test
    void find_missing_throws() {
        // TODO: when(repo.findById(99L)).thenReturn(Optional.empty());
        // TODO: assertThatThrownBy(() -> service.find(99L)).isInstanceOf(NotFound07.class);
    }

    @Test
    void create_saves() {
        // TODO: when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));
        // TODO: service.create("Чай");
        // TODO: verify(repo).save(any(Task07.class));
    }
}
