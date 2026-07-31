package m123_kafka_reliability.practice.task07;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * Основной слушатель уведомлений: валидация -> дедупликация -> эффект -> ack.
 *
 * TODO 1: валидация: eventId == null или payload пустой -> IllegalArgumentException
 *         (не-retryable: такие события повторять бессмысленно, им место в DLT).
 * TODO 2: дедупликация: processedEvents.markProcessed(eventId) == false -> лог «дубль» + ack + return.
 * TODO 3: эффект: если payload содержит "smtp-down" -> RuntimeException("SMTP недоступен")
 *         (retryable: повторяем с backoff); иначе печать «уведомление отправлено».
 * TODO 4: ack.acknowledge() только после успешной обработки.
 * TODO 5: счётчик отказов (сколько раз падали) — для сравнения с числом записей в DLT.
 *
 * ВАЖНЫЙ НЮАНС: дубль тоже нужно ПОДТВЕРЖДАТЬ (ack), иначе он будет приходить снова и снова —
 *   «дубль» не равно «ошибка обработки».
 */
@Component
public class NotificationListener07 {

    private final ProcessedEvents07 processedEvents;
    private final AtomicInteger failures = new AtomicInteger();

    public NotificationListener07(ProcessedEvents07 processedEvents) {
        this.processedEvents = processedEvents;
    }

    @KafkaListener(topics = "task.created", groupId = "notifier", id = "notifier-main")
    public void on(EventEnvelope07 event, Acknowledgment ack) {
        // TODO 1-5
    }

    public int failureCount() {
        return failures.get();
    }
}
