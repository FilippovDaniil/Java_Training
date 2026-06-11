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
// EmailChannel — канал по умолчанию
// ============================================================

// TODO: @Component
// TODO: @Primary
class EmailChannel implements NotificationChannel {

    // TODO: @PostConstruct — вывести "EmailChannel: инициализирован"
    public void init() { }

    @Override
    public void send(String recipient, String body) {
        // TODO: вывести "[EMAIL → recipient] body"
    }
}
