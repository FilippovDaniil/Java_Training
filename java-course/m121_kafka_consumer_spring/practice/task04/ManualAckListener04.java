package m121_kafka_consumer_spring.practice.task04;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * Слушатель с ручным подтверждением офсета.
 *
 * TODO 1: напечатать partition/offset/value записи.
 * TODO 2: если value содержит "boom" — бросить RuntimeException("сломанное событие")
 *         ДО подтверждения (эмулируем сбой обработки).
 * TODO 3: иначе — ack.acknowledge().
 *
 * ВАЖНО: порядок «работа -> ack» — это и есть выбор гарантии. Никогда не подтверждайте офсет
 *   «на входе», чтобы «не тормозить»: потерянное событие не восстановить из лога, если группа
 *   уже уехала вперёд (только ручным reset-offsets с простоем).
 */
@Component
public class ManualAckListener04 {

    @KafkaListener(topics = "task.created", groupId = "manual-ack")
    public void onTaskCreated(ConsumerRecord<String, String> rec, Acknowledgment ack) {
        // TODO 1: печать метаданных
        // TODO 2: имитация сбоя на "boom"
        // TODO 3: подтверждение офсета
    }
}
