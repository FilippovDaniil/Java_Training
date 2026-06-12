package m102_spring_test_unit.practice.task07;

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

record Task07(Long id, String title, String status) {}
