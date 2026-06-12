package m63_spring_core_events_aop.practice.task02;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// ============================================================
// Сервис — издатель события
// ============================================================

@Service
class OrderService02 {

    private final ApplicationEventPublisher publisher;

    // Spring автоматически инжектирует ApplicationEventPublisher
    public OrderService02(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void createOrder(String orderId) {
        System.out.println("Создаём заказ: " + orderId);
        // TODO: опубликуйте OrderCreatedEvent через publisher.publishEvent(...)
    }
}
