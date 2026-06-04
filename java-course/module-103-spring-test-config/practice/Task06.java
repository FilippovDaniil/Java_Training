/**
 * Задача 06 — Модуль 103: @DynamicPropertySource — свойства из рантайма
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Иногда значение свойства известно лишь в рантайме (порт встроенного сервиса, URL контейнера).
 *   Сымитируйте это: «внешний ресурс» FakeBroker06 (готов) сообщает динамический адрес.
 *     1) @SpringBootTest(classes = Task06.class);
 *     2) @DynamicPropertySource static void props(DynamicPropertyRegistry registry):
 *          registry.add("broker.url", FakeBroker06::url);   // ленивый Supplier!
 *     3) @Value("${broker.url}") String url;
 *     4) dynamic_prop_resolved(): assertThat(url).isEqualTo("tcp://localhost:61616").
 *
 * ЦЕЛЬ: подавать в контекст значение, вычисляемое в рантайме (паттерн Testcontainers, модуль 109).
 *
 * ВАЖНО: метод @DynamicPropertySource ДОЛЖЕН быть static; add принимает Supplier (ленивое значение).
 *
 * ПОДСКАЗКА: FakeBroker06.url() имитирует «адрес, известный только после старта брокера».
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootApplication
public class Task06 {
    public static void main(String[] args) { SpringApplication.run(Task06.class, args); }
}

// «Внешний ресурс»: адрес определяется в рантайме (имитация брокера/контейнера).
class FakeBroker06 {
    static String url() { return "tcp://localhost:61616"; }
}

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(classes = Task06.class)
class DynamicPropsTest06 {

    // TODO: @DynamicPropertySource
    // TODO: static void props(DynamicPropertyRegistry registry) {
    // TODO:     registry.add("broker.url", FakeBroker06::url);
    // TODO: }

    // TODO: @Value("${broker.url}") String url;

    @Test
    void dynamic_prop_resolved() {
        // TODO: assertThat(url).isEqualTo("tcp://localhost:61616");
    }
}
