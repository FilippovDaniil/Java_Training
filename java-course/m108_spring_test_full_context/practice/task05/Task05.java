package m108_spring_test_full_context.practice.task05;

/**
 * Задача 05 — Модуль 108: @DirtiesContext — пересоздание контекста
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Бин CounterBean05 (готов) хранит состояние. Тест его меняет — чтобы это не «утекло»
 *   в другие тестовые классы, помечаем сброс контекста:
 *     1) @SpringBootTest(classes = Task05.class);
 *        @DirtiesContext(classMode = AFTER_CLASS);   // пересоздать контекст после класса
 *        @Autowired CounterBean05 counter.
 *     2) mutates_state(): counter.increment(); counter.increment();
 *          assertThat(counter.value()).isEqualTo(2);   // состояние изменено
 *
 * ЦЕЛЬ: понять, что полный контекст КЭШИРУЕТСЯ между классами; @DirtiesContext сбрасывает его.
 *
 * ВАЖНО: @DirtiesContext замедляет сборку (пересоздание контекста) — применять ТОЧЕЧНО.
 *        Лучше писать тесты, не портящие состояние (или откатывать через @Transactional).
 *
 * ПОДСКАЗКА: classMode = AFTER_CLASS — сброс после всех тестов класса.
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicInteger;
import static org.assertj.core.api.Assertions.assertThat;

public class Task05 {
    public static void main(String[] args) { SpringApplication.run(Task05.class, args); }
}
