package m61_spring_core_advanced.practice.task04;

/**
 * Задача 04 — Модуль 61: @Lazy — отложенная инициализация бина
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Продемонстрируйте разницу между eager (стандартным) и lazy (отложенным)
 *   созданием бина через @PostConstruct-логирование.
 *
 *     1) Создайте EagerNotifier с @Component и @PostConstruct-методом,
 *        который печатает "EagerNotifier создан".
 *     2) Создайте LazyNotifier с @Component + @Lazy и @PostConstruct-методом,
 *        который печатает "LazyNotifier создан".
 *     3) В main:
 *        а) Поднимите контекст — сразу должно появиться "EagerNotifier создан",
 *           но НЕ должно быть "LazyNotifier создан".
 *        б) Только после context.getBean(LazyNotifier.class) должно появиться
 *           "LazyNotifier создан".
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   [СТАРТ контекста]
 *   EagerNotifier создан
 *   [ЗАПРОС LazyNotifier]
 *   LazyNotifier создан
 *
 * ПОДСКАЗКА:
 *   @Lazy откладывает вызов конструктора и @PostConstruct до первого getBean().
 */

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

public class Task04 {

    public static void main(String[] args) {
        System.out.println("[СТАРТ контекста]");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig04.class);

        // EagerNotifier уже должен быть создан (вывод появился выше)
        // LazyNotifier ещё НЕ создан

        System.out.println("[ЗАПРОС LazyNotifier]");

        // TODO: вызовите context.getBean(LazyNotifier.class)
        context.getBean(LazyNotifier.class);
        // TODO: убедитесь, что "LazyNotifier создан" появился ПОСЛЕ "[ЗАПРОС LazyNotifier]"

        context.close();
    }
}
