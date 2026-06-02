/**
 * Задача 01 — Модуль 61: @Qualifier — выбор конкретной реализации
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Интерфейс Notifier имеет две реализации: EmailNotifier и SmsNotifier.
 *   Класс AlertService должен использовать ТОЛЬКО EmailNotifier.
 *
 *     1) Пометьте EmailNotifier и SmsNotifier аннотацией @Component.
 *     2) В поле Notifier внутри AlertService добавьте @Autowired и
 *        @Qualifier("emailNotifier"), чтобы явно выбрать нужную реализацию.
 *     3) Поднимите контекст, получите AlertService и вызовите send("Тест").
 *        Убедитесь, что сообщение отправляется через Email.
 *
 * ПОДСКАЗКА:
 *   @Autowired
 *   @Qualifier("emailNotifier")
 *   private Notifier notifier;
 *
 *   Имя квалификатора по умолчанию = имя класса с маленькой буквы.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class Task01 {

    public static void main(String[] args) {
        // Поднять контекст с текущим пакетом
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig01.class);

        // TODO: получить бин AlertService из контекста
        // TODO: вызвать alertService.notify("Сервер недоступен")
        // TODO: убедиться в выводе, что использован EmailNotifier

        context.close();
    }
}

// ============================================================
// Конфигурация
// ============================================================

@Configuration
@ComponentScan
class AppConfig01 {}

// ============================================================
// Интерфейс канала уведомлений
// ============================================================

interface Notifier {
    void send(String message);
}

// ============================================================
// Реализация 1: Email
// ============================================================

// TODO: добавьте @Component
class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("[EMAIL] " + message);
    }
}

// ============================================================
// Реализация 2: SMS
// ============================================================

// TODO: добавьте @Component
class SmsNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("[SMS] " + message);
    }
}

// ============================================================
// Сервис, использующий конкретный канал
// ============================================================

@Component
class AlertService {

    // TODO: добавьте @Autowired
    // TODO: добавьте @Qualifier("emailNotifier")
    private Notifier notifier;

    public void notify(String message) {
        // TODO: вызовите notifier.send(message)
    }
}
