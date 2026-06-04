/**
 * Задача 07 — Модуль 101: МИНИ-ПРОЕКТ «Mockito: тест сервиса с замоканным репозиторием»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-test (JUnit 5 + Mockito + AssertJ).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЦЕЛЬ: собрать инструменты модуля воедино — протестировать TaskService07 в изоляции,
 *       подменив зависимость TaskRepository07 мок-объектом (без БД и Spring-контекста).
 *
 * КЛАССЫ-ПОД-ТЕСТОМ (готовы ниже):
 *   TaskRepository07 — интерфейс (findById, save, existsById);
 *   TaskService07    — find(id) (бросает NotFound при отсутствии), create(title) (save).
 *
 * ЗАДАНИЕ — напишите тесты:
 *   1) Класс пометьте @ExtendWith(MockitoExtension.class);
 *      @Mock TaskRepository07 repo; @InjectMocks TaskService07 service.
 *   2) find_returns_task():
 *        when(repo.findById(1L)).thenReturn(Optional.of(new Task07(1L,"Кофе")));
 *        assertThat(service.find(1L).title()).isEqualTo("Кофе");
 *        verify(repo).findById(1L).
 *   3) find_missing_throws():
 *        when(repo.findById(99L)).thenReturn(Optional.empty());
 *        assertThatThrownBy(() -> service.find(99L)).isInstanceOf(NotFound07.class).
 *   4) create_saves():
 *        when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));
 *        service.create("Чай");
 *        verify(repo).save(any(Task07.class));
 *        verify(repo, never()).deleteById(any());   // (метода нет — просто пример never на findById)
 *
 * ОЖИДАЕМЫЙ ИТОГ: сервис протестирован без реальной БД; видно поведение и взаимодействия.
 *
 * ПОДСКАЗКА: @InjectMocks подставит @Mock-репозиторий в конструктор сервиса автоматически.
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

record Task07(Long id, String title) {}
