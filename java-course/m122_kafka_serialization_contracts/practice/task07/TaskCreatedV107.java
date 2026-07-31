package m122_kafka_serialization_contracts.practice.task07;

/**
 * Payload события «задача создана», версия 1 — зафиксированный контракт.
 *
 * TODO: поля record — long taskId, String title, long authorId.
 */
public record TaskCreatedV107(long taskId) {
}
