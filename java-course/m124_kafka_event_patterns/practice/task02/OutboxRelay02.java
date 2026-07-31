package m124_kafka_event_patterns.practice.task02;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Релэй: единственный компонент, который отправляет события из outbox в Kafka.
 *
 * TODO 1: @Scheduled(fixedDelay = 500) @Transactional publishBatch():
 *           - выбрать до 100 неотправленных;
 *           - отправить каждую (topic, aggregateId как ключ, payload как значение);
 *           - при успехе markSent(), при ошибке incrementAttempts();
 *           - в конце: лог «релэй: отправлено N, сбоев M, осталось K».
 * TODO 2: отправку делать синхронно с таймаутом (get(5, SECONDS)) ИЛИ асинхронно, но тогда
 *         markSent — только в whenComplete при успехе. Объясните в комментарии, почему
 *         «пометить отправленным сразу после send()» — ошибка.
 * TODO 3: WARN для записей с attempts > 10 («застрявшее» событие требует внимания человека).
 *
 * ВАЖНО ПРО ПОРЯДОК: события одной сущности обязаны уходить в порядке occurredAt, иначе
 *   подписчик увидит DONE раньше NEW. Поэтому выборка сортируется, а ключ = aggregateId
 *   (одна партиция). Параллельная отправка пачки ломает порядок — для строгого порядка
 *   отправляйте последовательно или группируйте по aggregateId.
 */
@Component
public class OutboxRelay02 {

    private final OutboxRepository02 outbox;
    private final KafkaTemplate<String, String> kafka;

    public OutboxRelay02(OutboxRepository02 outbox, KafkaTemplate<String, String> kafka) {
        this.outbox = outbox;
        this.kafka = kafka;
    }

    @Transactional
    public void publishBatch() {
        // TODO 1-3
    }
}
