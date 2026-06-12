package m61_spring_core_advanced.practice.task01;

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
