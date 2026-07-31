package m123_kafka_reliability.practice.task07;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;

/**
 * Политика надёжности приложения в одном месте.
 *
 * TODO 1: recoverer -> "<исходный топик>.DLT" (партиция -1).
 * TODO 2: ExponentialBackOffWithMaxRetries(3): initialInterval 1000, multiplier 2.0, maxInterval 10_000.
 * TODO 3: DefaultErrorHandler(recoverer, backOff) + addNotRetryableExceptions(IllegalArgumentException.class).
 * TODO 4: retry-listener для наблюдаемости: логировать номер попытки, топик, ключ, причину —
 *         без этого в проде невозможно понять, что именно повторялось.
 *
 * ПОЧЕМУ ЭТО ОДИН БИН: политика повторов и DLQ — сквозное решение уровня сервиса. Разные
 *   политики на разные слушатели задают отдельными контейнер-фабриками, а не копипастой.
 */
@Configuration
public class ReliabilityConfig07 {

    @Bean
    DeadLetterPublishingRecoverer recoverer(KafkaTemplate<String, Object> template) {
        // TODO 1
        return null;
    }

    @Bean
    DefaultErrorHandler errorHandler(DeadLetterPublishingRecoverer recoverer) {
        // TODO 2-4
        return null;
    }
}
