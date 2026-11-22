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
        // 1) валидация — не-retryable
        if (event.eventId() == null || event.payload() == null || event.payload().isBlank()) {
            failures.incrementAndGet();
            throw new IllegalArgumentException("некорректный конверт: " + event);
        }

        // 2) дедупликация
        if (!processedEvents.markProcessed(event.eventId())) {
            System.out.printf("дубль eventId=%s — пропускаем%n", event.eventId());
            ack.acknowledge();   // дубль тоже подтверждаем!
            return;
        }

        // 3) эффект
        if (event.payload().contains("smtp-down")) {
            failures.incrementAndGet();
            throw new RuntimeException("SMTP недоступен: " + event.payload());
        }

        System.out.printf("уведомление отправлено для eventId=%s payload=%s%n",
                event.eventId(), event.payload());

        // 4) ack только после успеха
        ack.acknowledge();
    }

    public int failureCount() {
        return failures.get();
    }
}
