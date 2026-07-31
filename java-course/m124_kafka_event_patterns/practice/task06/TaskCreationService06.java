package m124_kafka_event_patterns.practice.task06;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Шаг 2 саги: создание задачи после успешного бронирования квоты.
 *
 * TODO 1: onQuotaReserved — создать задачу (в памяти достаточно):
 *           успех -> опубликовать "task.created", состояние саги = TASK_CREATED;
 *           сбой (эмулируйте по признаку в payload, например title содержит "fail")
 *              -> опубликовать "quota.release.requested", состояние саги = TASK_FAILED.
 * TODO 2: идемпотентность: если по sagaId задача уже создана — не создавать вторую.
 * TODO 3: хранить и печатать состояние саги (sagaId -> шаг) — RESERVE_REQUESTED,
 *         QUOTA_RESERVED, TASK_CREATED, TASK_FAILED, QUOTA_RELEASED, QUOTA_REJECTED.
 * TODO 4: слушатель "quota.rejected" — завершить сагу отказом (ничего компенсировать не нужно).
 *
 * ЗАЧЕМ СОСТОЯНИЕ САГИ: без него после рестарта неизвестно, какие шаги выполнены. В проде это
 *   таблица (saga_id, step, updated_at) + фоновая проверка «висящих» саг по таймауту.
 */
@Service
public class TaskCreationService06 {

    private final Map<String, String> sagaState = new ConcurrentHashMap<>();
    private final Map<String, Long> createdTaskBySaga = new ConcurrentHashMap<>();
    private final KafkaTemplate<String, String> kafka;

    public TaskCreationService06(KafkaTemplate<String, String> kafka) {
        this.kafka = kafka;
    }

    @KafkaListener(topics = SagaTopics06.RESERVED, groupId = "task-creation-service")
    public void onQuotaReserved(String payload) {
        // TODO 1-3
    }

    @KafkaListener(topics = SagaTopics06.REJECTED, groupId = "task-creation-service")
    public void onQuotaRejected(String payload) {
        // TODO 4
    }

    public Map<String, String> states() {
        return Map.copyOf(sagaState);
    }
}
