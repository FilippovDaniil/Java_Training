package m120_kafka_producer_spring.practice.task07;

import java.time.Instant;

/**
 * Событие «статус задачи изменён».
 *
 * TODO: поля record — long id, String oldStatus, String newStatus, Instant occurredAt.
 */
public record TaskStatusChanged07(long id) {
}
