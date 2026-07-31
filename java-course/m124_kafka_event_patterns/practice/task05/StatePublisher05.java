package m124_kafka_event_patterns.practice.task05;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Публикация состояния сущности в compacted-топик.
 *
 * TODO 1: publishState(String taskId, String stateJson) — send(TOPIC, taskId, stateJson).
 * TODO 2: deleteState(String taskId) — send(TOPIC, taskId, null): tombstone.
 * TODO 3: NewTopic-бин для task.state с cleanup.policy=compact (TopicConfig.CLEANUP_POLICY_CONFIG,
 *         значение TopicConfig.CLEANUP_POLICY_COMPACT).
 *
 * ВАЖНО: ключ обязателен всегда. Запись в compacted-топик без ключа брокер отвергнет
 *   (или она никогда не будет уплотнена) — compaction работает исключительно по ключу.
 */
@Service
public class StatePublisher05 {

    static final String TOPIC = "task.state";

    private final KafkaTemplate<String, String> kafka;

    public StatePublisher05(KafkaTemplate<String, String> kafka) {
        this.kafka = kafka;
    }

    public void publishState(String taskId, String stateJson) {
        // TODO 1
    }

    public void deleteState(String taskId) {
        // TODO 2
    }
}
