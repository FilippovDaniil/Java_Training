package m121_kafka_consumer_spring.practice.task02;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * Слушатель-аудитор: печатает всё, что известно о записи.
 *
 * TODO 1: onRecord — из ConsumerRecord достать topic(), partition(), offset(), key(),
 *         timestamp(), value() и напечатать одной строкой с префиксом [audit].
 * TODO 2: onWithHeaders — получить ключ и партицию через @Header и напечатать с префиксом
 *         [audit-headers].
 *
 * КОГДА ЧТО: ConsumerRecord — когда нужны все метаданные (диагностика, дедупликация по
 *   topic-partition-offset); @Header — когда нужны одно-два поля и метод хочется держать
 *   близким к бизнес-смыслу.
 */
@Component
public class AuditListener02 {

    @KafkaListener(topics = {"task.created", "task.status.changed"}, groupId = "audit")
    public void onRecord(ConsumerRecord<String, String> rec) {
        // TODO 1
    }

    @KafkaListener(topics = "task.created", groupId = "audit-headers")
    public void onWithHeaders(String value,
                              @Header(KafkaHeaders.RECEIVED_KEY) String key,
                              @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        // TODO 2
    }
}
