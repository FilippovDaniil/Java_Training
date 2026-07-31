package m124_kafka_event_patterns.practice.task02;

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

    protected OutboxMessage02() {
    }

    // TODO 1-2
}
