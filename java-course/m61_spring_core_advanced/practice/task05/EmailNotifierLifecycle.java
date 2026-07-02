package m61_spring_core_advanced.practice.task05;

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
@Component
class EmailNotifierLifecycle {

    public EmailNotifierLifecycle() {
        System.out.println("EmailNotifier: конструктор");
    }

    // TODO: добавьте @PostConstruct
    @PostConstruct
    public void init() {
        // TODO: вывести "EmailNotifier: @PostConstruct (открыть соединение)"
        System.out.println("EmailNotifier: @PostConstruct (открыть соединение)");
    }

    public void send(String message) {
        System.out.println("[EMAIL] " + message);
    }

    // TODO: добавьте @PreDestroy
    @PreDestroy
    public void cleanup() {
        // TODO: вывести "EmailNotifier: @PreDestroy (закрыть соединение)"
        System.out.println("EmailNotifier: @PreDestroy (закрыть соединение)");
    }
}
