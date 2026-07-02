package m61_spring_core_advanced.practice.task07;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

// ============================================================
// NotificationMessage — prototype (новый объект для каждого сообщения)
// ============================================================

// TODO: @Component
// TODO: @Scope("prototype")
@Component
@Scope("prototype")
class NotificationMessage {

    private String recipient;
    private String body;

    // TODO: @PostConstruct — вывести "NotificationMessage создан для: " + recipient
    // (recipient может быть null на этапе @PostConstruct, если не задан до создания)
    @PostConstruct
    public void init() {
        System.out.println("NotificationMessage создан для: " + recipient);
    }

    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
}
