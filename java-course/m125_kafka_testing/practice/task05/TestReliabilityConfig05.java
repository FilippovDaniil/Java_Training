package m125_kafka_testing.practice.task05;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

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

    /**
     * TODO 1: recoverer публикует в "<исходный топик>.DLT" — это дефолтное поведение
     * DeadLetterPublishingRecoverer (суффикс ".DLT"), поэтому отдельный DestinationResolver
     * не нужен.
     */
    @Bean
    DeadLetterPublishingRecoverer recoverer(KafkaTemplate<String, Object> template) {
        return new DeadLetterPublishingRecoverer(template);
    }

    /**
     * TODO 2-4: интервалы и число повторов берём из свойств, чтобы прод и тест
     * различались только конфигурацией, а не кодом.
     */
    @Bean
    DefaultErrorHandler errorHandler(DeadLetterPublishingRecoverer recoverer,
                                     @Value("${app.kafka.retry.interval-ms:50}") long intervalMs,
                                     @Value("${app.kafka.retry.max-retries:2}") long maxRetries) {
        FixedBackOff backOff = new FixedBackOff(intervalMs, maxRetries);
        DefaultErrorHandler handler = new DefaultErrorHandler(recoverer, backOff);
        handler.addNotRetryableExceptions(IllegalArgumentException.class);
        return handler;
    }
}

