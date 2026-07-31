package m125_kafka_testing.practice.task05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;

/**
 * Быстрая политика надёжности для тестов.
 *
 * TODO 1: DeadLetterPublishingRecoverer -> "<исходный топик>.DLT".
 * TODO 2: DefaultErrorHandler(recoverer, new FixedBackOff(50L, 2L)) — 3 попытки, паузы 50 мс.
 * TODO 3: addNotRetryableExceptions(IllegalArgumentException.class).
 * TODO 4: параметры backoff вынесите в @Value из свойств, чтобы прод и тест различались
 *         только значениями (application.yml vs application-test.yml), а не кодом.
 *
 * ПРИНЦИП: тест проверяет ПОВЕДЕНИЕ политики (число попыток, попадание в DLT), а не конкретные
 *   миллисекунды. Поэтому уменьшать интервалы в тестах — правильно, а «подгонять» ожидания
 *   под боевые паузы — нет.
 */
@Configuration
public class TestReliabilityConfig05 {

    @Bean
    DeadLetterPublishingRecoverer recoverer(KafkaTemplate<String, Object> template) {
        // TODO 1
        return null;
    }

    @Bean
    DefaultErrorHandler errorHandler(DeadLetterPublishingRecoverer recoverer) {
        // TODO 2-3
        return null;
    }
}
