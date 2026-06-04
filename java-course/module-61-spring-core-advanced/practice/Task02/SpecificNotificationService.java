import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// ============================================================
// Сервис с квалификатором — явно запрашивает SMS
// ============================================================

@Component
class SpecificNotificationService {

    // TODO: @Autowired
    // TODO: @Qualifier("smsNotifier02")
    private Notifier02 notifier;

    public void send(String message) {
        // TODO: notifier.send(message)
    }
}
