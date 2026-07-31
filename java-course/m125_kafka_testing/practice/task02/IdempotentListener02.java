package m125_kafka_testing.practice.task02;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * Слушатель под тестом: дедупликация по eventId + подтверждение офсета.
 *
 * TODO 1: если событие уже обработано (processed.add вернул false) — ack.acknowledge() и выход.
 * TODO 2: иначе notificationService.notify(event) и затем ack.acknowledge().
 * TODO 3: при исключении из сервиса — НЕ подтверждать офсет (исключение пробрасывается).
 *
 * ЗАМЕТЬТЕ: этот класс тестируется без Spring и без Kafka. Аннотация @KafkaListener нужна
 *   рантайму, а тесту — нет: он вызывает метод напрямую. Так и должно быть спроектировано.
 */
@Component
public class IdempotentListener02 {

    private final Set<UUID> processed = ConcurrentHashMap.newKeySet();
    private final NotificationService02 notificationService;

    public IdempotentListener02(NotificationService02 notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "task.created", groupId = "notifier")
    public void on(EventEnvelope02 event, Acknowledgment ack) {
        // TODO 1-3
    }
}
