package m125_kafka_testing.practice.task07;

/**
 * Задача 07 — Модуль 125: МИНИ-ПРОЕКТ «Тестовый набор событийного сервиса»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-kafka, spring-kafka-test, spring-boot-starter-test,
 *   spring-boot-starter-data-jpa, h2, awaitility, testcontainers (kafka, junit-jupiter).
 * Тест-классы без main. Запуск в IDE (▶) или ./gradlew test.
 *
 * ФАЙЛЫ ЗАДАЧИ:
 *   EventFlowTestSuite07.java  — карта набора тестов + сквозной интеграционный тест контура
 *   Task07.java                — тестовое приложение (outbox + релэй + проектор)
 *   OutboxFlowUnitTest07.java  — unit-уровень: команда пишет в outbox, релэй отправляет
 *   ContractApprovalTest07.java— контрактный (approval) тест JSON события
 *
 * ЦЕЛЬ: покрыть тестами весь контур из модуля 124 (команда -> outbox -> релэй -> Kafka ->
 *       проекция) на трёх уровнях и зафиксировать контракт события. Капстоун модуля.
 *
 * КАРТА НАБОРА (что каким уровнем проверяем):
 *   | Проверка                                            | Уровень              |
 *   |-----------------------------------------------------|----------------------|
 *   | команда пишет задачу и запись outbox одной транзакцией | unit + @DataJpaTest |
 *   | откат транзакции не оставляет запись в outbox        | unit + @DataJpaTest  |
 *   | релэй отправляет неотправленные и помечает sentAt    | @EmbeddedKafka       |
 *   | релэй не теряет записи при недоступном брокере       | @EmbeddedKafka       |
 *   | проектор идемпотентен (повтор события)               | unit                 |
 *   | проекция пересобирается с начала топика              | @EmbeddedKafka       |
 *   | порядок событий одной задачи сохранён                | Testcontainers       |
 *   | контракт JSON события не изменился                   | approval-тест        |
 *
 * ЗАДАНИЕ:
 *   1) Реализуйте сквозной тест в этом классе (@SpringBootTest + @EmbeddedKafka):
 *        - вызвать команду создания задачи;
 *        - дождаться (Awaitility), что outboxUnsent == 0 (релэй отправил);
 *        - дождаться, что в проекции появилась строка с нужным статусом;
 *        - проверить, что событие в топике имеет ключ = id задачи.
 *   2) Реализуйте OutboxFlowUnitTest07 (unit/JPA-уровень) по карте выше.
 *   3) Реализуйте ContractApprovalTest07 — «золотой» JSON события.
 *   4) Добавьте тест «повторная доставка события не меняет проекцию» (идемпотентность).
 *   5) Разделите наборы: обычные тесты (без Docker) и @Tag("integration") для Testcontainers;
 *      опишите в комментарии, как это настроить в Gradle (task integrationTest + useJUnitPlatform
 *      с includeTags/excludeTags).
 *
 * ОЖИДАЕМЫЙ ИТОГ: набор, который ловит реальные регрессии событийного сервиса — потерю события,
 *   дубль эффекта, сломанный контракт, нарушенный порядок, — и при этом быстро выполняется
 *   в обычной сборке.
 *
 * ВАЖНО: тест сквозного контура ОБЯЗАТЕЛЬНО ждёт условия (Awaitility), а не «секунду на всё».
 *   И проверяет эффект (строка в проекции), а не факт вызова слушателя: вызов без результата
 *   ничего не гарантирует.
 *
 * ПОДСКАЗКА: если сквозной тест стабильно «мигает» — почти всегда причина в том, что события
 *   отправлены до назначения партиций слушателю. Лечение: earliest + waitForAssignment.
 */

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.TestPropertySource;

import java.time.Duration;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest(classes = Task07.class)
@EmbeddedKafka(topics = {"task.created"}, partitions = 3)
@TestPropertySource(properties = {
        "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
        "spring.kafka.consumer.auto-offset-reset=earliest",
        "app.outbox.relay.delay=100"})
class EventFlowTestSuite07 {

    private static final String TOPIC = "task.created";

    @Autowired CreateTaskCommand command;
    @Autowired OutboxRepository outbox;
    @Autowired TaskProjectionRepository projection;
    @Autowired KafkaListenerEndpointRegistry registry;
    @Autowired EmbeddedKafkaBroker broker;

    private Consumer<String, String> consumer;

    @BeforeEach
    void setUp() {
        // ждём назначения партиций — иначе «мигающие» падения
        ContainerTestUtils.waitForAssignment(
                registry.getListenerContainer("task-projection-listener"), 3);

        Map<String, Object> props = KafkaTestUtils.consumerProps("e2e-consumer", "true", broker);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumer = new DefaultKafkaConsumerFactory<String, String>(props).createConsumer();
        broker.consumeFromAnEmbeddedTopic(consumer, TOPIC);
    }

    @AfterEach
    void tearDown() {
        if (consumer != null) consumer.close();
    }

    @Test
    void command_to_projection_end_to_end() {
        long id = command.create("Отчёт", 10);

        // 1) релэй отправил всё из outbox
        await().atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> assertThat(outbox.countBySentAtIsNull()).isZero());

        // 2) проекция получила строку с нужным статусом — проверяем ЭФФЕКТ, не вызов
        await().atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> {
                    var p = projection.findById(id);
                    assertThat(p).isPresent();
                    assertThat(p.get().getStatus()).isEqualTo("CREATED");
                });

        // 3) событие в топике имеет ключ = id задачи
        ConsumerRecord<String, String> record =
                KafkaTestUtils.getSingleRecord(consumer, TOPIC, Duration.ofSeconds(10));
        assertThat(record.key()).isEqualTo(String.valueOf(id));
    }

    @Test
    void duplicate_delivery_does_not_change_projection() {
        long id = command.create("Отчёт", 10);

        await().atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> assertThat(projection.findById(id)).isPresent());

        var before = projection.findById(id).orElseThrow();

        // повторная доставка того же события — проектор идемпотентен
        ConsumerRecord<String, String> record =
                KafkaTestUtils.getSingleRecord(consumer, TOPIC, Duration.ofSeconds(10));
        // отправляем то же событие ещё раз вручную
        // (в реальности это сделал бы брокер при at-least-once)
        var kafka = new org.springframework.kafka.core.KafkaTemplate<String, String>(
                new org.springframework.kafka.core.DefaultKafkaProducerFactory<>(
                        KafkaTestUtils.producerProps(broker)));
        kafka.send(TOPIC, record.key(), record.value());

        // ждём «тишины» и проверяем, что проекция не изменилась
        await().during(Duration.ofSeconds(2))
                .atMost(Duration.ofSeconds(5))
                .untilAsserted(() -> {
                    var after = projection.findById(id).orElseThrow();
                    assertThat(after.getStatus()).isEqualTo(before.getStatus());
                    assertThat(projection.count()).isEqualTo(1); // нет второй строки
                });
    }
}