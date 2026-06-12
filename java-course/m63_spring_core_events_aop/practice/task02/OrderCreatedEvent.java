package m63_spring_core_events_aop.practice.task02;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// ============================================================
// Кастомное событие (готово — изучите, не изменяйте)
// ============================================================
class OrderCreatedEvent {
    private final Object source;
    private final String orderId;

    public OrderCreatedEvent(Object source, String orderId) {
        this.source = source;
        this.orderId = orderId;
    }

    public String getOrderId() { return orderId; }
    public Object getSource()  { return source; }
}
