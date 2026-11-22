package m125_kafka_testing.practice.task03;

/**
 * Задача 03 — Модуль 125: @EmbeddedKafka — событие доходит до слушателя (Awaitility)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-kafka, spring-kafka-test, spring-boot-starter-test, awaitility.
 * Тест-класс без main. Внешний брокер НЕ нужен — Kafka поднимается в JVM теста.
 *
 * ФАЙЛЫ ЗАДАЧИ: EmbeddedKafkaTest03.java (тест), Task03.java (тестовое приложение),
 *               TaskListener03.java (слушатель со счётчиком).
 *
 * ЗАДАНИЕ:
 *   1) Аннотации теста:
 *        @SpringBootTest(classes = Task03.class)
 *        @EmbeddedKafka(partitions = 3, topics = {"task.created"},
 *                       brokerProperties = {"listeners=PLAINTEXT://localhost:0"})
 *        @TestPropertySource(properties = {
 *            "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
 *            "spring.kafka.consumer.auto-offset-reset=earliest",
 *            "spring.kafka.consumer.group-id=test-group"})
 *   2) event_reaches_listener(): отправить событие через KafkaTemplate и дождаться эффекта:
 *        await().atMost(Duration.ofSeconds(10))
 *               .untilAsserted(() -> assertThat(listener.received()).isEqualTo(1));
 *   3) key_defines_partition(): отправить 3 события с одним ключом и проверить, что слушатель
 *      увидел их все в исходном порядке (сохраняйте порядок в списке внутри слушателя).
 *   4) waits_for_partition_assignment(): перед отправкой дождитесь назначения партиций
 *        ContainerTestUtils.waitForAssignment(
 *            registry.getListenerContainer("task-listener"), 3);
 *      и объясните в комментарии, какую «мигающую» ошибку это устраняет.
 *
 * ЦЕЛЬ: проверить реальный путь publish -> брокер -> десериализация -> слушатель, не поднимая
 *       Docker и не завися от внешнего окружения.
 *
 * ВАЖНО: `listeners=PLAINTEXT://localhost:0` — случайный порт: иначе параллельные тестовые
 *   классы конфликтуют за 9092. Адрес встроенного брокера подставляется через
 *   ${spring.embedded.kafka.brokers} — без этого приложение пойдёт на localhost:9092.
 *
 * ПОДСКАЗКА: Thread.sleep в таких тестах запрещён: на CI он либо не хватает, либо тратит время.
 *   Ждите условие (Awaitility) или назначение партиций (ContainerTestUtils).
 */

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.TestPropertySource;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest(classes = Task03.class)
@EmbeddedKafka(
        partitions = 3,
        topics = {"task.created"},
        brokerProperties = {"listeners=PLAINTEXT://localhost:0"})
@TestPropertySource(properties = {
        "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
        "spring.kafka.consumer.auto-offset-reset=earliest",
        "spring.kafka.consumer.group-id=test-group"})
class EmbeddedKafkaTest03 {

    @Autowired
    KafkaTemplate<String, String> kafka;

    @Autowired
    TaskListener03 listener;

    @Autowired
    KafkaListenerEndpointRegistry registry;

    // ------------------------------------------------------------------
    // TODO 2
    // ------------------------------------------------------------------
    @Test
    void event_reaches_listener() {
        kafka.send("task.created", "k1", "hello-embedded");

        await().atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> assertThat(listener.received()).isEqualTo(1));
        assertThat(listener.values()).containsExactly("hello-embedded");
    }

    // ------------------------------------------------------------------
    // TODO 3
    // ------------------------------------------------------------------
    @Test
    void key_defines_partition() {
        // один ключ → одна партиция → гарантированный порядок
        String key = "order-42";
        kafka.send("task.created", key, "v1");
        kafka.send("task.created", key, "v2");
        kafka.send("task.created", key, "v3");

        await().atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> assertThat(listener.values()).hasSize(3));

        // все события с одним ключом обязаны прийти в исходном порядке
        assertThat(listener.values()).containsExactly("v1", "v2", "v3");
    }

    // ------------------------------------------------------------------
    // TODO 4
    // ------------------------------------------------------------------
    @Test
    void waits_for_partition_assignment() {
        // Ждём, пока контейнер получит назначение всех 3 партиций.
        // Без этого можно отправить событие в партицию, которую consumer ещё не «прихватил»,
        // и тест начнёт «мигать»: иногда событие доходит, иногда нет — в зависимости от гонки
        // между subscribe() и фактическим назначением партиций (rebalance).
        ContainerTestUtils.waitForAssignment(
                registry.getListenerContainer("task-listener"), 3);

        kafka.send("task.created", "k1", "after-assignment");

        await().atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> assertThat(listener.values())
                        .contains("after-assignment"));
    }
}
