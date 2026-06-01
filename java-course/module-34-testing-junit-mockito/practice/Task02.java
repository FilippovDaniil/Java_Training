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

// Классы под тестом (готовы)
interface UserRepo { void save(String email); }
interface MailSender {
    void send(String email, String text);
    void sendSms(String phone);
}

class NotificationService {
    private final UserRepo repo;
    private final MailSender mail;
    NotificationService(UserRepo repo, MailSender mail) { this.repo = repo; this.mail = mail; }
    void register(String email) {
        repo.save(email);
        mail.send(email, "Добро пожаловать!");
    }
}
