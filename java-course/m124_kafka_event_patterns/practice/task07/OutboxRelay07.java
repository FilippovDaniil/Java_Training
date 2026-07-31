package m124_kafka_event_patterns.practice.task07;

import java.time.Instant;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Релэй капстоуна: единственный отправитель в Kafka.
 *
 * TODO 1: @Scheduled(fixedDelay = 500) @Transactional publish():
 *           - выбрать до 100 неотправленных по occurredAt;
 *           - отправить (ключ = aggregateId, значение = payload) синхронно с таймаутом;
 *           - markSent() при успехе, incrementAttempts() при ошибке;
 *           - запомнить lastRun = Instant.now() (для /api/ops).
 * TODO 2: не терять исключения: логировать ошибку с id записи и числом попыток.
 * TODO 3: сохранять порядок событий одной задачи — отправлять последовательно (не в parallelStream).
 */
@Component
public class OutboxRelay07 {

    private final OutboxRepository07 outbox;
    private final KafkaTemplate<String, String> kafka;
    private volatile Instant lastRun;

    public OutboxRelay07(OutboxRepository07 outbox, KafkaTemplate<String, String> kafka) {
        this.outbox = outbox;
        this.kafka = kafka;
    }

    @Transactional
    public void publish() {
        // TODO 1-3
    }

    public Instant lastRun() {
        return lastRun;
    }
}
