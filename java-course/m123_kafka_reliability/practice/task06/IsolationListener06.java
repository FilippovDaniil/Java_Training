package m123_kafka_reliability.practice.task06;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Два потребителя с разным уровнем изоляции — видно эффект транзакций.
 *
 * TODO 1: onCommitted — группа "committed-reader", печатать «[committed] ...».
 *         Уровень read_committed берётся из application.yml.
 * TODO 2: onUncommitted — группа "uncommitted-reader" с переопределением свойства:
 *         @KafkaListener(..., properties = "isolation.level=read_uncommitted"),
 *         печатать «[uncommitted] ...».
 * TODO 3: сравнить, что увидел каждый после publishBoth и после publishAndRollback.
 *
 * ПОДСКАЗКА: параметр properties у @KafkaListener позволяет менять настройки consumer'а
 *   для одного слушателя, не заводя вторую ConsumerFactory.
 */
@Component
public class IsolationListener06 {

    @KafkaListener(topics = "task.created", groupId = "committed-reader")
    public void onCommitted(String value) {
        // TODO 1
    }

    @KafkaListener(topics = "task.created", groupId = "uncommitted-reader",
            properties = "isolation.level=read_uncommitted")
    public void onUncommitted(String value) {
        // TODO 2
    }
}
