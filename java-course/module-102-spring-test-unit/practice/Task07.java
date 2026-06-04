/**
 * Задача 07 — Модуль 102: МИНИ-ПРОЕКТ «Полный unit-тест TaskService»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-test (JUnit 5 + Mockito + AssertJ).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЦЕЛЬ: собрать всё из модуля — протестировать TaskService07 целиком, в изоляции:
 *       стабы, verify, ArgumentCaptor, краевые случаи. Без БД и Spring-контекста.
 *
 * КЛАСС-ПОД-ТЕСТОМ TaskService07 (готов ниже):
 *   - create(title): валидирует, сохраняет новую задачу со статусом NEW, уведомляет шлюз;
 *   - complete(id): находит, ставит DONE, сохраняет, уведомляет; NotFound07 если нет.
 *
 * ЗАДАНИЕ — напишите тест-класс @ExtendWith(MockitoExtension.class)
 *           с @Mock repo, @Mock gateway, @InjectMocks service, @Captor ArgumentCaptor<Task07>:
 *
 *   1) create_persists_NEW_and_notifies():
 *        when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));
 *        service.create("Кофе");
 *        verify(repo).save(captor.capture());
 *        assertThat(captor.getValue().status()).isEqualTo("NEW");
 *        verify(gateway).created("Кофе");
 *
 *   2) create_blank_rejected_no_side_effects():
 *        assertThatThrownBy(() -> service.create(" ")).isInstanceOf(IllegalArgumentException.class);
 *        verifyNoInteractions(repo, gateway);
 *
 *   3) complete_sets_DONE():
 *        when(repo.findById(1L)).thenReturn(Optional.of(new Task07(1L,"Кофе","NEW")));
 *        when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));
 *        assertThat(service.complete(1L).status()).isEqualTo("DONE");
 *        verify(gateway).completed(1L);
 *
 *   4) complete_missing_throws():
 *        when(repo.findById(99L)).thenReturn(Optional.empty());
 *        assertThatThrownBy(() -> service.complete(99L)).isInstanceOf(NotFound07.class);
 *        verify(gateway, never()).completed(anyLong());
 *
 * ОЖИДАЕМЫЙ ИТОГ: вся логика сервиса покрыта быстрыми изолированными тестами.
 *
 * ПОДСКАЗКА: соедините приёмы задач 03 (стабы), 04 (verify), 05 (captor), 06 (краевые случаи).
 */
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

// --- классы-под-тестом (готовы) ---
record Task07(Long id, String title, String status) {}

class NotFound07 extends RuntimeException {
    NotFound07(Long id) { super("not found: " + id); }
}

interface TaskRepository07 {
    Optional<Task07> findById(Long id);
    Task07 save(Task07 t);
}

interface NotificationGateway07 {
    void created(String title);
    void completed(Long id);
}

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
