package m124_kafka_event_patterns.practice.task06;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

/**
 * Запуск саги и наблюдение за её состоянием.
 *
 * TODO 1: POST /api/saga/tasks — тело {"title": "...", "authorId": 10}:
 *           sagaId = UUID, публикация "quota.reserve.requested" с ключом = sagaId,
 *           ответ 202 Accepted с {"sagaId": ...}.
 * TODO 2: GET /api/saga — карта sagaId -> текущий шаг (из TaskCreationService06.states()).
 * TODO 3: объясните в комментарии, почему здесь 202 Accepted, а не 201 Created.
 *
 * ПОДСКАЗКА К TODO 3: на момент ответа задача ещё не создана — процесс только запущен.
 *   Возвращать 201 с id задачи было бы ложью: сага может закончиться отказом по квоте.
 */
@RestController
@RequestMapping("/api/saga")
public class SagaStartController06 {

    private final KafkaTemplate<String, String> kafka;
    private final TaskCreationService06 sagas;

    public SagaStartController06(KafkaTemplate<String, String> kafka, TaskCreationService06 sagas) {
        this.kafka = kafka;
        this.sagas = sagas;
    }

    @PostMapping("/tasks")
    ResponseEntity<?> start(@RequestBody Map<String, Object> body) {
        String title = (String) body.get("title");
        long authorId = ((Number) body.get("authorId")).longValue();
        String sagaId = UUID.randomUUID().toString();

        String payload = String.format(
                "{\"sagaId\":\"%s\",\"title\":\"%s\",\"authorId\":%d}",
                sagaId, title, authorId);
        kafka.send(SagaTopics06.RESERVE_REQUESTED, sagaId, payload);

        return ResponseEntity.accepted().body(Map.of("sagaId", sagaId));
    }

    @GetMapping
    ResponseEntity<?> states() {
        return ResponseEntity.ok(sagas.states());
    }
}
