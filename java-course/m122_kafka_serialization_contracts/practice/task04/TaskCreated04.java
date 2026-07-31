package m122_kafka_serialization_contracts.practice.task04;

/**
 * Событие «задача создана» (логическое имя типа в контракте — "created").
 *
 * TODO: поля record — long taskId, String title, long authorId.
 */
public record TaskCreated04(long taskId) {
}
