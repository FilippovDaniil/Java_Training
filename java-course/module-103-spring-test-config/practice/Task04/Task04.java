/**
 * Задача 04 — Модуль 103: тест биндинга @ConfigurationProperties
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Типобезопасная конфигурация AppProps04 (готова, prefix=app). Проверьте, что свойства
 *   корректно биндятся в поля:
 *     1) @SpringBootTest(classes = Task04.class);
 *     2) @TestPropertySource(properties = {"app.max-tasks=7", "app.notifications=true"});
 *     3) @Autowired AppProps04 props;
 *     4) binds_fields(): assertThat(props.maxTasks()).isEqualTo(7); assertThat(props.notifications()).isTrue().
 *
 * ЦЕЛЬ: убедиться, что внешняя конфигурация правильно ложится в @ConfigurationProperties-объект.
 *
 * ВАЖНО: app.max-tasks (kebab-case) биндится в поле maxTasks (relaxed binding).
 *
 * ПОДСКАЗКА: @EnableConfigurationProperties(AppProps04.class) уже стоит на приложении.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task04 {
    public static void main(String[] args) { SpringApplication.run(Task04.class, args); }
}
