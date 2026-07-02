package m61_spring_core_advanced.practice.task01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

// ============================================================
// Сервис, использующий конкретный канал
// ============================================================

@Component
class AlertService {

    // TODO: добавьте @Autowired
    // TODO: добавьте @Qualifier("emailNotifier")
    @Autowired
    @Qualifier("emailNotifier")
    private Notifier notifier;

    public void notify(String message) {
        // TODO: вызовите notifier.send(message)
        notifier.send(message);
    }
}
