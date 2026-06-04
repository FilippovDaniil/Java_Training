/**
 * Задача 03 — Модуль 102: стабы when/thenReturn, thenThrow, thenAnswer
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-test (JUnit 5 + Mockito + AssertJ).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Освойте три вида стабов поведения мока (TaskService03/Repository03 готовы):
 *     1) returns(): when(repo.findById(1L)).thenReturn(Optional.of(new Task03(1L,"Кофе")));
 *          assertThat(service.find(1L).title()).isEqualTo("Кофе").
 *     2) empty_then_default(): when(repo.findById(99L)).thenReturn(Optional.empty());
 *          assertThat(service.findOrDefault(99L)).isEqualTo("(нет)").
 *     3) save_throws(): when(repo.save(any())).thenThrow(new RuntimeException("db down"));
 *          assertThatThrownBy(() -> service.create("X")).hasMessageContaining("db down").
 *     4) save_echoes(): when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));
 *          assertThat(service.create("Чай").title()).isEqualTo("Чай").
 *
 * ЦЕЛЬ: научиться задавать ответ мока: значение / пусто / исключение / «вернуть аргумент».
 *
 * ВАЖНО: thenAnswer(inv -> inv.getArgument(0)) возвращает то, что передали в save.
 *
 * ПОДСКАЗКА: каждый when настраивает один сценарий поведения зависимости.
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
import static org.mockito.Mockito.when;

// --- классы-под-тестом (готовы) ---
record Task03(Long id, String title) {}

interface TaskRepository03 {
    Optional<Task03> findById(Long id);
    Task03 save(Task03 t);
}

class TaskService03 {
    private final TaskRepository03 repo;
    TaskService03(TaskRepository03 repo) { this.repo = repo; }
    Task03 find(Long id) { return repo.findById(id).orElseThrow(); }
    String findOrDefault(Long id) { return repo.findById(id).map(Task03::title).orElse("(нет)"); }
    Task03 create(String title) { return repo.save(new Task03(null, title)); }
}

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
