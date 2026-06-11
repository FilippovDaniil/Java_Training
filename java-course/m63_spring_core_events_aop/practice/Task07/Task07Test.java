package m63_spring_core_events_aop.practice.task07;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// ============================================================
// Интеграционный тест
// ============================================================

@SpringJUnitConfig(AppConfig07.class)
class Task07Test {

    @Autowired
    ShopOrderService07 shopOrderService;

    @Autowired
    AuditLog07 auditLog07;

    @Autowired
    EmailNotifier07 notifier;

    @Test
    void placeOrderTriggersAuditAndNotification() {
        // TODO: вызовите shopOrderService.placeOrder("client-42", "Планшет")
        // TODO: проверьте, что auditLog07.getLogs() не пуст
        // TODO: проверьте, что notifier.getNotifications() не пуст
        // TODO: проверьте, что notifier.getNotifications().get(0) содержит "client-42"
    }
}
