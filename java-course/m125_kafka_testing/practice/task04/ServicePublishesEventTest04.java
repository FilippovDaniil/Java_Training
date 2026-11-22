package m125_kafka_testing.practice.task04;

/**
 * Задача 04 — Модуль 125: KafkaTestUtils — проверяем, что сервис действительно публикует событие
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-kafka, spring-kafka-test, spring-boot-starter-test.
 * Тест-класс без main. Брокер встроенный (@EmbeddedKafka).
 *
 * ФАЙЛЫ ЗАДАЧИ: ServicePublishesEventTest04.java (тест), Task04.java (тестовое приложение),
 *               TaskService04.java (сервис, который должен опубликовать событие).
 *
 * ЗАДАНИЕ:
 *   1) Поднимите @EmbeddedKafka(topics = {"task.created"}, partitions = 3) и внедрите
 *      EmbeddedKafkaBroker (@Autowired).
 *   2) Создайте тестовый консьюмер:
 *        Map<String, Object> props = KafkaTestUtils.consumerProps("test-consumer", "true", broker);
 *        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
 *        Consumer<String, String> consumer =
 *            new DefaultKafkaConsumerFactory<String, String>(props).createConsumer();
 *        broker.consumeFromAnEmbeddedTopic(consumer, "task.created");
 *   3) service_publishes_event_with_task_id_as_key():
 *        - вызвать service.createTask("Отчёт", 10);
 *        - record = KafkaTestUtils.getSingleRecord(consumer, "task.created", Duration.ofSeconds(10));
 *        - проверить ключ (id задачи), наличие "Отчёт" в значении и заголовок eventId.
 *   4) publishes_nothing_when_validation_fails(): при пустом title сервис бросает исключение
 *      и НЕ публикует событие — проверьте, что записей в топике нет
 *      (KafkaTestUtils.getRecords(consumer, Duration.ofSeconds(2)).isEmpty()).
 *   5) Закрывайте консьюмер (try-with-resources или @AfterEach) — иначе тесты «текут».
 *
 * ЦЕЛЬ: тестировать наблюдаемое поведение сервиса «снаружи»: в топике появилось правильное
 *       сообщение. Это то, на что опираются другие команды-подписчики.
 *
 * ВАЖНО: пункт 4 не менее важен, чем 3: сервис не должен публиковать событие о том, чего
 *   не произошло. Такие «фантомные» события ломают подписчиков тише всего.
 *
 * ПОДСКАЗКА: getSingleRecord падает по таймауту, если записи нет, — это и есть проверка
 *   «событие опубликовано»; отдельный assertNotNull не нужен.
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
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.time.Duration;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = Task04.class)
@EmbeddedKafka(topics = {"task.created"}, partitions = 3)
class ServicePublishesEventTest04 {

    private static final String TOPIC = "task.created";

    @Autowired
    EmbeddedKafkaBroker broker;

    @Autowired
    TaskService04 service;

    private Consumer<String, String> consumer;

    @BeforeEach
    void setUp() {
        Map<String, Object> props = KafkaTestUtils.consumerProps("test-consumer", "true", broker);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumer = new DefaultKafkaConsumerFactory<String, String>(props).createConsumer();
        broker.consumeFromAnEmbeddedTopic(consumer, TOPIC);
    }

    @AfterEach
    void tearDown() {
        if (consumer != null) {
            consumer.close();
        }
    }

    @Test
    void service_publishes_event_with_task_id_as_key() {
        long id = service.createTask("Отчёт", 10);

        ConsumerRecord<String, String> record =
                KafkaTestUtils.getSingleRecord(consumer, TOPIC, Duration.ofSeconds(10));

        // ключ — id задачи (обеспечивает порядок по партиции)
        assertThat(record.key()).isEqualTo(String.valueOf(id));
        // полезная нагрузка содержит заголовок задачи
        assertThat(record.value()).contains("Отчёт");
        // заголовок eventId присутствует
        assertThat(record.headers().lastHeader("eventId")).isNotNull();
        assertThat(new String(record.headers().lastHeader("eventId").value())).isNotBlank();
    }

    @Test
    void publishes_nothing_when_validation_fails() {
        assertThatThrownBy(() -> service.createTask("", 10))
                .isInstanceOf(IllegalArgumentException.class);

        // сервис не должен публиковать событие о том, чего не произошло
        assertThat(KafkaTestUtils.getRecords(consumer, Duration.ofSeconds(2))).isEmpty();
    }
}
