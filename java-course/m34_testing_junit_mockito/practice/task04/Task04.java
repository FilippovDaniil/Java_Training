package m34_testing_junit_mockito.practice.task04;

/**
 * Задача 04 — Модуль 34: Мок, бросающий исключение
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: JUnit 5 + Mockito.
 *
 * ЗАДАНИЕ:
 *   Класс DataLoader использует RemoteApi.fetch(). Настройте мок так,
 *   чтобы fetch() бросал RuntimeException (имитация сбоя сети), и
 *   проверьте, что DataLoader.loadSafe() корректно обрабатывает сбой:
 *   возвращает "" (пустую строку) вместо падения.
 *   Также напишите тест «счастливого пути», где fetch() возвращает данные.
 *
 * ПОДСКАЗКА:
 *   when(api.fetch()).thenThrow(new RuntimeException("network down"));
 *   assertEquals("", loader.loadSafe());
 *   when(api.fetch()).thenReturn("данные"); — для счастливого пути.
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Task04 {

    @Mock
    private RemoteApi remoteApi;

    @InjectMocks
    private DataLoader dataLoader;

    @Test
    void testLoadSafeSuccess() {
        // Счастливый путь: fetch() возвращает данные
        String expectedData = "данные с сервера";
        when(remoteApi.fetch()).thenReturn(expectedData);

        String result = dataLoader.loadSafe();

        assertEquals(expectedData, result);
        verify(remoteApi, times(1)).fetch();
    }

    @Test
    void testLoadSafeHandlesFailure() {
        // Несчастливый путь: fetch() бросает исключение
        when(remoteApi.fetch()).thenThrow(new RuntimeException("network down"));

        String result = dataLoader.loadSafe();

        assertEquals("", result);
        verify(remoteApi, times(1)).fetch();
    }

    @Test
    void testLoadSafeDoesNotThrowException() {
        // Проверяем, что loadSafe никогда не бросает исключение
        when(remoteApi.fetch()).thenThrow(new RuntimeException("error"));

        assertDoesNotThrow(() -> dataLoader.loadSafe());

        verify(remoteApi).fetch();
    }

    @Test
    void testLoadSafeWithDifferentData() {
        // Пустая строка
        when(remoteApi.fetch()).thenReturn("");
        assertEquals("", dataLoader.loadSafe());

        // Обычные данные
        when(remoteApi.fetch()).thenReturn("some data");
        assertEquals("some data", dataLoader.loadSafe());

        // Длинные данные
        String longData = "a".repeat(1000);
        when(remoteApi.fetch()).thenReturn(longData);
        assertEquals(longData, dataLoader.loadSafe());

        verify(remoteApi, times(3)).fetch();
    }
}