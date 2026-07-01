package m60_spring_core_beans.practice.task06;

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
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(NotifierConfig.class);
        // TODO: получите бин NotificationService
        NotificationService service = ctx.getBean(NotificationService.class);
        // TODO: вызовите notifyAll("Вышел новый пост!")
        service.notifyAll("Вышел новый пост!!!");
        // TODO: убедитесь, что порядок: Email → SMS → Push
        // TODO: закройте контекст
        ctx.close();
    }
}
