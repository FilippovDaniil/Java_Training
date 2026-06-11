package m34_testing_junit_mockito.practice.task02;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class NotificationService {
    private final UserRepo repo;
    private final MailSender mail;
    NotificationService(UserRepo repo, MailSender mail) { this.repo = repo; this.mail = mail; }
    void register(String email) {
        repo.save(email);
        mail.send(email, "Добро пожаловать!");
    }
}
