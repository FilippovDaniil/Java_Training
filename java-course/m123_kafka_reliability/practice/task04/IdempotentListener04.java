package m123_kafka_reliability.practice.task04;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Идемпотентный слушатель: побочный эффект ровно один раз на eventId.
 *
 * TODO 1: если !processedEvents.markProcessed(event.eventId()) — напечатать
 *         «дубль eventId=... — пропускаем» и выйти.
 * TODO 2: иначе выполнить эффект: напечатать «письмо отправлено для eventId=...»
 *         и увеличить счётчик effects.
 * TODO 3: напечатать текущее число эффектов — оно должно совпадать с числом УНИКАЛЬНЫХ событий.
 *
 * ВАЖНО (порядок в проде): проверка-отметка и эффект — в одной транзакции БД (@Transactional).
 *   Здесь эффект — печать в консоль, поэтому транзакции нет; в реальном коде без неё
 *   идемпотентность иллюзорна.
 */
@Component
public class IdempotentListener04 {

    private final ProcessedEvents04 processedEvents;
    private final AtomicInteger effects = new AtomicInteger();

    public IdempotentListener04(ProcessedEvents04 processedEvents) {
        this.processedEvents = processedEvents;
    }

    @KafkaListener(topics = "task.created", groupId = "idempotent-demo")
    public void on(EventEnvelope04 event) {
        // TODO 1-3
    }
}
