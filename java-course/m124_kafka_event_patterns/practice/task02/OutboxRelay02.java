package m124_kafka_event_patterns.practice.task02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    private static final Logger log = LoggerFactory.getLogger(OutboxRelay02.class);
    private static final int BATCH_SIZE = 100;
    private static final int MAX_ATTEMPTS_WARN = 10;

    private final OutboxRepository02 outbox;
    private final KafkaTemplate<String, String> kafka;

    public OutboxRelay02(OutboxRepository02 outbox, KafkaTemplate<String, String> kafka) {
        this.outbox = outbox;
        this.kafka = kafka;
    }

    @Scheduled(fixedDelay = 500)
    @Transactional
    public void publishBatch() {
        List<OutboxMessage02> batch = outbox.findBySentAtIsNullOrderByOccurredAtAsc(
                org.springframework.data.domain.Limit.of(BATCH_SIZE));

        if (batch.isEmpty()) {
            return;
        }

        int sent = 0;
        int failed = 0;

        for (OutboxMessage02 msg : batch) {
            if (msg.getAttempts() > MAX_ATTEMPTS_WARN) {
                log.warn("застрявшее событие id={} attempts={} topic={} aggregateId={} — требует внимания",
                        msg.getId(), msg.getAttempts(), msg.getTopic(), msg.getAggregateId());
            }

            try {
                // синхронно, с таймаутом: markSent только после подтверждения брокером
                kafka.send(msg.getTopic(), msg.getAggregateId(), msg.getPayload())
                        .get(5, TimeUnit.SECONDS);

                msg.markSent();
                sent++;
            } catch (Exception e) {
                msg.incrementAttempts();
                failed++;
                log.error("ошибка отправки id={} topic={}: {}",
                        msg.getId(), msg.getTopic(), e.getMessage());
            }
        }

        long remaining = outbox.countBySentAtIsNull();
        log.info("релэй: отправлено {}, сбоев {}, осталось {}", sent, failed, remaining);
    }
}
