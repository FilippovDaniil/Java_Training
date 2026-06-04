import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

// ============================================================
// Реализация 2: SMS
// ============================================================

// TODO: добавьте @Component
class SmsNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("[SMS] " + message);
    }
}
