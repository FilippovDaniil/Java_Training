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

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class Task02 {

    @Test
    void testRegisterSavesAndSendsMail() {
        // Создайте моки repository и mailSender, вызовите register, проверьте verify
    }
}
