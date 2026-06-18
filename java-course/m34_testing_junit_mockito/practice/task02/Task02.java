package m34_testing_junit_mockito.practice.task02;

/**
 * Задача 02 — Модуль 34: verify (проверка вызовов)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: JUnit 5 + Mockito.
 *
 * ЗАДАНИЕ:
 *   Класс NotificationService при register(email) должен:
 *     - сохранить пользователя через repository.save(email);
 *     - отправить письмо через mailSender.send(email, ...).
 *   Напишите тест с ДВУМЯ моками (repository, mailSender) и проверьте
 *   через verify, что оба метода были вызваны РОВНО один раз с нужным
 *   аргументом. Также проверьте never() для метода, который не должен
 *   вызываться.
 *
 * ПОДСКАЗКА:
 *   verify(repository, times(1)).save("a@b.c");
 *   verify(mailSender).send(eq("a@b.c"), anyString());
 *   verify(mailSender, never()).sendSms(anyString());
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


public class Task02 {

    @Mock
    private MailSender mailSender;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        // Инициализируем моки
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterSavesAndSendsMail() {
        // Arrange - подготовка
        String email = "a@b.c";

        // Act - действие
        notificationService.register(email);

        // Assert - проверка
        // 1. Проверяем, что repository.save() был вызван ровно 1 раз с правильным email
        verify(userRepo, times(1)).save(email);

        // 2. Проверяем, что mailSender.send() был вызван 1 раз с правильным email и любым текстом
        verify(mailSender, times(1)).send(eq(email), anyString());

        // 3. Проверяем, что mailSender.sendSms() НЕ был вызван
        verify(mailSender, never()).sendSms(anyString());
    }
}