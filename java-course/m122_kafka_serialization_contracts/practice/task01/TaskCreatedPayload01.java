package m122_kafka_serialization_contracts.practice.task01;

/**
 * Полезная нагрузка события «задача создана».
 *
 * TODO: поля record — long taskId, String title, long authorId, String status.
 *
 * ПРАВИЛО: payload содержит ровно те поля, которые нужны подписчикам, и ни одного лишнего.
 *   Каждое поле — обязательство поддерживать его совместимо в будущих версиях.
 */
public record TaskCreatedPayload01(long taskId) {
}
