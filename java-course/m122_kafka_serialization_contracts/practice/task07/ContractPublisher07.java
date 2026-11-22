package m122_kafka_serialization_contracts.practice.task07;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.UUID;


/**
 * Публикация событий в трёх режимах: v1, совместимая v2, ломающая v2 с двойной публикацией.
 *
 * TODO 1: publishV1(TaskCreatedV107 payload) — конверт с version = 1, топик TOPIC_V1,
 *         ключ = taskId, заголовки eventId/eventType/version.
 * TODO 2: publishV2(TaskCreatedV207 payload) — конверт с version = 2, ТОТ ЖЕ топик TOPIC_V1
 *         (изменение совместимое, новый топик не нужен).
 * TODO 3: publishBreakingV2(...) — ломающее изменение: публикация в TOPIC_V2 и одновременно
 *         «переведённого» события в TOPIC_V1 (двойная публикация на время миграции).
 * TODO 4: константа DUAL_PUBLISH — флаг, отключающий публикацию в TOPIC_V1 одной правкой,
 *         когда все подписчики переехали.
 *
 * ПРАВИЛО: имена топиков и правила формирования ключа/заголовков живут ТОЛЬКО здесь.
 */
@Service
public class ContractPublisher07 {

    static final String TOPIC_V1 = "task.created";
    static final String TOPIC_V2 = "task.created.v2";
    static final boolean DUAL_PUBLISH = true;   // false, когда миграция завершена

    private final KafkaTemplate<String, Object> kafka;

    public ContractPublisher07(KafkaTemplate<String, Object> kafka) {
        this.kafka = kafka;
    }

    public void publishV1(TaskCreatedV107 payload) {
        EventEnvelope07 env = new EventEnvelope07(
                UUID.randomUUID(), "task.created", 1, Instant.now(),
                "trace-v1", "task-tracker", payload);
        send(TOPIC_V1, String.valueOf(payload.taskId()), env);
    }

    public void publishV2(TaskCreatedV207 payload) {
        EventEnvelope07 env = new EventEnvelope07(
                UUID.randomUUID(), "task.created", 2, Instant.now(),
                "trace-v2", "task-tracker", payload);
        send(TOPIC_V1, String.valueOf(payload.taskId()), env);   // тот же топик — совместимо
    }

    public void publishBreakingV2(TaskCreatedV207 payload) {
        EventEnvelope07 envV2 = new EventEnvelope07(
                UUID.randomUUID(), "task.created.v2", 2, Instant.now(),
                "trace-breaking", "task-tracker", payload);
        send(TOPIC_V2, String.valueOf(payload.taskId()), envV2);

        if (DUAL_PUBLISH) {
            // на время миграции дублируем в старый топик в v1-совместимом виде
            TaskCreatedV107 legacy = new TaskCreatedV107(
                    payload.taskId(), payload.title(), payload.authorId());
            EventEnvelope07 legacyEnv = new EventEnvelope07(
                    UUID.randomUUID(), "task.created", 1, Instant.now(),
                    "trace-breaking", "task-tracker", legacy);
            send(TOPIC_V1, String.valueOf(payload.taskId()), legacyEnv);
        }
    }

    private void send(String topic, String key, EventEnvelope07 env) {
        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, key, env);
        record.headers().add("eventId", env.eventId().toString().getBytes(StandardCharsets.UTF_8));
        record.headers().add("eventType", env.type().getBytes(StandardCharsets.UTF_8));
        record.headers().add("version", String.valueOf(env.version()).getBytes(StandardCharsets.UTF_8));
        record.headers().add("traceId", env.traceId().getBytes(StandardCharsets.UTF_8));

        kafka.send(record).whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("ошибка отправки: " + ex.getMessage());
                return;
            }
            var md = result.getRecordMetadata();
            System.out.printf("отправлено v%d -> %s-%d offset %d%n",
                    env.version(), md.topic(), md.partition(), md.offset());
        });
    }
}
