package m121_kafka_consumer_spring.practice.task07;

import java.time.Instant;

/**
 * Событие «задача создана» (контракт продюсера из модуля 120).
 *
 * TODO: поля record — long id, String title, long authorId, Instant occurredAt.
 */
public record TaskCreated07(long id) {
}
