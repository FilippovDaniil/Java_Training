package m61_spring_core_advanced.practice.task05;

/**
 * Задача 05 — Модуль 61: жизненный цикл бина — @PostConstruct и @PreDestroy
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Реализуйте EmailNotifierLifecycle с полным жизненным циклом:
 *
 *     1) Конструктор — вывести "EmailNotifier: конструктор".
 *     2) @PostConstruct-метод init() — вывести "EmailNotifier: @PostConstruct (открыть соединение)".
 *     3) Метод send(String) — вывести "[EMAIL] <сообщение>".
 *     4) @PreDestroy-метод cleanup() — вывести "EmailNotifier: @PreDestroy (закрыть соединение)".
 *     5) В main поднимите контекст, вызовите send() и закройте контекст через context.close().
 *        Убедитесь, что @PreDestroy сработал.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   EmailNotifier: конструктор
 *   EmailNotifier: @PostConstruct (открыть соединение)
 *   [EMAIL] Тестовое сообщение
 *   EmailNotifier: @PreDestroy (закрыть соединение)
 *
 * ПОДСКАЗКА:
 *   @PreDestroy работает ТОЛЬКО для singleton-бинов.
 *   context.close() обязателен для корректного вызова @PreDestroy.
 *   Используйте try-with-resources: try (var ctx = new AnnotationConfigApplicationContext(...)) { }
 */

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class Task05 {

    public static void main(String[] args) {
        // TODO: поднять контекст (можно через try-with-resources для автозакрытия)
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig05.class);){
            EmailNotifierLifecycle lifecycle = context.getBean(EmailNotifierLifecycle.class);
            lifecycle.send("Тестовое сообщение");
        }
        // TODO: получить бин EmailNotifierLifecycle
        // TODO: вызвать send("Тестовое сообщение")
        // TODO: закрыть контекст → должен сработать @PreDestroy
    }
}
