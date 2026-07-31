package m122_kafka_serialization_contracts.practice.task03;

/**
 * Схема события версии 1 — то, что уже работает в проде и читается старыми подписчиками.
 *
 * TODO: поля record — long taskId, String title, long authorId.
 *
 * ПРАВИЛО: этот файл после релиза не меняют. Новая версия — отдельный тип (V2), а не правка V1;
 *   иначе невозможно проверить совместимость и откатиться.
 */
public record TaskCreatedV103(long taskId) {
}
