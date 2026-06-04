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

record Task06(Long id, String title, String assignee) {}
