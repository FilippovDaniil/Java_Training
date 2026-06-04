import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// ============================================================
// Реализации (TODO: расставьте аннотации)
// ============================================================
class ConsoleNotificationService implements NotificationService {
    @Override
    public void send(String to, String message) {
        // TODO: вывести "[CONSOLE] → to: <адрес>, сообщение: <текст>"
    }
}
