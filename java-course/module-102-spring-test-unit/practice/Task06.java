/**
 * Задача 06 — Модуль 102: краевые случаи и исключения
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-test (JUnit 5 + Mockito + AssertJ).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   TaskService06.assign(id, assignee) (готов) бросает разные исключения в краевых случаях.
 *   Покройте тестами не только «счастливый путь»:
 *     1) assign_ok(): when(repo.findById(1L)).thenReturn(Optional.of(new Task06(1L,"Кофе",null)));
 *          when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));
 *          assertThat(service.assign(1L, "alice").assignee()).isEqualTo("alice").
 *     2) assign_missing_404(): when(repo.findById(99L)).thenReturn(Optional.empty());
 *          assertThatThrownBy(() -> service.assign(99L, "alice")).isInstanceOf(NotFound06.class).
 *     3) assign_blank_assignee_400():
 *          assertThatThrownBy(() -> service.assign(1L, "  ")).isInstanceOf(IllegalArgumentException.class);
 *          // и до репозитория не дошло: verifyNoInteractions(repo).
 *
 * ЦЕЛЬ: тестировать валидацию, пустые результаты и исключения — не только happy-path.
 *
 * ВАЖНО: в assign_blank проверка идёт ДО обращения к repo → verifyNoInteractions(repo).
 *
 * ПОДСКАЗКА: разные краевые случаи → разные типы исключений (404 vs 400).
 */
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

// --- классы-под-тестом (готовы) ---
record Task06(Long id, String title, String assignee) {}

class NotFound06 extends RuntimeException {
    NotFound06(Long id) { super("not found: " + id); }
}

interface TaskRepository06 {
    Optional<Task06> findById(Long id);
    Task06 save(Task06 t);
}

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
