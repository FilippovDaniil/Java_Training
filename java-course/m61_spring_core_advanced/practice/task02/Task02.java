package m61_spring_core_advanced.practice.task02;

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
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig02.class);

        // TODO: получить DefaultNotificationService, вызвать send("Привет") → ожидается [PUSH]
        context.getBean(DefaultNotificationService.class).send("Привет");
        // TODO: получить SpecificNotificationService, вызвать send("Привет") → ожидается [SMS]
        context.getBean(SpecificNotificationService.class).send("Привет");

        context.close();
    }
}
