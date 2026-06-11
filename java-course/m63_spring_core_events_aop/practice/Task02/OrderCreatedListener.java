package m63_spring_core_events_aop.practice.task02;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// ============================================================
// Слушатель события
// ============================================================

@Component
class OrderCreatedListener {

    // TODO: добавьте @EventListener
    public void onOrderCreated(OrderCreatedEvent event) {
        // TODO: выведите "Уведомление: заказ <orderId> создан!"
    }
}
