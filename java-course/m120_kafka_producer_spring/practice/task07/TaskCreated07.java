package m120_kafka_producer_spring.practice.task07;

import java.time.Instant;

/**
 * Событие «задача создана» — публичный контракт для подписчиков (модуль 121 будет его читать).
 *
 * TODO: поля record — long id, String title, long authorId, Instant occurredAt.
 */
public record TaskCreated07(long id) {
}
