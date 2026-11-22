package m124_kafka_event_patterns.practice.task01;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

/**
 * Запись таблицы outbox — событие, ожидающее отправки в брокер.
 *
 * TODO 1: поля — UUID id (@Id), String aggregateId, String topic, String type,
 *         String payload, Instant occurredAt, Instant sentAt (nullable), int attempts.
 * TODO 2: конструктор-фабрика of(topic, type, aggregateId, payloadJson):
 *         id = UUID.randomUUID(), occurredAt = Instant.now(), sentAt = null, attempts = 0.
 * TODO 3: методы markSent() и incrementAttempts() для релэя (задача 02).
 *
 * DDL, который стоит держать в голове (PostgreSQL):
 *   CREATE TABLE outbox (
 *       id uuid PRIMARY KEY, aggregate_id varchar(64) NOT NULL, topic varchar(128) NOT NULL,
 *       type varchar(64) NOT NULL, payload jsonb NOT NULL, occurred_at timestamptz NOT NULL,
 *       sent_at timestamptz, attempts int NOT NULL DEFAULT 0);
 *   CREATE INDEX outbox_unsent_idx ON outbox (occurred_at) WHERE sent_at IS NULL;
 *
 * ЗАЧЕМ partial-индекс: релэй всегда выбирает «где sent_at IS NULL» — индекс должен содержать
 *   только эти строки, иначе он бесполезно распухнет вместе с историей.
 */
@Entity
@Table(name = "outbox")
public class OutboxMessage01 {

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

    protected OutboxMessage01() {
    }

    public static OutboxMessage01 of(String topic, String type, String aggregateId, String payloadJson) {
        OutboxMessage01 m = new OutboxMessage01();
        m.id = UUID.randomUUID();          // id = eventId события
        m.topic = topic;
        m.type = type;
        m.aggregateId = aggregateId;
        m.payload = payloadJson;
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
