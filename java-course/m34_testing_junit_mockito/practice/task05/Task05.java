package m34_testing_junit_mockito.practice.task05;

/**
 * Задача 05 — Модуль 34: ArgumentCaptor
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: JUnit 5 + Mockito.
 *
 * ЗАДАНИЕ:
 *   Класс AuditService при action(user) сохраняет в repository объект
 *   LogEntry (с заполненными полями user и message). С помощью
 *   ArgumentCaptor «перехватите» объект, переданный в repository.save,
 *   и проверьте его поля.
 *
 * ПОДСКАЗКА:
 *   ArgumentCaptor<LogEntry> captor = ArgumentCaptor.forClass(LogEntry.class);
 *   verify(repository).save(captor.capture());
 *   LogEntry saved = captor.getValue();
 *   assertEquals("admin", saved.user);
 */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Task05 {

    @Mock
    private LogRepository repository;

    @InjectMocks
    private AuditService auditService;

    @Test
    void testAuditCapturesLogEntry() {
        // Arrange - подготовка
        String userName = "admin";

        // Act - действие
        auditService.action(userName);

        // Assert - проверка с помощью ArgumentCaptor
        ArgumentCaptor<LogEntry> captor = ArgumentCaptor.forClass(LogEntry.class);
        verify(repository, times(1)).save(captor.capture());

        LogEntry savedEntry = captor.getValue();

        // Проверяем поля сохраненного объекта
        assertAll(
                () -> assertEquals("admin", savedEntry.user, "User должен быть 'admin'"),
                () -> assertEquals("Выполнено действие пользователем admin", savedEntry.message,
                        "Message должен содержать правильный текст")
        );
    }

    @Test
    void testAuditWithDifferentUser() {
        // Arrange
        String userName = "guest";

        // Act
        auditService.action(userName);

        // Assert
        ArgumentCaptor<LogEntry> captor = ArgumentCaptor.forClass(LogEntry.class);
        verify(repository).save(captor.capture());

        LogEntry savedEntry = captor.getValue();

        assertAll(
                () -> assertEquals("guest", savedEntry.user),
                () -> assertEquals("Выполнено действие пользователем guest", savedEntry.message)
        );
    }

    @Test
    void testAuditCapturesMultipleLogs() {
        // Act - выполняем несколько действий
        auditService.action("user1");
        auditService.action("user2");
        auditService.action("user3");

        // Assert - перехватываем все вызовы
        ArgumentCaptor<LogEntry> captor = ArgumentCaptor.forClass(LogEntry.class);
        verify(repository, times(3)).save(captor.capture());

        // Получаем все сохраненные объекты
        var allEntries = captor.getAllValues();

        assertEquals(3, allEntries.size(), "Должно быть сохранено 3 записи");

        // Проверяем каждую запись
        assertEquals("user1", allEntries.get(0).user);
        assertEquals("user2", allEntries.get(1).user);
        assertEquals("user3", allEntries.get(2).user);

        // Проверяем сообщения
        for (int i = 0; i < allEntries.size(); i++) {
            String expectedUser = "user" + (i + 1);
            assertEquals("Выполнено действие пользователем " + expectedUser,
                    allEntries.get(i).message);
        }
    }

    @Test
    void testAuditWithEmptyUsername() {
        // Arrange
        String userName = "";

        // Act
        auditService.action(userName);

        // Assert
        ArgumentCaptor<LogEntry> captor = ArgumentCaptor.forClass(LogEntry.class);
        verify(repository).save(captor.capture());

        LogEntry savedEntry = captor.getValue();

        assertEquals("", savedEntry.user);
        assertEquals("Выполнено действие пользователем ", savedEntry.message);
    }

    @Test
    void testAuditWithNullUsername() {
        // Arrange
        String userName = null;

        // Act
        auditService.action(userName);

        // Assert
        ArgumentCaptor<LogEntry> captor = ArgumentCaptor.forClass(LogEntry.class);
        verify(repository).save(captor.capture());

        LogEntry savedEntry = captor.getValue();

        // Проверяем, что null обрабатывается (может быть null или "null" в зависимости от реализации)
        assertNotNull(savedEntry, "LogEntry не должен быть null");
        // Проверяем, что метод не упал
        assertDoesNotThrow(() -> auditService.action(null));
    }
}
