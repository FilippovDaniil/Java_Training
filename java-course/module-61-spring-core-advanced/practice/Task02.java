/**
 * Задача 02 — Модуль 61: @Primary — реализация по умолчанию
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Три реализации Notifier: EmailNotifier, SmsNotifier, PushNotifier.
 *   Нужно сделать PushNotifier реализацией «по умолчанию», которая
 *   внедряется без @Qualifier туда, где тип — просто Notifier.
 *
 *     1) Пометьте все три реализации @Component.
 *     2) Добавьте @Primary к PushNotifier.
 *     3) В DefaultNotificationService используйте @Autowired без @Qualifier.
 *        При запуске должен вывестись "[PUSH] …".
 *     4) В SpecificNotificationService добавьте @Autowired + @Qualifier("smsNotifier").
 *        При запуске должен вывестись "[SMS] …".
 *
 * ПОДСКАЗКА:
 *   @Primary выигрывает у всех кандидатов, когда квалификатор не указан.
 *   @Qualifier точнее @Primary — если оба применимы, побеждает @Qualifier.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

public class Task02 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig02.class);

        // TODO: получить DefaultNotificationService, вызвать send("Привет") → ожидается [PUSH]
        // TODO: получить SpecificNotificationService, вызвать send("Привет") → ожидается [SMS]

        context.close();
    }
}

@Configuration
@ComponentScan
class AppConfig02 {}

// ============================================================
// Интерфейс (тот же, что в Task01)
// ============================================================

interface Notifier02 {
    void send(String message);
}

// ============================================================
// Реализации
// ============================================================

// TODO: @Component
class EmailNotifier02 implements Notifier02 {
    @Override
    public void send(String message) { System.out.println("[EMAIL] " + message); }
}

// TODO: @Component
class SmsNotifier02 implements Notifier02 {
    @Override
    public void send(String message) { System.out.println("[SMS] " + message); }
}

// TODO: @Component
// TODO: @Primary  — этот бин внедряется по умолчанию
class PushNotifier02 implements Notifier02 {
    @Override
    public void send(String message) { System.out.println("[PUSH] " + message); }
}

// ============================================================
// Сервис без квалификатора — должен получить @Primary бин
// ============================================================

@Component
class DefaultNotificationService {

    // TODO: @Autowired (без @Qualifier — должен прийти PushNotifier02)
    private Notifier02 notifier;

    public void send(String message) {
        // TODO: notifier.send(message)
    }
}

// ============================================================
// Сервис с квалификатором — явно запрашивает SMS
// ============================================================

@Component
class SpecificNotificationService {

    // TODO: @Autowired
    // TODO: @Qualifier("smsNotifier02")
    private Notifier02 notifier;

    public void send(String message) {
        // TODO: notifier.send(message)
    }
}
