package m120_kafka_producer_spring.practice.task06;

import java.time.Instant;

/**
 * Событие «задача создана» для сравнения двух продюсеров.
 *
 * TODO: поля record — long id, String title, Instant occurredAt.
 */
public record TaskCreated06(long id) {
}
