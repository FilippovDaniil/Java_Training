package m102_spring_test_unit.practice.task02;

/**
 * Задача 02 — Модуль 102: @ExtendWith(MockitoExtension) + @Mock + @InjectMocks
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-test (JUnit 5 + Mockito + AssertJ).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Сервис TaskService02 зависит от TaskRepository02 (готовы). Настройте каркас с моками:
 *     1) Класс пометьте @ExtendWith(MockitoExtension.class).
 *     2) @Mock TaskRepository02 repo; @InjectMocks TaskService02 service.
 *     3) find_returns_title():
 *          when(repo.findById(1L)).thenReturn(Optional.of(new Task02(1L, "Кофе")));
 *          assertThat(service.title(1L)).isEqualTo("Кофе").
 *
 * ЦЕЛЬ: освоить связку расширение+@Mock+@InjectMocks — мок внедряется в сервис автоматически.
 *
 * ВАЖНО: без @ExtendWith(MockitoExtension.class) поля @Mock останутся null.
 *
 * ПОДСКАЗКА: @InjectMocks вызовет конструктор TaskService02(TaskRepository02) с моком.
 */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

record Task02(Long id, String title) {}
