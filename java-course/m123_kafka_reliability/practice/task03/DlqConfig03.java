package m123_kafka_reliability.practice.task03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;

/**
 * Конфигурация DLQ.
 *
 * TODO 1: DeadLetterPublishingRecoverer с резолвером назначения:
 *           (record, ex) -> new TopicPartition(record.topic() + ".DLT", -1)
 *         (-1 = пусть партицию выберет продюсер по ключу; сохранение исходной партиции
 *          требует, чтобы в DLT было столько же партиций).
 * TODO 2: DefaultErrorHandler(recoverer, new FixedBackOff(500L, 2L)).
 * TODO 3 (эксперимент): передать в recoverer резолвер, отправляющий ВСЕ ошибки в один топик
 *         "app.DLT", и обсудить в комментарии, когда это удобнее, чем DLT на каждый топик.
 *
 * ПОДСКАЗКА: recoverer публикует запись как есть (ключ, значение, заголовки) + добавляет
 *   заголовки kafka_dlt-*. Значит, «переиграть» её позже можно, просто отправив обратно
 *   в исходный топик.
 */
@Configuration
public class DlqConfig03 {

    @Bean
    DeadLetterPublishingRecoverer recoverer(KafkaTemplate<String, Object> template) {
        // TODO 1
        return null;
    }

    @Bean
    DefaultErrorHandler errorHandler(DeadLetterPublishingRecoverer recoverer) {
        // TODO 2
        return null;
    }
}
