package m120_kafka_producer_spring.practice.task03;

import java.time.Instant;

/**
 * Событие «задача создана» — контракт для подписчиков.
 *
 * TODO: опишите поля record:
 *   long id            — идентификатор задачи (он же ключ сообщения);
 *   String title       — название;
 *   long authorId      — автор;
 *   Instant occurredAt — момент, когда событие произошло (не когда отправлено).
 *
 * ПРАВИЛА КОНТРАКТА:
 *   - только простые/сериализуемые типы, никаких JPA-сущностей и ленивых коллекций;
 *   - имена полей не переименовываем — это публичный контракт (подробнее в модуле 122);
 *   - occurredAt в UTC (Instant), а не LocalDateTime без зоны.
 */
public record TaskCreated03(long id) {
}
