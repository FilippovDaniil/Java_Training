package m61_spring_core_advanced.practice.task02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// ============================================================
// Сервис без квалификатора — должен получить @Primary бин
// ============================================================

@Component
class DefaultNotificationService {

    // TODO: @Autowired (без @Qualifier — должен прийти PushNotifier02)
    @Autowired
    private Notifier02 notifier;

    public void send(String message) {
        // TODO: notifier.send(message)
        notifier.send(message);
    }
}
