package m61_spring_core_advanced.practice.task07;

/**
 * Задача 07 — Модуль 61: Мини-проект — система уведомлений
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Соберите систему уведомлений, применив все темы модуля:
 *
 *     1) Три реализации интерфейса NotificationChannel:
 *        - EmailChannel    (@Component, @Primary — канал по умолчанию)
 *        - SmsChannel      (@Component)
 *        - PushChannel     (@Component)
 *        Каждый реализует send(String recipient, String body).
 *        Каждый логирует своё создание через @PostConstruct.
 *
 *     2) NotificationMessage — prototype-бин (@Scope("prototype")):
 *        Хранит recipient и body; создаётся заново для каждого сообщения.
 *        @PostConstruct логирует: "NotificationMessage создан для: <recipient>".
 *
 *     3) NotificationService:
 *        - Поле defaultChannel: тип NotificationChannel без @Qualifier → внедрится @Primary (Email).
 *        - Поле smsChannel: тип NotificationChannel + @Qualifier("smsChannel").
 *        - ApplicationContext для получения prototype-бинов NotificationMessage.
 *        - Метод sendDefault(String recipient, String body):
 *              получить новый NotificationMessage через context.getBean(),
 *              вызвать defaultChannel.send(recipient, body).
 *        - Метод sendViaSms(String recipient, String body):
 *              аналогично, но использует smsChannel.
 *        - @PostConstruct: "NotificationService готов".
 *        - @PreDestroy: "NotificationService завершает работу".
 *
 *     4) В main:
 *        а) Поднять контекст.
 *        б) Получить NotificationService.
 *        в) Вызвать sendDefault("user@example.com", "Ваш заказ принят").
 *        г) Вызвать sendViaSms("+79001234567", "Код подтверждения: 1234").
 *        д) Закрыть контекст.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (порядок может отличаться для @PostConstruct):
 *   EmailChannel: инициализирован
 *   SmsChannel: инициализирован
 *   PushChannel: инициализирован
 *   NotificationService готов
 *   NotificationMessage создан для: user@example.com
 *   [EMAIL → user@example.com] Ваш заказ принят
 *   NotificationMessage создан для: +79001234567
 *   [SMS → +79001234567] Код подтверждения: 1234
 *   NotificationService завершает работу
 *
 * ПОДСКАЗКА:
 *   Для получения prototype-бина внутри singleton используйте ApplicationContext:
 *     NotificationMessage msg = context.getBean(NotificationMessage.class);
 *   Это единственный корректный способ получить новый экземпляр prototype
 *   при каждом вызове метода singleton-бина.
 */

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class Task07 {

    public static void main(String[] args) {
        // TODO: поднять контекст (AppConfig07)
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig07.class);
        // TODO: получить NotificationService
        NotificationService service = ctx.getBean(NotificationService.class);
        // TODO: вызвать sendDefault("user@example.com", "Ваш заказ принят")
        service.sendDefault("user@example.com", "Ваш заказ принят");
        // TODO: вызвать sendViaSms("+79001234567", "Код подтверждения: 1234")
        service.sendViaSms("+79001234567", "Код подтверждения: 1234");
        // TODO: закрыть контекст
        ctx.close();
    }
}
