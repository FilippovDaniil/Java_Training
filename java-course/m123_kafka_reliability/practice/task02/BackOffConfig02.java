package m123_kafka_reliability.practice.task02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.apache.kafka.common.KafkaException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries;
import org.springframework.util.backoff.ExponentialBackOff;

/**
 * Экспоненциальные повторы + классификация исключений.
 *
 * TODO 1: ExponentialBackOffWithMaxRetries(3): initialInterval 1000, multiplier 2.0,
 *         maxInterval 10_000.
 * TODO 2: DefaultErrorHandler с этим backOff.
 * TODO 3: handler.addNotRetryableExceptions(IllegalArgumentException.class).
 * TODO 4 (сравнение): вызвать handler.setLogLevel(KafkaException.Level.WARN) и посмотреть,
 *         как меняется шум в логах при повторах.
 *
 * ПОДСКАЗКА: ExponentialBackOffWithMaxRetries(n) задаёт ЧИСЛО ПОВТОРОВ, тогда как обычный
 *   ExponentialBackOff по умолчанию повторяет бесконечно — легко получить «вечный» цикл.
 */
@Configuration
public class BackOffConfig02 {

    @Bean
    DefaultErrorHandler errorHandler() {
        ExponentialBackOffWithMaxRetries backOff = new ExponentialBackOffWithMaxRetries(3);
        backOff.setInitialInterval(1000L);   // 1 сек
        backOff.setMultiplier(2.0);          // 2x
        backOff.setMaxInterval(10_000L);     // не больше 10 сек

        DefaultErrorHandler handler = new DefaultErrorHandler(backOff);
        handler.addNotRetryableExceptions(IllegalArgumentException.class);

        handler.setRetryListeners((record, ex, deliveryAttempt) ->
                System.out.printf("retry #%d для value=%s причина: %s%n",
                        deliveryAttempt, record.value(), ex.getMessage()));

        return handler;
    }
}