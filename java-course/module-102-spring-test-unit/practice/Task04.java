/**
 * Задача 04 — Модуль 102: проверка взаимодействий verify (times/never/noInteractions)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-test (JUnit 5 + Mockito + AssertJ).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Сервис TaskService04 при completе уведомляет шлюз, а при пустом заголовке — нет (готов ниже).
 *   Проверьте ВЗАИМОДЕЙСТВИЯ, а не только результат:
 *     1) complete_notifies():
 *          when(repo.findById(1L)).thenReturn(Optional.of(new Task04(1L,"Кофе")));
 *          service.complete(1L);
 *          verify(repo).save(any());            // сохранил ровно раз
 *          verify(gateway).notify(1L);          // уведомил ровно раз
 *     2) create_blank_no_repo():
 *          assertThatThrownBy(() -> service.create("  ")).isInstanceOf(IllegalArgumentException.class);
 *          verifyNoInteractions(repo);          // до репозитория не дошло
 *          verify(gateway, never()).notify(anyLong());
 *
 * ЦЕЛЬ: усвоить, что unit-тест проверяет КАК сервис общался с зависимостями (save? notify? never?).
 *
 * ВАЖНО: verifyNoInteractions(mock) — мок вообще не трогали; never() — конкретный метод не звали.
 *
 * ПОДСКАЗКА: verify(mock).method(arg) подразумевает ровно один вызов (times(1)).
 */
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

// --- классы-под-тестом (готовы) ---
record Task04(Long id, String title) {}

interface TaskRepository04 {
    Optional<Task04> findById(Long id);
    Task04 save(Task04 t);
}

interface NotificationGateway04 {
    void notify(Long taskId);
}

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
