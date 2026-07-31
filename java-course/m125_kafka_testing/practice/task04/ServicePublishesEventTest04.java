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

import org.junit.jupiter.api.Test;

class ServicePublishesEventTest04 {

    @Test
    void service_publishes_event_with_task_id_as_key() {
        // TODO 3
    }

    @Test
    void publishes_nothing_when_validation_fails() {
        // TODO 4
    }
}
