package m121_kafka_consumer_spring.practice.task03;

import java.time.Instant;

/**
 * Событие «задача создана» на стороне потребителя.
 *
 * TODO: поля record — long id, String title, long authorId, Instant occurredAt.
 *
 * ВАЖНО: у потребителя своя копия контракта — это нормально для микросервисов. Главное,
 *   чтобы имена полей совпадали с публикуемым JSON; неизвестные поля потребитель должен
 *   игнорировать (Jackson: FAIL_ON_UNKNOWN_PROPERTIES = false), иначе новое поле в событии
 *   сломает всех подписчиков (совместимость схем — модуль 122).
 */
public record TaskCreated03(long id) {
}
