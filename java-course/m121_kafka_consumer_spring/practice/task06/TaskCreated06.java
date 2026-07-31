package m121_kafka_consumer_spring.practice.task06;

import java.time.Instant;

/**
 * Событие «задача создана» для батчевой обработки.
 *
 * TODO: поля record — long id, String title, long authorId, Instant occurredAt.
 */
public record TaskCreated06(long id) {
}
