package m102_spring_test_unit.practice.task03;

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

record Task03(Long id, String title) {}
