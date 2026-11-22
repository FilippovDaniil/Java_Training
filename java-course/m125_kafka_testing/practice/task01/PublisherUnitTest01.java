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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PublisherUnitTest01 {

    @Mock
    KafkaTemplate<String, Object> kafka;

    @InjectMocks
    TaskEventPublisher01 publisher;

    @Test
    void publishes_to_correct_topic_with_task_id_as_key() {
        // given: мок send возвращает завершённый future (иначе NPE на whenComplete)
        given(kafka.send(any(), any(), any()))
                .willReturn(CompletableFuture.completedFuture(null));

        TaskCreated01 event = new TaskCreated01(42, "Отчёт", 10, Instant.now());

        // when
        publisher.publishCreated(event);

        // then: проверяем контракт — топик, ключ, содержимое
        ArgumentCaptor<String> topicCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> keyCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> valueCaptor = ArgumentCaptor.forClass(Object.class);

        verify(kafka).send(topicCaptor.capture(), keyCaptor.capture(), valueCaptor.capture());

        assertThat(topicCaptor.getValue()).isEqualTo("task.created");
        assertThat(keyCaptor.getValue()).isEqualTo("42");
        assertThat(valueCaptor.getValue()).isInstanceOf(TaskCreated01.class);
        assertThat(((TaskCreated01) valueCaptor.getValue()).title()).isEqualTo("Отчёт");
    }

    @Test
    void does_not_swallow_send_failure() {
        // given: брокер недоступен — future завершается ошибкой
        given(kafka.send(any(), any(), any()))
                .willReturn(CompletableFuture.failedFuture(
                        new RuntimeException("брокер недоступен")));

        // when
        publisher.publishCreated(new TaskCreated01(42, "Отчёт", 10, Instant.now()));

        // then: сбой зафиксирован, а не проглочен
        assertThat(publisher.failures()).isEqualTo(1);
    }

    @Test
    void uses_null_key_when_order_not_required() {
        // given
        given(kafka.send(any(), any(), any()))
                .willReturn(CompletableFuture.completedFuture(null));

        Object metric = new Object();

        // when
        publisher.publishMetric(metric);

        // then: топик app.metrics, ключ null
        ArgumentCaptor<String> topicCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> keyCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> valueCaptor = ArgumentCaptor.forClass(Object.class);

        verify(kafka).send(topicCaptor.capture(), keyCaptor.capture(), valueCaptor.capture());

        assertThat(topicCaptor.getValue()).isEqualTo("app.metrics");
        assertThat(keyCaptor.getValue()).isNull();
        assertThat(valueCaptor.getValue()).isSameAs(metric);
    }
}
