package m125_kafka_testing.practice.task02;

import java.time.Instant;
import java.util.UUID;

/**
 * Конверт события.
 *
 * TODO: поля record — UUID eventId, String type, Instant occurredAt, String payload.
 */
public record EventEnvelope02(UUID eventId) {
}
