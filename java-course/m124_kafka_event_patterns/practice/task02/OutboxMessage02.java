package m124_kafka_event_patterns.practice.task02;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

/**
 * Запись outbox (как в задаче 01) — здесь она нужна релэю.
 *
 * TODO 1: поля — UUID id, String aggregateId, String topic, String type, String payload,
 *         Instant occurredAt, Instant sentAt, int attempts + геттеры.
 * TODO 2: markSent() — sentAt = Instant.now(); incrementAttempts() — attempts++.
 */
@Entity
@Table(name = "outbox2")
public class OutboxMessage02 {

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

    protected OutboxMessage02() {
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