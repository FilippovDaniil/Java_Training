package m124_kafka_event_patterns.practice.task07;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

/**
 * Запись outbox капстоуна.
 *
 * TODO 1: поля — UUID id (= eventId), String aggregateId, String topic, String type,
 *         String payload, Instant occurredAt, Instant sentAt, int attempts.
 * TODO 2: фабрика of(topic, type, aggregateId, payload) + markSent() + incrementAttempts().
 */
@Entity
@Table(name = "outbox7")
public class OutboxMessage07 {

    @Id
    private UUID id;

    protected OutboxMessage07() {
    }

    // TODO 1-2
}
