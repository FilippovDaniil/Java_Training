package m61_spring_core_advanced.practice.task06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

// Бин B зависит от A через конструктор — ЦИКЛ
@Component
class RoutingServiceBroken {
    private final NotificationDispatcher nd;

    RoutingServiceBroken(NotificationDispatcher nd) {
        this.nd = nd;
    }

    public void route(String message) {
        System.out.println("[ROUTE] " + message);
    }
}
