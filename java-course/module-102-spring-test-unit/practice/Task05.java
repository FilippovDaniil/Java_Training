/**
 * Задача 05 — Модуль 102: ArgumentCaptor — что именно передали в мок
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-test (JUnit 5 + Mockito + AssertJ).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   TaskService05.create выставляет новой задаче статус "NEW" (готов). Проверьте, что именно
 *   ушло в repo.save, перехватив аргумент:
 *     1) Класс @ExtendWith(MockitoExtension.class); @Mock repo; @InjectMocks service;
 *        @Captor ArgumentCaptor<Task05> captor.
 *     2) saves_with_new_status():
 *          when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));
 *          service.create("Кофе");
 *          verify(repo).save(captor.capture());
 *          Task05 saved = captor.getValue();
 *          assertThat(saved.title()).isEqualTo("Кофе");
 *          assertThat(saved.status()).isEqualTo("NEW");
 *
 * ЦЕЛЬ: проверять НЕ только факт вызова, но и СОДЕРЖИМОЕ аргумента, переданного зависимости.
 *
 * ВАЖНО: captor.capture() ставится ВНУТРИ verify(...), значение читается через getValue().
 *
 * ПОДСКАЗКА: @Captor создаёт ArgumentCaptor; альтернатива — ArgumentCaptor.forClass(Task05.class).
 */
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

// --- классы-под-тестом (готовы) ---
record Task05(Long id, String title, String status) {}

interface TaskRepository05 {
    Task05 save(Task05 t);
}

class TaskService05 {
    private final TaskRepository05 repo;
    TaskService05(TaskRepository05 repo) { this.repo = repo; }
    Task05 create(String title) {
        return repo.save(new Task05(null, title, "NEW"));   // логика: новый статус = NEW
    }
}

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
