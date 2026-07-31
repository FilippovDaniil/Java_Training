package m120_kafka_producer_spring.practice.task07;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Единственный класс приложения, который знает про Kafka: топики, ключи, обработку сбоев.
 *
 * TODO 1: publishCreated(TaskCreated07 event) — send(TASK_CREATED, ключ = id, event).
 * TODO 2: publishStatusChanged(TaskStatusChanged07 event) — send(TASK_STATUS_CHANGED, ключ = id, event).
 * TODO 3: общий обработчик результата: при ошибке — log.error с ключом и причиной
 *         (событие потеряно, это надо видеть в логах и метриках).
 *
 * ЗАЧЕМ: сервис-слой публикует «событие домена», не зная ни имени топика, ни того, что
 *   транспортом является Kafka. Заменим брокер — правим один класс.
 */
@Service
public class TaskEventPublisher07 {

    private final KafkaTemplate<String, Object> kafka;

    public TaskEventPublisher07(KafkaTemplate<String, Object> kafka) {
        this.kafka = kafka;
    }

    public void publishCreated(TaskCreated07 event) {
        // TODO 1
    }

    public void publishStatusChanged(TaskStatusChanged07 event) {
        // TODO 2
    }
}
