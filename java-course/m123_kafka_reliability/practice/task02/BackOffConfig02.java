package m123_kafka_reliability.practice.task02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.DefaultErrorHandler;

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
        // TODO 1-3
        return null;
    }
}
