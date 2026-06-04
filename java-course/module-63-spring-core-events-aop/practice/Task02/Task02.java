/**
 * Задача 02 — Модуль 63: Кастомное событие OrderCreatedEvent
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md).
 * Это программа с main — запускайте в IDE с подключёнными зависимостями.
 *
 * ЗАДАНИЕ:
 *   1) OrderCreatedEvent уже готов (класс ниже). Изучите его структуру.
 *   2) В OrderService02 реализуйте метод createOrder(String orderId):
 *        - выведите "Создаём заказ: <orderId>"
 *        - опубликуйте событие OrderCreatedEvent через publisher.publishEvent(...)
 *   3) В OrderCreatedListener реализуйте метод onOrderCreated(OrderCreatedEvent event):
 *        - добавьте @EventListener
 *        - выведите "Уведомление: заказ <orderId> создан!"
 *   4) В main создайте контекст, получите бин OrderService02 и вызовите createOrder("ORD-001").
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Создаём заказ: ORD-001
 *   Уведомление: заказ ORD-001 создан!
 *
 * ПОДСКАЗКА:
 *   publisher.publishEvent(new OrderCreatedEvent(this, orderId));
 *
 *   Чтобы получить бин из контекста:
 *   OrderService02 svc = ctx.getBean(OrderService02.class);
 */

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class Task02 {

    public static void main(String[] args) {
        // TODO: создайте контекст с AppConfig02
        // TODO: получите бин OrderService02 и вызовите createOrder("ORD-001")
        // TODO: закройте контекст
    }
}
