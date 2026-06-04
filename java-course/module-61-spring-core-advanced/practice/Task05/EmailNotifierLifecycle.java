import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

// ============================================================
// Бин с полным жизненным циклом
// ============================================================

// TODO: добавьте @Component
class EmailNotifierLifecycle {

    public EmailNotifierLifecycle() {
        System.out.println("EmailNotifier: конструктор");
    }

    // TODO: добавьте @PostConstruct
    public void init() {
        // TODO: вывести "EmailNotifier: @PostConstruct (открыть соединение)"
    }

    public void send(String message) {
        System.out.println("[EMAIL] " + message);
    }

    // TODO: добавьте @PreDestroy
    public void cleanup() {
        // TODO: вывести "EmailNotifier: @PreDestroy (закрыть соединение)"
    }
}
