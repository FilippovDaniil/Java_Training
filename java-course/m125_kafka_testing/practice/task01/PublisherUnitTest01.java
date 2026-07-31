package m125_kafka_testing.practice.task01;

/**
 * Задача 01 — Модуль 125: unit-тест продюсера (мок KafkaTemplate, ArgumentCaptor)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-kafka, spring-boot-starter-test (JUnit 5, AssertJ, Mockito).
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test. Брокер НЕ нужен.
 *
 * ФАЙЛЫ ЗАДАЧИ: PublisherUnitTest01.java (тест), TaskEventPublisher01.java (проверяемый класс),
 *               TaskCreated01.java (событие).
 *
 * ЗАДАНИЕ:
 *   1) Настройте тест: @ExtendWith(MockitoExtension.class), @Mock KafkaTemplate<String, Object>,
 *      @InjectMocks TaskEventPublisher01.
 *   2) publishes_to_correct_topic_with_task_id_as_key():
 *        - замокайте send: given(kafka.send(any(), any(), any()))
 *              .willReturn(CompletableFuture.completedFuture(null));
 *        - вызовите publisher.publishCreated(new TaskCreated01(42, "Отчёт", 10, Instant.now()));
 *        - через ArgumentCaptor проверьте: топик "task.created", ключ "42",
 *          в событии title = "Отчёт".
 *   3) does_not_swallow_send_failure():
 *        - верните CompletableFuture.failedFuture(new RuntimeException("брокер недоступен"));
 *        - убедитесь, что publisher зафиксировал сбой (счётчик ошибок/лог), а не «проглотил» его.
 *   4) uses_null_key_when_order_not_required(): проверьте вторую ветку API publisher'а.
 *
 * ЦЕЛЬ: проверять КОНТРАКТ публикации (топик + ключ + содержимое) без брокера — такие тесты
 *       идут в основной набор и выполняются за миллисекунды.
 *
 * ВАЖНО: замоканный send(), возвращающий null, — самая частая причина NPE в подобных тестах:
 *   реальный KafkaTemplate всегда возвращает CompletableFuture, и код вызывает у него whenComplete.
 *
 * ПОДСКАЗКА: ключ — это часть контракта (от него зависит порядок и партиция), поэтому проверка
 *   «отправлено хоть что-то» бесполезна; проверяйте именно ключ.
 */

import org.junit.jupiter.api.Test;

class PublisherUnitTest01 {

    @Test
    void publishes_to_correct_topic_with_task_id_as_key() {
        // TODO 2
    }

    @Test
    void does_not_swallow_send_failure() {
        // TODO 3
    }

    @Test
    void uses_null_key_when_order_not_required() {
        // TODO 4
    }
}
