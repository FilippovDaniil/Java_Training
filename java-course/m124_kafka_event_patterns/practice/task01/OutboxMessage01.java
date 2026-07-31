package m124_kafka_event_patterns.practice.task01;

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

    protected OutboxMessage01() {
    }

    // TODO 1-3
}
