package m123_kafka_reliability.practice.task01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.DefaultErrorHandler;

/**
 * Обработчик ошибок слушателя: сколько раз повторять и с какой паузой.
 *
 * TODO 1: вернуть new DefaultErrorHandler(new FixedBackOff(1000L, 3L)) —
 *         пауза 1 секунда, 3 повтора после первой неудачи.
 * TODO 2: включить логирование каждой попытки: handler.setRetryListeners(...) —
 *         печатать номер попытки и причину.
 * TODO 3 (наблюдение): поставить FixedBackOff.UNLIMITED_ATTEMPTS и убедиться, что «вечные»
 *         повторы останавливают обработку партиции. Вернуть 3 попытки.
 *
 * ВАЖНО: без recoverer'а (первый аргумент) исчерпавшая попытки запись просто логируется и
 *   пропускается — событие потеряно. В задаче 03 добавим DLQ, чтобы такие записи сохранялись.
 */
@Configuration
public class ErrorHandlerConfig01 {

    @Bean
    DefaultErrorHandler errorHandler() {
        // TODO 1-2
        return null;
    }
}
