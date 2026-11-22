package m122_kafka_serialization_contracts.practice.task02;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
        ProducerRecord<String, String> record =
                new ProducerRecord<>("task.created", key, json);

        record.headers()
                .add("eventId", UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        record.headers()
                .add("eventType", "task.created".getBytes(StandardCharsets.UTF_8));
        record.headers()
                .add("version", "1".getBytes(StandardCharsets.UTF_8));
        record.headers()
                .add("traceId", traceId.getBytes(StandardCharsets.UTF_8));

        try {
            SendResult<String, String> result = kafka.send(record).get(5, TimeUnit.SECONDS);
            var md = result.getRecordMetadata();
            System.out.printf("отправлено с заголовками -> %s-%d offset %d%n",
                    md.topic(), md.partition(), md.offset());
        } catch (Exception e) {
            System.err.println("ошибка отправки: " + e.getMessage());
        }
    }
}
