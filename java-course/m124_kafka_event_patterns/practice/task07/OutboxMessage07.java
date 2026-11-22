package m124_kafka_event_patterns.practice.task07;

import jakarta.persistence.Column;
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

    private String aggregateId;
    private String topic;
    private String type;

    @Column(columnDefinition = "TEXT")
    private String payload;

    private Instant occurredAt;
    private Instant sentAt;
    private int attempts;

    protected OutboxMessage07() {
    }

    public static OutboxMessage07 of(String topic, String type, String aggregateId, String payload) {
        OutboxMessage07 m = new OutboxMessage07();
        m.id = UUID.randomUUID();
        m.topic = topic;
        m.type = type;
        m.aggregateId = aggregateId;
        m.payload = payload;
        m.occurredAt = Instant.now();
        m.sentAt = null;
        m.attempts = 0;
        return m;
    }

    public void markSent() { this.sentAt = Instant.now(); }
    public void incrementAttempts() { this.attempts++; }

    public UUID getId() { return id; }
    public String getAggregateId() { return aggregateId; }
    public String getTopic() { return topic; }
    public String getType() { return type; }
    public String getPayload() { return payload; }
    public Instant getOccurredAt() { return occurredAt; }
    public Instant getSentAt() { return sentAt; }
    public int getAttempts() { return attempts; }
}
