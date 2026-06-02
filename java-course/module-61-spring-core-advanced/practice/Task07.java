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
        // TODO: получить NotificationService
        // TODO: вызвать sendDefault("user@example.com", "Ваш заказ принят")
        // TODO: вызвать sendViaSms("+79001234567", "Код подтверждения: 1234")
        // TODO: закрыть контекст
    }
}

// ============================================================
// Конфигурация
// ============================================================

@Configuration
@ComponentScan
class AppConfig07 {}

// ============================================================
// Интерфейс канала уведомлений
// ============================================================

interface NotificationChannel {
    void send(String recipient, String body);
}

// ============================================================
// EmailChannel — канал по умолчанию
// ============================================================

// TODO: @Component
// TODO: @Primary
class EmailChannel implements NotificationChannel {

    // TODO: @PostConstruct — вывести "EmailChannel: инициализирован"
    public void init() { }

    @Override
    public void send(String recipient, String body) {
        // TODO: вывести "[EMAIL → recipient] body"
    }
}

// ============================================================
// SmsChannel
// ============================================================

// TODO: @Component
class SmsChannel implements NotificationChannel {

    // TODO: @PostConstruct — вывести "SmsChannel: инициализирован"
    public void init() { }

    @Override
    public void send(String recipient, String body) {
        // TODO: вывести "[SMS → recipient] body"
    }
}

// ============================================================
// PushChannel
// ============================================================

// TODO: @Component
class PushChannel implements NotificationChannel {

    // TODO: @PostConstruct — вывести "PushChannel: инициализирован"
    public void init() { }

    @Override
    public void send(String recipient, String body) {
        // TODO: вывести "[PUSH → recipient] body"
    }
}

// ============================================================
// NotificationMessage — prototype (новый объект для каждого сообщения)
// ============================================================

// TODO: @Component
// TODO: @Scope("prototype")
class NotificationMessage {

    private String recipient;
    private String body;

    // TODO: @PostConstruct — вывести "NotificationMessage создан для: " + recipient
    // (recipient может быть null на этапе @PostConstruct, если не задан до создания)
    public void init() { }

    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
}

// ============================================================
// NotificationService — основной сервис (singleton)
// ============================================================

// TODO: @Component
class NotificationService {

    // TODO: @Autowired (без @Qualifier — внедрится @Primary → EmailChannel)
    private NotificationChannel defaultChannel;

    // TODO: @Autowired
    // TODO: @Qualifier("smsChannel")
    private NotificationChannel smsChannel;

    // TODO: @Autowired
    private ApplicationContext context;

    // TODO: @PostConstruct — вывести "NotificationService готов"
    public void init() { }

    // TODO: @PreDestroy — вывести "NotificationService завершает работу"
    public void destroy() { }

    public void sendDefault(String recipient, String body) {
        // TODO: получить новый NotificationMessage через context.getBean(NotificationMessage.class)
        // TODO: установить recipient и body
        // TODO: вывести лог: "NotificationMessage создан для: " + recipient
        // TODO: вызвать defaultChannel.send(recipient, body)
    }

    public void sendViaSms(String recipient, String body) {
        // TODO: аналогично sendDefault, но использовать smsChannel
    }
}
