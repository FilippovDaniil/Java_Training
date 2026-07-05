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
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
class Task07Test {

    @Autowired
    private ShopOrderService07 shopOrderService;

    @Autowired
    private AuditLog07 auditLog07;

    @Autowired
    private EmailNotifier07 notifier;

    @Test
    void placeOrderTriggersAuditAndNotification() {
        shopOrderService.placeOrder("client-42", "Планшет");

        // Проверка аудит-лога
        assertFalse(auditLog07.getLogs().isEmpty(), "Аудитный лог не должен быть пустым");
        assertTrue(auditLog07.getLogs().get(0).contains("placeOrder"), "Лог должен содержать 'placeOrder'");

        // Проверка уведомлений
        assertFalse(notifier.getNotifications().isEmpty(), "Уведомления не должны быть пустыми");
        assertTrue(notifier.getNotifications().get(0).contains("client-42"),
                "Уведомление должно содержать ID клиента 'client-42'");
    }
}
