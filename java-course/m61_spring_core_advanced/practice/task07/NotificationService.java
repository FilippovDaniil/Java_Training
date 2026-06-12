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
// NotificationService — основной сервис (singleton)
// ============================================================

// TODO: @Component
class NotificationService {

    // TODO: @Autowired (без @Qualifier — внедрится @Primary → EmailChannel)
    private NotificationChannel defaultChannel;

    // TODO: @Autowired
    // TODO: @Qualifier("smsChannel")
    private NotificationChannel smsChannel;

    // TODO: @Autowired
    private ApplicationContext context;

    // TODO: @PostConstruct — вывести "NotificationService готов"
    public void init() { }

    // TODO: @PreDestroy — вывести "NotificationService завершает работу"
    public void destroy() { }

    public void sendDefault(String recipient, String body) {
        // TODO: получить новый NotificationMessage через context.getBean(NotificationMessage.class)
        // TODO: установить recipient и body
        // TODO: вывести лог: "NotificationMessage создан для: " + recipient
        // TODO: вызвать defaultChannel.send(recipient, body)
    }

    public void sendViaSms(String recipient, String body) {
        // TODO: аналогично sendDefault, но использовать smsChannel
    }
}
