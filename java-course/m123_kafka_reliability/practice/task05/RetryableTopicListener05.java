package m123_kafka_reliability.practice.task05;

import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

/**
 * Слушатель с неблокирующими ретраями.
 *
 * TODO 1: добавить @RetryableTopic(attempts = "4", backoff = @Backoff(delay = 1000, multiplier = 2.0),
 *         autoCreateTopics = "true", dltStrategy = DltStrategy.FAIL_ON_ERROR).
 * TODO 2: в on(...) печатать «[{topic}] {value}» и падать на "bad".
 * TODO 3: добавить метод с @DltHandler: печатать «[DLT] {value}: {причина}»
 *         (причина — заголовок KafkaHeaders.DLT_EXCEPTION_MESSAGE).
 * TODO 4 (сравнение): включите одновременно бин DefaultErrorHandler из задачи 01 и посмотрите,
 *         какой механизм сработает (подсказка: @RetryableTopic перекрывает обработку для
 *         своего слушателя) — зафиксируйте вывод.
 *
 * ЗАЧЕМ ЗНАТЬ ОБА МЕХАНИЗМА: blocking retry — «повторяем здесь и сейчас, порядок дороже»;
 *   non-blocking — «убираем проблемную запись в сторону, пропускная способность дороже».
 *   Смешивать их в одном слушателе не нужно.
 */
@Component
public class RetryableTopicListener05 {

    @RetryableTopic(
            attempts = "4",                                 // 1 + 3 повтора
            backoff = @Backoff(delay = 1000, multiplier = 2.0),   // 1с, 2с, 4с
            autoCreateTopics = "true",
            dltStrategy = DltStrategy.FAIL_ON_ERROR)
    @KafkaListener(topics = "task.created", groupId = "retryable-topic-demo")
    public void on(String value,
                   @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        System.out.printf("[%s] %s%n", topic, value);

        if (value.contains("bad")) {
            throw new RuntimeException("не могу обработать " + value);
        }
        System.out.printf("[%s] %s -> обработано%n", topic, value);
    }

    @DltHandler
    public void onDlt(String value,
                      @Header(name = KafkaHeaders.DLT_EXCEPTION_MESSAGE, required = false) String message) {
        System.out.printf("[DLT] %s: %s%n", value, message);
    }
}
