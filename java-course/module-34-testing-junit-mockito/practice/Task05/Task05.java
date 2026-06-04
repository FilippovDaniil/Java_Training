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
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Task05 {

    @Test
    void testAuditCapturesLogEntry() {
        // Перехватите аргумент save через ArgumentCaptor и проверьте поля
    }
}
