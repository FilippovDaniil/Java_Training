package m124_kafka_event_patterns.practice.task06;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

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
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AtomicLong taskSeq = new AtomicLong();

    public TaskCreationService06(KafkaTemplate<String, String> kafka) {
        this.kafka = kafka;
    }

    @KafkaListener(topics = SagaTopics06.RESERVED, groupId = "task-creation-service")
    public void onQuotaReserved(String payload) throws Exception {
        JsonNode node = objectMapper.readTree(payload);
        String sagaId = node.get("sagaId").asText();

        sagaState.put(sagaId, "QUOTA_RESERVED");
        System.out.printf("saga %s: QUOTA_RESERVED%n", sagaId);

        // идемпотентность: задача уже создана — не создавать вторую
        if (createdTaskBySaga.containsKey(sagaId)) {
            System.out.printf("saga %s: задача уже создана, повторяем task.created%n", sagaId);
            kafka.send(SagaTopics06.TASK_CREATED, sagaId,
                    "{\"sagaId\":\"" + sagaId + "\",\"taskId\":" + createdTaskBySaga.get(sagaId) + "}");
            return;
        }

        // эмуляция сбоя: title содержит "fail"
        String title = node.has("title") ? node.get("title").asText() : "";
        if (title.contains("fail")) {
            sagaState.put(sagaId, "TASK_FAILED");
            System.out.printf("saga %s: TASK_FAILED -> компенсация%n", sagaId);
            kafka.send(SagaTopics06.RELEASE_REQUESTED, sagaId,
                    "{\"sagaId\":\"" + sagaId + "\",\"reason\":\"task creation failed\"}");
            return;
        }

        long taskId = taskSeq.incrementAndGet();
        createdTaskBySaga.put(sagaId, taskId);
        sagaState.put(sagaId, "TASK_CREATED");
        System.out.printf("saga %s: TASK_CREATED (taskId=%d)%n", sagaId, taskId);

        kafka.send(SagaTopics06.TASK_CREATED, sagaId,
                "{\"sagaId\":\"" + sagaId + "\",\"taskId\":" + taskId + "}");
    }

    @KafkaListener(topics = SagaTopics06.REJECTED, groupId = "task-creation-service")
    public void onQuotaRejected(String payload) throws Exception {
        JsonNode node = objectMapper.readTree(payload);
        String sagaId = node.get("sagaId").asText();
        sagaState.put(sagaId, "QUOTA_REJECTED");
        System.out.printf("saga %s: QUOTA_REJECTED — завершено отказом%n", sagaId);
        // компенсация не нужна: шаг 1 не выполнен
    }

    public Map<String, String> states() {
        return Map.copyOf(sagaState);
    }
}
