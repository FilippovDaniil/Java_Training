/**
 * Задача 03 — Модуль 103: инлайн-свойства через @SpringBootTest(properties = ...)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Бин-сервис AppInfo03 (готов) читает свойство app.name через @Value. Задайте его инлайн:
 *     1) @SpringBootTest(classes = Task03.class, properties = "app.name=TaskTracker-Test");
 *     2) @Autowired AppInfo03 info;
 *     3) inline_property(): assertThat(info.name()).isEqualTo("TaskTracker-Test").
 *
 * ЦЕЛЬ: ещё один способ задать свойство — прямо в @SpringBootTest(properties=...).
 *
 * ВАЖНО: дефолт в @Value("${app.name:unknown}") спасает, если свойство не задано.
 *
 * ПОДСКАЗКА: properties принимает как одну строку, так и массив "k=v".
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task03 {
    public static void main(String[] args) { SpringApplication.run(Task03.class, args); }
}
