package m61_spring_core_advanced.practice.task07;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// ============================================================
// PushChannel
// ============================================================

// TODO: @Component
@Component
class PushChannel implements NotificationChannel {

    // TODO: @PostConstruct — вывести "PushChannel: инициализирован"
    @PostConstruct
    public void init() {
        System.out.println("PushChannel: инициализирован");
    }

    @Override
    public void send(String recipient, String body) {
        // TODO: вывести "[PUSH → recipient] body"
        System.out.println("[EMAIL] -> " + recipient + "] " + body);
    }
}
