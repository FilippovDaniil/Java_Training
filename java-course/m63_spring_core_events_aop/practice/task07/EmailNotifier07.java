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
// Уведомитель по email (каркас — реализуйте слушатель)
// ============================================================

@Component
class EmailNotifier07 {
    private final List<String> notifications = new ArrayList<>();

    // TODO: добавьте @EventListener
    public void onOrder(ShopOrderEvent07 event) {
        // TODO: сформируйте строку "[EMAIL] Клиенту <customerId>: ваш заказ на <product> принят."
        // TODO: добавьте строку в notifications
        // TODO: выведите строку в консоль
    }

    public List<String> getNotifications() { return notifications; }
}
