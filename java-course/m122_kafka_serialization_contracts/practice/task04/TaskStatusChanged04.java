package m122_kafka_serialization_contracts.practice.task04;

/**
 * Событие «статус изменён» (логическое имя типа в контракте — "status").
 *
 * TODO: поля record — long taskId, String oldStatus, String newStatus.
 */
public record TaskStatusChanged04(long taskId) {
}
