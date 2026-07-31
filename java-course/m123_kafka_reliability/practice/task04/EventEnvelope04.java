package m123_kafka_reliability.practice.task04;

import java.time.Instant;
import java.util.UUID;

/**
 * Конверт события с идентификатором для дедупликации.
 *
 * TODO: поля record — UUID eventId, String type, Instant occurredAt, String payload.
 *
 * ВАЖНО: eventId генерируется ОДИН РАЗ при создании события (в источнике), а не при отправке.
 */
public record EventEnvelope04(UUID eventId) {
}
