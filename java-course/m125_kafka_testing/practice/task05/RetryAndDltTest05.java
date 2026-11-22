package m125_kafka_testing.practice.task05;

/**
 * Задача 05 — Модуль 125: тесты ретраев и DLQ (короткий BackOff, консьюмер на DLT)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-kafka, spring-kafka-test, spring-boot-starter-test, awaitility.
 * Тест-класс без main. Брокер встроенный (@EmbeddedKafka).
 *
 * ФАЙЛЫ ЗАДАЧИ: RetryAndDltTest05.java (тест), Task05.java (тестовое приложение),
 *               FailingListener05.java (слушатель со счётчиком попыток),
 *               TestReliabilityConfig05.java (быстрый BackOff + DLQ для тестов).
 *
 * ЗАДАНИЕ:
 *   1) TestReliabilityConfig05: DefaultErrorHandler с FixedBackOff(50L, 2L) — 3 попытки почти без
 *      задержек — и DeadLetterPublishingRecoverer в "<топик>.DLT".
 *   2) retries_configured_number_of_times(): отправить сообщение, на котором слушатель падает;
 *      дождаться, что счётчик попыток стал 3 (Awaitility), а не 1 и не бесконечность.
 *   3) failed_event_lands_in_dlt(): тестовым консьюмером прочитать "task.created.DLT" и проверить:
 *        - значение совпадает с исходным;
 *        - заголовок kafka_dlt-exception-message содержит текст исключения;
 *        - заголовок kafka_dlt-original-topic = "task.created".
 *   4) not_retryable_exception_goes_straight_to_dlt(): для IllegalArgumentException (добавьте её
 *      в notRetryable) попыток должно быть РОВНО 1, а запись — сразу в DLT.
 *   5) successful_event_never_reaches_dlt(): нормальное сообщение обработано, в DLT ничего нет.
 *
 * ЦЕЛЬ: зафиксировать тестами политику надёжности из модуля 123 — иначе она «уплывёт» при
 *       первом же рефакторинге конфигурации.
 *
 * ВАЖНО: в тестовом профиле интервалы ретраев должны быть минимальными (50 мс). С боевым
 *   ExponentialBackOff (1с/2с/4с) один такой тест будет идти 7+ секунд, а набор — минуты.
 *   Значение backoff берите из свойств, чтобы прод и тест отличались только конфигурацией.
 *
 * ПОДСКАЗКА: число попыток удобно считать в самом слушателе (AtomicInteger) — это наблюдаемое
 *   состояние, по которому тест делает утверждения без чтения логов.
 */

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.time.Duration;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest(classes = Task05.class)
@EmbeddedKafka(topics = {"task.created", "task.created.DLT"}, partitions = 1)
class RetryAndDltTest05 {

    private static final String TOPIC = "task.created";
    private static final String DLT = "task.created.DLT";

    @Autowired
    EmbeddedKafkaBroker broker;

    @Autowired
    KafkaTemplate<String, Object> kafka;

    @Autowired
    FailingListener05 listener;

    private Consumer<String, Object> consumer;

    @BeforeEach
    void setUp() {
        Map<String, Object> props = KafkaTestUtils.consumerProps("dlt-consumer", "true", broker);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumer = new DefaultKafkaConsumerFactory<String, Object>(props).createConsumer();
        broker.consumeFromAnEmbeddedTopic(consumer, DLT);
    }

    @AfterEach
    void tearDown() {
        if (consumer != null) {
            consumer.close();
        }
    }

    @Test
    void retries_configured_number_of_times() {
        kafka.send(TOPIC, "bad");

        // 1 первая попытка + 2 повтора = 3 попытки, не 1 и не бесконечность
        await().atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> assertThat(listener.attempts()).isEqualTo(3));
    }

    @Test
    void failed_event_lands_in_dlt() {
        kafka.send(TOPIC, "bad");

        ConsumerRecord<String, Object> dltRecord =
                KafkaTestUtils.getSingleRecord(consumer, DLT, Duration.ofSeconds(10));

        assertThat(dltRecord.value()).isEqualTo("bad");
        assertThat(new String(dltRecord.headers().lastHeader("kafka_dlt-exception-message").value()))
                .contains("не могу обработать");
        assertThat(new String(dltRecord.headers().lastHeader("kafka_dlt-original-topic").value()))
                .isEqualTo(TOPIC);
    }

    @Test
    void not_retryable_exception_goes_straight_to_dlt() {
        kafka.send(TOPIC, "invalid");

        // не повторяемое исключение → ровно 1 попытка
        await().atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> assertThat(listener.attempts()).isEqualTo(1));

        // и запись сразу в DLT
        ConsumerRecord<String, Object> dltRecord =
                KafkaTestUtils.getSingleRecord(consumer, DLT, Duration.ofSeconds(10));
        assertThat(dltRecord.value()).isEqualTo("invalid");
        assertThat(new String(dltRecord.headers().lastHeader("kafka_dlt-exception-message").value()))
                .contains("некорректные данные");
    }

    @Test
    void successful_event_never_reaches_dlt() {
        kafka.send(TOPIC, "ok");

        await().atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> assertThat(listener.processed()).isEqualTo(1));

        // в DLT ничего не должно попасть
        assertThat(KafkaTestUtils.getRecords(consumer, Duration.ofSeconds(2))).isEmpty();
    }
}
