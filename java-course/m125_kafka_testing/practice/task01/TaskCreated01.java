package m125_kafka_testing.practice.task01;

import java.time.Instant;

/**
 * Событие для тестов.
 *
 * TODO: поля record — long id, String title, long authorId, Instant occurredAt.
 */
public record TaskCreated01(long id) {
}
