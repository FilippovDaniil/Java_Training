import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuditService {
    private final LogRepository repo;
    AuditService(LogRepository repo) { this.repo = repo; }
    void action(String user) {
        repo.save(new LogEntry(user, "Выполнено действие пользователем " + user));
    }
}
