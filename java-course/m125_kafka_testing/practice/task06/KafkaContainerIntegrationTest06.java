package m125_kafka_testing.practice.task06;

/**
 * Задача 06 — Модуль 125: Testcontainers — интеграция с настоящим брокером
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-kafka, spring-boot-starter-test, org.testcontainers:kafka,
 *   org.testcontainers:junit-jupiter, awaitility. НУЖЕН запущенный Docker (Rancher/Docker Desktop).
 * Тест-класс без main.
 *
 * ФАЙЛЫ ЗАДАЧИ: KafkaContainerIntegrationTest06.java (тест), Task06.java (тестовое приложение),
 *               OrderListener06.java (слушатель).
 *
 * ЗАДАНИЕ:
 *   1) Поднимите брокер контейнером:
 *        @Testcontainers
 *        @Container static final KafkaContainer kafka =
 *            new KafkaContainer(DockerImageName.parse("apache/kafka:3.9.0"));
 *        @DynamicPropertySource static void props(DynamicPropertyRegistry r) {
 *            r.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
 *            r.add("spring.kafka.consumer.auto-offset-reset", () -> "earliest");
 *        }
 *   2) end_to_end_publish_consume(): опубликовать событие и дождаться эффекта в слушателе.
 *   3) order_is_preserved_within_key(): отправить 20 событий с одним ключом и проверить, что
 *      слушатель получил их в том же порядке (сравнение списков).
 *   4) survives_broker_restart(): остановить и снова запустить контейнер брокера
 *      (kafka.stop() / kafka.start() или через Testcontainers API), убедиться, что после
 *      восстановления слушатель продолжает работу без потери накопленных событий.
 *      Если этот пункт нестабилен на вашей машине — зафиксируйте наблюдение в комментарии.
 *   5) Сравните время выполнения этого класса с задачей 03 (@EmbeddedKafka) и запишите вывод:
 *      какие проверки стоят Docker'а, а какие нет.
 *
 * ЦЕЛЬ: получить «последнюю милю» доверия — тест на том же брокере, что в проде, включая
 *       поведение при рестарте и настоящие конфиги топиков.
 *
 * ВАЖНО: контейнер объявляется static и переиспользуется всем классом; @Container на нестатическом
 *   поле поднимает НОВЫЙ брокер на каждый тест — набор тестов станет неприлично долгим.
 *
 * ПОДСКАЗКА: такие тесты выносят в отдельный набор (integrationTest / тег @Tag("integration")),
 *   чтобы обычная сборка не требовала Docker. В CI — отдельный шаг pipeline.
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@Testcontainers
@SpringBootTest(classes = Task06.class)
class KafkaContainerIntegrationTest06 {

    @Container
    static final KafkaContainer kafka =
            new KafkaContainer(DockerImageName.parse("apache/kafka:3.9.0"));

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry r) {
        r.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
        r.add("spring.kafka.consumer.auto-offset-reset", () -> "earliest");
    }

    @Autowired
    KafkaTemplate<String, String> template;

    @Autowired
    OrderListener06 listener;

    @Test
    void end_to_end_publish_consume() {
        template.send("task.created", "k1", "hello-container");

        await().atMost(Duration.ofSeconds(30))
                .untilAsserted(() -> assertThat(listener.values()).contains("hello-container"));
    }

    @Test
    void order_is_preserved_within_key() {
        String key = "order-42";
        List<String> sent = IntStream.rangeClosed(1, 20)
                .mapToObj(i -> "v" + i)
                .toList();

        sent.forEach(v -> template.send("task.created", key, v));

        await().atMost(Duration.ofSeconds(30))
                .untilAsserted(() -> assertThat(listener.values()).containsAll(sent));

        // все 20 с одним ключом пришли в исходном порядке
        assertThat(listener.values()).containsSubsequence(sent.toArray(String[]::new));
    }

    @Test
    void survives_broker_restart() {
        template.send("task.created", "k1", "before-restart");

        await().atMost(Duration.ofSeconds(30))
                .untilAsserted(() -> assertThat(listener.values()).contains("before-restart"));

        // перезапуск контейнера: Testcontainers сам поднимет его на том же порту
        kafka.stop();
        kafka.start();

        template.send("task.created", "k1", "after-restart");

        await().atMost(Duration.ofSeconds(60))
                .untilAsserted(() -> assertThat(listener.values()).contains("after-restart"));

        // события, накопленные до рестарта, не потеряны
        assertThat(listener.values()).contains("before-restart", "after-restart");
    }
}
