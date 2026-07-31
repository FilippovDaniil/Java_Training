package m123_kafka_reliability.practice.task07;

import java.time.Instant;
import java.util.UUID;

/**
 * Конверт события уведомления.
 *
 * TODO: поля record — UUID eventId, String type, Instant occurredAt, String payload.
 */
public record EventEnvelope07(UUID eventId) {
}
