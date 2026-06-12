package m61_spring_core_advanced.practice.task06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

// Бин A зависит от B через конструктор
@Component
class NotificationDispatcher {
    private final RoutingServiceBroken rs;

    NotificationDispatcher(RoutingServiceBroken rs) {
        this.rs = rs;
    }

    public void dispatch(String message) {
        System.out.println("Dispatching: " + message);
        rs.route(message);
    }
}
