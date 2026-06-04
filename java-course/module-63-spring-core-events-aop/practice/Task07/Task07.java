/**
 * Задача 07 — Модуль 63: МИНИ-ПРОЕКТ «Аудит + событийные уведомления»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework:spring-context:6.1.x,
 *   org.springframework:spring-aspects:6.1.x,
 *   org.aspectj:aspectjweaver:1.9.x,
 *   org.springframework:spring-test:6.1.x,
 *   org.junit.jupiter:junit-jupiter:5.x  (см. theory.md)
 *
 * ЗАДАНИЕ (три части в одном файле):
 *
 *   ЧАСТЬ A — Аспект аудита (@Around):
 *     AuditAspect07 должен перехватывать все методы ShopOrderService07.
 *     До вызова — вывести "[AUDIT START] <метод>(<аргументы>)".
 *     После вызова — вывести "[AUDIT END]   <метод> — завершён за <мс> мс".
 *     Реализуйте метод audit(ProceedingJoinPoint pjp) в AuditAspect07.
 *
 *   ЧАСТЬ B — Событийное уведомление:
 *     ShopOrderService07.placeOrder(String customerId, String product) должен:
 *       1) выводить "Оформляем заказ: товар=<product>, клиент=<customerId>"
 *       2) публиковать ShopOrderEvent07 через publisher.publishEvent(...)
 *     EmailNotifier07 уже содержит слушатель — добавьте @EventListener и логику:
 *       выводить "[EMAIL] Клиенту <customerId>: ваш заказ на <product> принят."
 *
 *   ЧАСТЬ C — Интеграционный тест (класс Task07Test):
 *     Тест является @SpringJUnitConfig(AppConfig07.class).
 *     placeOrderTriggersAuditAndNotification():
 *       - вызовите shopOrderService.placeOrder("client-42", "Планшет")
 *       - проверьте, что auditLog07.getLogs() не пуст
 *       - проверьте, что notifier.getNotifications() не пуст
 *       - проверьте, что уведомление содержит "client-42"
 *
 * АРХИТЕКТУРА (все стрелки — через Spring-контейнер):
 *
 *   [тест / main]
 *       │
 *       ▼
 *   ShopOrderService07 ──publish──► ShopOrderEvent07
 *       │                                │
 *   [AuditAspect07]            [EmailNotifier07.onOrder]
 *   ([AUDIT START/END])        ([EMAIL] уведомление)
 *
 * ПОДСКАЗКА: смотрите Task02 (событие), Task04 (@Around), Task06 (тест).
 *   Не забудьте @Async не нужен — уведомление синхронное.
 */

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

class Task07 {

    public static void main(String[] args) {
        var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(AppConfig07.class);
        ShopOrderService07 service = ctx.getBean(ShopOrderService07.class);
        service.placeOrder("client-42", "Планшет");
        service.placeOrder("client-99", "Смартфон");
        ctx.close();
    }
}
