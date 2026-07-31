package m122_kafka_serialization_contracts.practice.task02;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * Чтение заголовков в слушателе.
 *
 * TODO 1: объявить параметры @Header("eventId"), @Header("eventType"),
 *         @Header(name = "version", required = false), @Header(name = "traceId", required = false).
 * TODO 2: напечатать все заголовки и значение.
 * TODO 3 (наблюдение): отправьте CLI-продюсером сообщение БЕЗ заголовков и посмотрите,
 *         что произойдёт с required = true и с required = false.
 *
 * ПОДСКАЗКА: заголовки приходят как String, если сконвертированы Spring'ом; при работе с
 *   «сырыми» байтами используйте ConsumerRecord.headers() и декодируйте сами.
 */
@Component
public class HeaderListener02 {

    @KafkaListener(topics = "task.created", groupId = "headers-demo")
    public void on(String value,
                   @Header(name = "eventId", required = false) String eventId,
                   @Header(name = "eventType", required = false) String eventType) {
        // TODO 1-2
    }
}
