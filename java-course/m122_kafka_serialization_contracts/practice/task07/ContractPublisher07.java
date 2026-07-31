package m122_kafka_serialization_contracts.practice.task07;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

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
    static final boolean DUAL_PUBLISH = true;   // TODO 4: false, когда миграция завершена

    private final KafkaTemplate<String, Object> kafka;

    public ContractPublisher07(KafkaTemplate<String, Object> kafka) {
        this.kafka = kafka;
    }

    public void publishV1(TaskCreatedV107 payload) {
        // TODO 1
    }

    public void publishV2(TaskCreatedV207 payload) {
        // TODO 2
    }

    public void publishBreakingV2(TaskCreatedV207 payload) {
        // TODO 3 (+ учесть DUAL_PUBLISH)
    }
}
