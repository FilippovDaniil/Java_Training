package m124_kafka_event_patterns.practice.task07;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Limit;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
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

    private static final Logger log = LoggerFactory.getLogger(OutboxRelay07.class);
    private static final int BATCH = 100;

    private final OutboxRepository07 outbox;
    private final KafkaTemplate<String, String> kafka;
    private volatile Instant lastRun;

    public OutboxRelay07(OutboxRepository07 outbox, KafkaTemplate<String, String> kafka) {
        this.outbox = outbox;
        this.kafka = kafka;
    }

    @Scheduled(fixedDelay = 500)
    @Transactional
    public void publish() {
        List<OutboxMessage07> batch = outbox.findBySentAtIsNullOrderByOccurredAtAsc(Limit.of(BATCH));

        for (OutboxMessage07 msg : batch) {
            try {
                kafka.send(msg.getTopic(), msg.getAggregateId(), msg.getPayload())
                        .get(5, TimeUnit.SECONDS);
                msg.markSent();
            } catch (Exception e) {
                msg.incrementAttempts();
                log.error("ошибка отправки id={} attempts={}: {}",
                        msg.getId(), msg.getAttempts(), e.getMessage());
            }
        }

        lastRun = Instant.now();

        if (!batch.isEmpty()) {
            log.info("релэй: отправлено {}, сбоев {}, осталось {}",
                    batch.stream().filter(m -> m.getSentAt() != null).count(),
                    batch.stream().filter(m -> m.getSentAt() == null).count(),
                    outbox.countBySentAtIsNull());
        }
    }

    public Instant lastRun() {
        return lastRun;
    }
}
