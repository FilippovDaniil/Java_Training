package m103_spring_test_config.practice.task02;

/**
 * Задача 02 — Модуль 103: @TestPropertySource — точечное переопределение свойств
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Переопределите свойства прямо в аннотации класса (без отдельного файла):
 *     1) @SpringBootTest(classes = Task02.class);
 *     2) @TestPropertySource(properties = {
 *            "app.max-tasks=10",
 *            "app.feature.notifications=false"
 *        });
 *     3) @Value("${app.max-tasks}") int maxTasks; @Value("${app.feature.notifications}") boolean notif;
 *     4) overrides_props(): assertThat(maxTasks).isEqualTo(10); assertThat(notif).isFalse().
 *
 * ЦЕЛЬ: задавать свойства для конкретного класса тестов, не трогая общие файлы.
 *
 * ВАЖНО: @TestPropertySource перекрывает application*.properties (приоритет выше).
 *
 * ПОДСКАЗКА: удобно, когда нужно проверить поведение при конкретных значениях конфигурации.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task02 {
    public static void main(String[] args) { SpringApplication.run(Task02.class, args); }
}
