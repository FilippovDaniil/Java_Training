package m122_kafka_serialization_contracts.practice.task03;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Два потребителя одного топика: «старый код» (ждёт v1) и «новый код» (ждёт v2).
 *
 * TODO 1: onV1 — группа "consumer-v1", печатать все поля v1 (проверка forward-совместимости).
 * TODO 2: onV2 — группа "consumer-v2", печатать поля v2 включая priority/deadline
 *         (проверка backward-совместимости).
 * TODO 3: в обоих методах печатать «сырое» значение тоже — чтобы видеть, что пришло в топик,
 *         и что из этого потребитель разобрал.
 *
 * ВАЖНО: разные group.id обязательны — иначе два слушателя поделят партиции и каждое событие
 *   попадёт только в одного из них, и эксперимент не покажет обе стороны совместимости.
 */
@Component
public class CompatibilityListener03 {

    @KafkaListener(topics = "task.created", groupId = "consumer-v1")
    public void onV1(TaskCreatedV103 event) {
        // TODO 1
    }

    @KafkaListener(topics = "task.created", groupId = "consumer-v2")
    public void onV2(TaskCreatedV203 event) {
        // TODO 2
    }
}
