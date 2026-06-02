/**
 * Задача 06 — Модуль 60: Инъекция коллекции бинов + @Order
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Реализуйте систему уведомлений: несколько каналов доставки, все должны
 *   быть вызваны при публикации поста.
 *
 *     1) Интерфейс Notifier уже готов. Создайте три реализации:
 *          EmailNotifier — выводит "Email: <сообщение>"
 *          SmsNotifier   — выводит "SMS: <сообщение>"
 *          PushNotifier  — выводит "Push: <сообщение>"
 *        Пометьте каждую @Component.
 *
 *     2) Управляйте порядком вызовов через @Order:
 *          EmailNotifier → @Order(1)   (первым)
 *          SmsNotifier   → @Order(2)
 *          PushNotifier  → @Order(3)   (последним)
 *
 *     3) В NotificationService внедрите List<Notifier> через конструктор.
 *        Spring автоматически соберёт все бины, реализующие Notifier,
 *        и упорядочит их по @Order.
 *
 *     4) В методе notifyAll(String message) вызовите notify() у каждого нотификатора.
 *
 *     5) В main поднимите контекст, получите NotificationService,
 *        вызовите notifyAll("Вышел новый пост!").
 *        Ожидаемый порядок вывода: Email → SMS → Push.
 *
 * ПОДСКАЗКА:
 *   // Конструктор сервиса:
 *   public NotificationService(List<Notifier> notifiers) {
 *       this.notifiers = notifiers;
 *   }
 *
 *   Spring соберёт список сам. @Order(n) определяет позицию в List.
 */
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public class Task06 {

    public static void main(String[] args) {
        // TODO: создайте контекст с NotifierConfig.class
        // TODO: получите бин NotificationService
        // TODO: вызовите notifyAll("Вышел новый пост!")
        // TODO: убедитесь, что порядок: Email → SMS → Push
        // TODO: закройте контекст
    }
}

@Configuration
@ComponentScan(basePackages = "blog")
class NotifierConfig { }

// ============================================================
// Интерфейс — готов
// ============================================================

interface Notifier {
    void notify(String message);
}

// ============================================================
// Реализации — добавьте @Component и @Order (TODO-метки)
// ============================================================

// TODO: @Component
// TODO: @Order(1)
class EmailNotifier implements Notifier {
    @Override
    public void notify(String message) {
        // TODO: System.out.println("Email: " + message);
    }
}

// TODO: @Component
// TODO: @Order(2)
class SmsNotifier implements Notifier {
    @Override
    public void notify(String message) {
        // TODO: System.out.println("SMS: " + message);
    }
}

// TODO: @Component
// TODO: @Order(3)
class PushNotifier implements Notifier {
    @Override
    public void notify(String message) {
        // TODO: System.out.println("Push: " + message);
    }
}

// ============================================================
// Сервис — реализуйте инъекцию List<Notifier>
// ============================================================

@Service
class NotificationService {

    // TODO: объявите поле  private final List<Notifier> notifiers

    // TODO: добавьте конструктор, принимающий List<Notifier>

    public void notifyAll(String message) {
        // TODO: переберите notifiers и вызовите notify(message) у каждого
    }
}
