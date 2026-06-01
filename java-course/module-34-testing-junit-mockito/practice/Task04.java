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
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Task04 {

    @Test
    void testLoadSafeHandlesFailure() {
        // Настройте мок на thenThrow и проверьте graceful-обработку
    }

    // TODO: тест счастливого пути (thenReturn)
}

// Классы под тестом (готовы)
interface RemoteApi { String fetch(); }

class DataLoader {
    private final RemoteApi api;
    DataLoader(RemoteApi api) { this.api = api; }
    String loadSafe() {
        try {
            return api.fetch();
        } catch (RuntimeException e) {
            return "";
        }
    }
}
