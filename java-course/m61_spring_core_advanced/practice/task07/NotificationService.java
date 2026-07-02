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
@Component
class NotificationService {

    // TODO: @Autowired (без @Qualifier — внедрится @Primary → EmailChannel)
    @Autowired
    private NotificationChannel defaultChannel;

    // TODO: @Autowired
    // TODO: @Qualifier("smsChannel")
    @Autowired
    @Qualifier("smsChannel")
    private NotificationChannel smsChannel;

    // TODO: @Autowired
    @Autowired
    private ApplicationContext context;

    // TODO: @PostConstruct — вывести "NotificationService готов"
    @PostConstruct
    public void init() {
        System.out.println("NotificationService готов");
    }

    // TODO: @PreDestroy — вывести "NotificationService завершает работу"
    @PreDestroy
    public void destroy() {
        System.out.println("NotificationService завершает работу");
    }

    public void sendDefault(String recipient, String body) {
        // TODO: получить новый NotificationMessage через context.getBean(NotificationMessage.class)
        NotificationMessage notificationMessage = context.getBean(NotificationMessage.class);
        // TODO: установить recipient и body
        notificationMessage.setRecipient(recipient);
        notificationMessage.setBody(body);
        // TODO: вывести лог: "NotificationMessage создан для: " + recipient
        System.out.println("NotificationMessage создан для: " + recipient);
        // TODO: вызвать defaultChannel.send(recipient, body)
        defaultChannel.send(recipient, body);
    }

    public void sendViaSms(String recipient, String body) {
        // TODO: аналогично sendDefault, но использовать smsChannel
        // TODO: получить новый NotificationMessage через context.getBean(NotificationMessage.class)
        NotificationMessage notificationMessage = context.getBean(NotificationMessage.class);
        // TODO: установить recipient и body
        notificationMessage.setRecipient(recipient);
        notificationMessage.setBody(body);
        // TODO: вывести лог: "NotificationMessage создан для: " + recipient
        System.out.println("NotificationMessage создан для: " + recipient);
        // TODO: вызвать defaultChannel.send(recipient, body)
        smsChannel.send(recipient,body);
    }
}
