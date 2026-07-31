package m122_kafka_serialization_contracts.practice.task07;

import java.time.Instant;
import java.util.UUID;

/**
 * Конверт события — общий для всех типов и версий.
 *
 * TODO: поля record — UUID eventId, String type, int version, Instant occurredAt,
 *       String traceId, String source, Object payload.
 *
 * ЗАЧЕМ Object payload здесь: продюсер кладёт в один и тот же конверт payload разных версий.
 *   У потребителя payload разбирают в конкретный тип по паре (type, version) — либо через
 *   generic-конверт с JavaType, либо вторым шагом через ObjectMapper.convertValue.
 */
public record EventEnvelope07(UUID eventId) {
}
