package m124_kafka_event_patterns.practice.task06;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        // TODO 1
        return ResponseEntity.status(501).build();
    }

    @GetMapping
    ResponseEntity<?> states() {
        // TODO 2
        return ResponseEntity.status(501).build();
    }
}
