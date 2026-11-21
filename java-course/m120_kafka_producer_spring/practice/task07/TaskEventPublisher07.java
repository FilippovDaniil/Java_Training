package m120_kafka_producer_spring.practice.task07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(TaskEventPublisher07.class);

    private final KafkaTemplate<String, Object> kafka;

    public TaskEventPublisher07(KafkaTemplate<String, Object> kafka) {
        this.kafka = kafka;
    }

    public void publishCreated(TaskCreated07 event) {
        send(KafkaTopicsConfig07.TASK_CREATED, String.valueOf(event.id()), event);
    }

    public void publishStatusChanged(TaskStatusChanged07 event) {
        send(KafkaTopicsConfig07.TASK_STATUS_CHANGED, String.valueOf(event.id()), event);
    }

    private void send(String topic, String key, Object event) {
        kafka.send(topic, key, event).whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Не удалось отправить событие в {}: key={}, event={}",
                        topic, key, event, ex);
                return;
            }
            var md = result.getRecordMetadata();
            log.info("отправлено {} -> {} partition {} offset {}",
                    event, topic, md.partition(), md.offset());
        });
    }
}
