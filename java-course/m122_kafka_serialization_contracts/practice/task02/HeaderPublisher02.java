package m122_kafka_serialization_contracts.practice.task02;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Публикация с заголовками — через ProducerRecord (KafkaTemplate.send(topic, key, value)
 * заголовки задавать не умеет).
 *
 * TODO 1: собрать ProducerRecord<String, String>("task.created", key, json).
 * TODO 2: добавить заголовки eventId, eventType, version, traceId
 *         (record.headers().add(имя, значение.getBytes(StandardCharsets.UTF_8))).
 * TODO 3: kafka.send(record) + обработка результата; затем kafka.flush().
 *
 * АЛЬТЕРНАТИВА: MessageBuilder.withPayload(...).setHeader(...).build() и send(Message<?>) —
 *   удобнее, когда заголовков много и хочется декларативности.
 */
@Service
public class HeaderPublisher02 {

    private final KafkaTemplate<String, String> kafka;

    public HeaderPublisher02(KafkaTemplate<String, String> kafka) {
        this.kafka = kafka;
    }

    public void publish(String key, String json, String traceId) {
        // TODO 1-3
    }
}
