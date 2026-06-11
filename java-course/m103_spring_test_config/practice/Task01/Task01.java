package m103_spring_test_config.practice.task01;

/**
 * Задача 01 — Модуль 103: профиль test и application-test.properties
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   1) В src/test/resources создайте application-test.properties:
 *          app.environment=test
 *          app.max-tasks=3
 *   2) Тест:
 *        - @SpringBootTest(classes = Task01.class);
 *        - @ActiveProfiles("test")   // активирует профиль → подхватывает application-test.properties;
 *        - @Value("${app.environment}") String env; @Value("${app.max-tasks}") int maxTasks;
 *        - profile_props_loaded(): assertThat(env).isEqualTo("test"); assertThat(maxTasks).isEqualTo(3).
 *
 * ЦЕЛЬ: понять связь @ActiveProfiles("test") ↔ application-test.properties (src/test/resources).
 *
 * ВАЖНО: без @ActiveProfiles("test") файл application-test.properties не активируется.
 *
 * ПОДСКАЗКА: тестовые свойства кладут в src/test/resources, НЕ в src/main/resources.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task01 {
    public static void main(String[] args) { SpringApplication.run(Task01.class, args); }
}
