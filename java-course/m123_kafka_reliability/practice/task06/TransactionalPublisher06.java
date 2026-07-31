package m123_kafka_reliability.practice.task06;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Отправка нескольких записей одной транзакцией Kafka.
 *
 * TODO 1: publishBoth — kafka.executeInTransaction(t -> { t.send("task.created", key, event);
 *         t.send("task.audit", key, audit); return true; }).
 * TODO 2: publishAndRollback — то же, но после отправок бросить RuntimeException внутри лямбды:
 *         транзакция должна быть отменена, обе записи — невидимы для read_committed.
 * TODO 3 (наблюдение): вызвать обычный kafka.send(...) ВНЕ транзакции при включённом
 *         transaction-id-prefix и посмотреть, что произойдёт (подсказка: KafkaTemplate
 *         с allowNonTransactional / исключение о необходимости транзакции).
 *
 * ГДЕ ЭТО ПРИМЕНИМО В ПРОДЕ: паттерн read-process-write — прочитали из топика A, посчитали,
 *   записали в топики B и C, и всё это (включая коммит офсета A) стало видимым атомарно.
 */
@Service
public class TransactionalPublisher06 {

    private final KafkaTemplate<String, Object> kafka;

    public TransactionalPublisher06(KafkaTemplate<String, Object> kafka) {
        this.kafka = kafka;
    }

    public void publishBoth(String key, Object event, Object audit) {
        // TODO 1
    }

    public void publishAndRollback(String key, Object event, Object audit) {
        // TODO 2
    }
}
