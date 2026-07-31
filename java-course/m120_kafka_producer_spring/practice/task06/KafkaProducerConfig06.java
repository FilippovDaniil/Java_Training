package m120_kafka_producer_spring.practice.task06;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * Ручная конфигурация двух продюсеров с разными гарантиями.
 *
 * TODO 1: props(...) — общая карта настроек:
 *           BOOTSTRAP_SERVERS_CONFIG   = "localhost:29092"
 *           KEY_SERIALIZER_CLASS_CONFIG   = StringSerializer.class
 *           VALUE_SERIALIZER_CLASS_CONFIG = JsonSerializer.class
 * TODO 2: reliableProducerFactory — добавить: acks=all, enable.idempotence=true,
 *           max.in.flight.requests.per.connection=5, delivery.timeout.ms=120000,
 *           compression.type=lz4, linger.ms=10.
 * TODO 3: fastProducerFactory — acks=0, enable.idempotence=false, linger.ms=0.
 * TODO 4: два бина KafkaTemplate поверх фабрик; @Primary — на reliableTemplate,
 *           чтобы «по умолчанию» приложение брало надёжный вариант.
 *
 * ВАЖНО: ProducerFactory потокобезопасна и держит пул продюсеров — создавайте её один раз
 *   как бин, а не на каждый вызов.
 */
@Configuration
public class KafkaProducerConfig06 {

    // TODO 1: private Map<String, Object> props() { ... }

    @Bean
    ProducerFactory<String, Object> reliableProducerFactory() {
        // TODO 2: DefaultKafkaProducerFactory с надёжными настройками
        return null;
    }

    @Bean
    ProducerFactory<String, Object> fastProducerFactory() {
        // TODO 3: DefaultKafkaProducerFactory с «быстрыми» настройками
        return null;
    }

    @Bean
    @Primary
    KafkaTemplate<String, Object> reliableTemplate(ProducerFactory<String, Object> reliableProducerFactory) {
        // TODO 4: new KafkaTemplate<>(reliableProducerFactory)
        return null;
    }

    @Bean
    KafkaTemplate<String, Object> fastTemplate(ProducerFactory<String, Object> fastProducerFactory) {
        // TODO 4: new KafkaTemplate<>(fastProducerFactory)
        return null;
    }
}
