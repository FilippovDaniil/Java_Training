package m120_kafka_producer_spring.practice.task06;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

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

    private Map<String, Object> props() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put("spring.json.add.type.headers", false);
        return props;
    }

    @Bean
    ProducerFactory<String, Object> reliableProducerFactory() {
        Map<String, Object> props = props();
        props.put(ProducerConfig.ACKS_CONFIG, "all");                           // ждём ack от всех ISR
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);              // без дублей при ретраях
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 5);     // идемпотентность допускает до 5
        props.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, 120_000);
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "lz4");               // быстрый и эффективный
        props.put(ProducerConfig.LINGER_MS_CONFIG, 10);                         // лёгкий батчинг
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    ProducerFactory<String, Object> fastProducerFactory() {
        Map<String, Object> props = props();
        props.put(ProducerConfig.ACKS_CONFIG, "0");                             // не ждём ack
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, false);             // несовместимо с acks=0
        props.put(ProducerConfig.LINGER_MS_CONFIG, 0);                          // без задержки
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    @Primary
    KafkaTemplate<String, Object> reliableTemplate(ProducerFactory<String, Object> reliableProducerFactory) {
        return new KafkaTemplate<>(reliableProducerFactory);
    }

    @Bean
    KafkaTemplate<String, Object> fastTemplate(ProducerFactory<String, Object> fastProducerFactory) {
        return new KafkaTemplate<>(fastProducerFactory);
    }
}
