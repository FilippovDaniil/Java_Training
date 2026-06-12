package m63_spring_core_events_aop.practice.task01;

/**
 * Задача 01 — Модуль 63: Слушатель встроенного события Spring
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md).
 * Это программа с main — запускайте в IDE с подключёнными зависимостями.
 *
 * ЗАДАНИЕ:
 *   1) Поднимите контекст Spring (AnnotationConfigApplicationContext) с классом AppConfig.
 *   2) StartupListener уже объявлен ниже — он слушает ContextRefreshedEvent.
 *      Добавьте аннотацию @EventListener над методом onRefresh().
 *   3) Запустите программу. В консоли должно появиться сообщение:
 *      "Контекст поднят! Приложение готово к работе."
 *   4) Закройте контекст (close()). Добавьте второй слушатель на ContextClosedEvent
 *      и убедитесь, что при закрытии выводится соответствующее сообщение.
 *
 * ПОДСКАЗКА:
 *   @EventListener
 *   public void onRefresh(ContextRefreshedEvent event) { ... }
 *
 *   Встроенные события: ContextRefreshedEvent, ContextClosedEvent — они уже есть в spring-context,
 *   ничего дополнительного создавать не нужно.
 */

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

public class Task01 {

    public static void main(String[] args) {
        // TODO: создайте AnnotationConfigApplicationContext(AppConfig01.class)
        // TODO: закройте контекст методом close()
    }
}
